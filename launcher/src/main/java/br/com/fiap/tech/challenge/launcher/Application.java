package br.com.fiap.tech.challenge.launcher;

import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.client.MarketPaymentClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {MarketPaymentClient.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
