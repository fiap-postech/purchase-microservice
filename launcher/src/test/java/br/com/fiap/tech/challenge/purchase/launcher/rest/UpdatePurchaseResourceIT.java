package br.com.fiap.tech.challenge.purchase.launcher.rest;

import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.CREATED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.DELIVERED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.MADE;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.MAKING;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.PAID_ERROR;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.PAID_SUCCESS;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_MAKE;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_PAYMENT;
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
@ActiveProfiles({"test"})
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
    @Sql(scripts = {"/scripts/clean.sql"})
    void shouldGetAnErrorWhenTryToUpdatePurchaseThatNotExists() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .put("/purchase/{id}/{status}", UUID.randomUUID().toString(), WAITING_MAKE)
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    @ParameterizedTest
    @MethodSource("errorStatusOptionsStream")
    @Sql(scripts = {"/scripts/clean.sql", "/scripts/create-purchase.sql"})
    void shouldGetAnErrorWhenTryToUpdatePurchaseStatusToAInvalidOne(PurchaseStatusChange change) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .put("/purchase/{id}/{status}", change.getId(), change.getStatus())
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    @ParameterizedTest
    @MethodSource("allowedStatusOptionsStream")
    @Sql(scripts = {"/scripts/clean.sql", "/scripts/create-purchase.sql"})
    void shouldUpdatePurchase(PurchaseStatusChange change) {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .put("/purchase/{id}/{status}", change.getId(), change.getStatus())
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/PurchaseResponseSchema.json"));
    }

    static Stream<PurchaseStatusChange> allowedStatusOptionsStream() {
        return Stream.of(
                new PurchaseStatusChange("163e3c9f-5a97-4ba0-bab4-b69d11530771", PAID_SUCCESS),
                new PurchaseStatusChange("163e3c9f-5a97-4ba0-bab4-b69d11530771", WAITING_PAYMENT),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", PAID_ERROR),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", WAITING_MAKE),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", MAKING),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", MADE),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", DELIVERED)
        );
    }

    static Stream<PurchaseStatusChange> errorStatusOptionsStream() {
        return Stream.of(
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", CREATED),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", WAITING_MAKE),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", MAKING),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", MADE),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", DELIVERED),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", PAID_SUCCESS),
                new PurchaseStatusChange("e76cf4a5-0994-4729-972f-682529714120", PAID_ERROR),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", CREATED),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", PAID_ERROR),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", PAID_SUCCESS),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", MAKING),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", MADE),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", WAITING_PAYMENT),
                new PurchaseStatusChange("086dd216-2ef7-432c-a27b-4c840624c98d", DELIVERED),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", CREATED),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", PAID_SUCCESS),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", PAID_ERROR),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", MAKING),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", MADE),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", WAITING_PAYMENT),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", DELIVERED),
                new PurchaseStatusChange("ebbf8a00-3702-4758-84a9-a6cce4a5cbf6", WAITING_MAKE),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", CREATED),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", PAID_SUCCESS),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", PAID_ERROR),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", WAITING_PAYMENT),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", WAITING_MAKE),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", MADE),
                new PurchaseStatusChange("0f97ed15-2b11-48b5-bc08-fbac61e40c27", DELIVERED),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", MAKING),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", CREATED),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", PAID_SUCCESS),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", WAITING_PAYMENT),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", PAID_ERROR),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", WAITING_MAKE),
                new PurchaseStatusChange("7d79859c-5831-48d0-920b-b9affda68074", DELIVERED),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", MADE),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", CREATED),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", WAITING_PAYMENT),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", PAID_ERROR),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", PAID_SUCCESS),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", WAITING_MAKE),
                new PurchaseStatusChange("0480c7c0-7c81-430c-bfec-1a17e9915c06", MAKING),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", DELIVERED),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", MADE),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", WAITING_PAYMENT),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", MAKING),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", WAITING_MAKE),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", PAID_SUCCESS),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", PAID_ERROR),
                new PurchaseStatusChange("0ef0fa3c-4147-4cd2-9afd-0971afdb31e8", CREATED)
        );
    }

    @RequiredArgsConstructor
    @Getter
    static class PurchaseStatusChange {
        private final String id;
        private final PurchaseStatus status;
    }

}
