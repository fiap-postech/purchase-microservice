package br.com.fiap.tech.challenge.purchase.launcher.util;

import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.driver.cart.closed.consumer.config.EnvironmentProperties;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static br.com.fiap.tech.challenge.purchase.driver.cart.closed.consumer.config.EnvironmentProperties.CART_CLOSED_QUEUE;
import static br.com.fiap.tech.challenge.purchase.driver.payment.consumer.config.EnvironmentProperties.PAYMENT_DONE_QUEUE;
import static br.com.fiap.tech.challenge.purchase.launcher.util.ConfigurationOverrides.LOCAL_PORT;
import static br.com.fiap.tech.challenge.purchase.launcher.util.JsonUtil.fromJsonString;
import static br.com.fiap.tech.challenge.purchase.launcher.util.QueueUtil.sendMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseUtil {

    public static String getPaymentDoneQueueName(Environment env) {
        return env.getProperty(PAYMENT_DONE_QUEUE);
    }

    public static String getCartClosedQueueName(Environment env) {
        return env.getProperty(CART_CLOSED_QUEUE);
    }

    public static ResponseList<PurchaseResponse> getAllPurchases() {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("http://localhost:%d/purchase", LOCAL_PORT)))
                    .headers("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .GET()
                    .build();

            var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            assertThat(response.statusCode()).isEqualTo(200);

            var typeRef = new TypeReference<ResponseList<PurchaseResponse>>() {};
            return fromJsonString(response.body(), typeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createNewPurchase(SqsTemplate template, String queueName, PurchaseInputDTO inputDTO) {
        var responseBeforeTest = getAllPurchases();
        var countBeforeTest = responseBeforeTest.totalElements();

        sendMessage(template, queueName, inputDTO);

        given()
                .await()
                .pollInterval(Duration.ofSeconds(2))
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    var response = getAllPurchases();

                    assertThat(response.totalElements()).isEqualTo(countBeforeTest + 1);
                });
    }
}
