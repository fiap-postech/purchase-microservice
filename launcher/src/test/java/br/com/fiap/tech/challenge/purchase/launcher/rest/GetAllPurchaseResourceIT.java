package br.com.fiap.tech.challenge.purchase.launcher.rest;

import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.fiap.tech.challenge.purchase.launcher.containers.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.containers.LocalStackContainers.localStackContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.util.ConfigurationOverrides.overrideConfiguration;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({ "test" })
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class GetAllPurchaseResourceIT {

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @Autowired
    private Environment env;

    @Autowired
    private SqsTemplate sqsTemplate;

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, DATABASE, LOCAL_STACK_CONTAINER);
    }

    @Test
    @Sql(scripts = { "/scripts/clean.sql" })
    void shouldGetEmptyResponseWhenPurchasesDoesNotExists() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/purchase")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PurchaseResponseListSchema.json"));
    }

    @Test
    @Sql(scripts = { "/scripts/clean.sql", "/scripts/create-purchase.sql" })
    void shouldGetPurchasesWhenExists() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/purchase")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PurchaseResponseListSchema.json"));
    }



}
