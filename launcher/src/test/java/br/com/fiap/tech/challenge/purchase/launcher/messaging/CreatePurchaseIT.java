package br.com.fiap.tech.challenge.purchase.launcher.messaging;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import br.com.fiap.tech.challenge.purchase.launcher.service.QueueService;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.instancio.Model;
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
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.purchase.launcher.containers.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.containers.LocalStackContainers.localStackContainer;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseInputDTOFixture.comboAndSandwichAndBeveragePurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseInputDTOFixture.sandwichAndBeveragePurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseInputDTOFixture.singleBeveragePurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseInputDTOFixture.singleComboPurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseInputDTOFixture.singleSandwichPurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.fixture.input.PurchaseInputDTOFixture.singleSideDishPurchaseInputDTO;
import static br.com.fiap.tech.challenge.purchase.launcher.util.ConfigurationOverrides.overrideConfiguration;
import static br.com.fiap.tech.challenge.purchase.launcher.util.PurchaseUtil.getAllPurchases;
import static br.com.fiap.tech.challenge.purchase.launcher.util.PurchaseUtil.getCartClosedQueueName;
import static br.com.fiap.tech.challenge.purchase.launcher.util.PurchaseUtil.getPaymentDoneQueueName;
import static br.com.fiap.tech.challenge.purchase.launcher.util.QueueUtil.sendMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({"test"})
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class CreatePurchaseIT {

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

    @ParameterizedTest
    @MethodSource("getCreateOptions")
    void testCreatePurchase(Model<PurchaseInputDTO> model) {
        var message = create(model);

        var responseBeforeTest = getAllPurchases();
        var countBeforeTest = responseBeforeTest.totalElements();

        sendMessage(sqsTemplate, getCartClosedQueueName(env), message);

        given().await()
                .pollInterval(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    var response = getAllPurchases();

                    assertThat(response.totalElements()).isEqualTo(countBeforeTest + 1);
                });

        verify(queueService).received(any(PurchaseDTO.class));
    }

    static Stream<Model<PurchaseInputDTO>> getCreateOptions() {
        return Stream.of(
                singleBeveragePurchaseInputDTO(),
                singleSideDishPurchaseInputDTO(),
                singleSandwichPurchaseInputDTO(),
                singleComboPurchaseInputDTO(),
                sandwichAndBeveragePurchaseInputDTO(),
                comboAndSandwichAndBeveragePurchaseInputDTO()
        );
    }
}
