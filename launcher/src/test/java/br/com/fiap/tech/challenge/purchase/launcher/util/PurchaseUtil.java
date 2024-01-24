package br.com.fiap.tech.challenge.purchase.launcher.util;

import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static br.com.fiap.tech.challenge.purchase.launcher.util.JsonUtil.fromJsonString;
import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseUtil {

    public static ResponseList<PurchaseResponse> getAllPurchases(int port) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("http://localhost:%d/purchase", port)))
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
}
