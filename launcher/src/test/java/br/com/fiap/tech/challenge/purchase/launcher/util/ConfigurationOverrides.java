package br.com.fiap.tech.challenge.purchase.launcher.util;

import io.restassured.RestAssured;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import static org.slf4j.LoggerFactory.getLogger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationOverrides {

    private static final Logger LOGGER = getLogger(ConfigurationOverrides.class);

    public static final int LOCAL_PORT = 8691;

    public static void overrideConfiguration(DynamicPropertyRegistry registry, MySQLContainer<?> database, GenericContainer<?> localstack) {
        localstack.followOutput(new Slf4jLogConsumer(LOGGER));

        System.setProperty("spring.datasource.url", database.getJdbcUrl());
        System.setProperty("spring.datasource.username", database.getUsername());
        System.setProperty("spring.datasource.password", database.getPassword());

        System.setProperty("server.port", String.valueOf(LOCAL_PORT));

        RestAssured.port = LOCAL_PORT;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
