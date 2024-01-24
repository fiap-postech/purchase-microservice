package br.com.fiap.tech.challenge.purchase.launcher.rest;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.launcher.config.TestConfiguration;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
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
import static br.com.fiap.tech.challenge.purchase.launcher.util.PurchaseUtil.getAllPurchases;
import static br.com.fiap.tech.challenge.purchase.launcher.util.QueueUtil.sendMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({ "test" })
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class CreatePurchaseIT {

    private static final Logger LOGGER = getLogger(CreatePurchaseIT.class);


    private static final int LOCAL_PORT = 8691;

    private String queueName;

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @Autowired
    private Environment env;

    @Autowired
    private SqsTemplate sqsTemplate;

    @BeforeAll
    static void beforeAll() {
        LOCAL_STACK_CONTAINER.followOutput(new Slf4jLogConsumer(LOGGER));

        System.setProperty("spring.datasource.url", DATABASE.getJdbcUrl());
        System.setProperty("spring.datasource.username", DATABASE.getUsername());
        System.setProperty("spring.datasource.password", DATABASE.getPassword());

        System.setProperty("server.port", String.valueOf(LOCAL_PORT));
    }

    @BeforeEach
    void setup() {
        queueName = env.getProperty("aws.resources.sqs.payment-done.queue");
    }

    @ParameterizedTest
    @MethodSource("getCreateOptions")
    void testCreatePurchase(Model<PurchaseInputDTO> model) {
        var message = create(model);

        var responseBeforeTest = getAllPurchases(LOCAL_PORT);
        var countBeforeTest = responseBeforeTest.totalElements();

        sendMessage(sqsTemplate, queueName, message);

        given()
                .await()
                .pollInterval(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    var response = getAllPurchases(LOCAL_PORT);

                    assertThat(response.totalElements()).isEqualTo(countBeforeTest + 1);
                });
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
