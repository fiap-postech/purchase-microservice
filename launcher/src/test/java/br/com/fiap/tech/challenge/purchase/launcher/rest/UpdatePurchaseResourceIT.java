package br.com.fiap.tech.challenge.purchase.launcher.rest;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
import java.util.stream.Stream;

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
class UpdatePurchaseResourceIT {

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
    void shouldGetAnErrorWhenTryToUpdatePurchaseThatNotExists() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .patch("/purchase/{id}/{status}", UUID.randomUUID().toString(), PurchaseStatus.WAITING_MAKE)
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    @ParameterizedTest
    @MethodSource("statusOptionsStream")
    @Sql(scripts = { "/scripts/clean.sql", "/scripts/create-purchase.sql" })
    void shouldUpdatePurchase(PurchaseStatus status) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .patch("/purchase/{id}/{status}", "086dd216-2ef7-432c-a27b-4c840624c98d", status)
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PurchaseResponseSchema.json"));
    }

    static Stream<PurchaseStatus> statusOptionsStream() {
        return Stream.of(
                PurchaseStatus.WAITING_MAKE,
                PurchaseStatus.MAKING,
                PurchaseStatus.MADE,
                PurchaseStatus.DELIVERED
        );
    }

}
