package br.com.fiap.tech.challenge.purchase.launcher.rest;

import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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

import java.util.UUID;

import static br.com.fiap.tech.challenge.purchase.launcher.containers.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.containers.LocalStackContainers.localStackContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.util.ConfigurationOverrides.overrideConfiguration;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({ "test" })
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class GetPurchaseResourceIT {

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, DATABASE, LOCAL_STACK_CONTAINER);
    }

    @Test
    @Sql(scripts = { "/scripts/clean.sql" })
    void shouldGetAnErrorWhenPurchasesDoesNotExists() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/purchase/{id}", UUID.randomUUID().toString())
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    @Test
    @Sql(scripts = { "/scripts/clean.sql", "/scripts/create-purchase.sql" })
    void shouldGetPurchasesWhenExists() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/purchase/{id}", "086dd216-2ef7-432c-a27b-4c840624c98d")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PurchaseResponseSchema.json"));
    }



}
