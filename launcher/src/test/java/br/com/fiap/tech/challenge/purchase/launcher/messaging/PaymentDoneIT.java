package br.com.fiap.tech.challenge.purchase.launcher.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseNotification;
import br.com.fiap.tech.challenge.purchase.application.dto.SimplePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.UpdatePaymentDTO;
import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import br.com.fiap.tech.challenge.purchase.launcher.service.QueueService;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus.ERROR;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PaymentStatus.PAID;
import static br.com.fiap.tech.challenge.purchase.launcher.containers.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.containers.LocalStackContainers.localStackContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.util.ConfigurationOverrides.overrideConfiguration;
import static br.com.fiap.tech.challenge.purchase.launcher.util.PurchaseUtil.getPaymentDoneQueueName;
import static br.com.fiap.tech.challenge.purchase.launcher.util.QueueUtil.sendMessage;
import static org.awaitility.Awaitility.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({"test"})
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class PaymentDoneIT {

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @Autowired
    private Environment env;

    @Autowired
    private SqsTemplate sqsTemplate;

    @MockBean
    private QueueService queueService;

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, DATABASE, LOCAL_STACK_CONTAINER);
    }

    @Test
    @Sql(scripts = {"/scripts/clean.sql", "/scripts/create-purchase.sql"})
    void testPaymentConfirmed() {
        var message = new UpdatePaymentDTO(
                UUID.randomUUID().toString(),
                "754f95a6-1b54-42f8-ae6f-d4eb0ddd9df3",
                PAID
        );

        sendMessage(sqsTemplate, getPaymentDoneQueueName(env), message);

        given().await()
                .pollInterval(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .until(() -> {
                    try {
                        verify(queueService).received(any(SimplePurchaseDTO.class));
                    } catch (AssertionError e) {
                        return false;
                    }

                    try {
                        verify(queueService).received(any(PurchaseNotification.class));
                    } catch (AssertionError e) {
                        return false;
                    }

                    return true;
                });
    }

    @Test
    @Sql(scripts = {"/scripts/clean.sql", "/scripts/create-purchase.sql"})
    void testPaymentFailed() {
        var message = new UpdatePaymentDTO(
                UUID.randomUUID().toString(),
                "754f95a6-1b54-42f8-ae6f-d4eb0ddd9df3",
                ERROR
        );

        sendMessage(sqsTemplate, getPaymentDoneQueueName(env), message);

        given().await()
                .pollInterval(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .until(() -> {
                    try {
                        verify(queueService, never()).received(any(SimplePurchaseDTO.class));
                    } catch (AssertionError e) {
                        return false;
                    }

                    try {
                        verify(queueService).received(any(PurchaseNotification.class));
                    } catch (AssertionError e) {
                        return false;
                    }

                    return true;
                });
    }
}
