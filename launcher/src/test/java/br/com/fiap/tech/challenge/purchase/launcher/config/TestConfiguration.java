package br.com.fiap.tech.challenge.purchase.launcher.config;


import br.com.fiap.tech.challenge.purchase.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.config.PurchaseCreatedProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.payment.done.consumer.config.PaymentDoneConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.rest.config.RestConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "br.com.fiap.tech.challenge")
@EntityScan(basePackages = "br.com.fiap.tech.challenge")
@Import(
        {
                RestConfiguration.class,
                MySQLConfiguration.class,
                PaymentDoneConsumerConfiguration.class,
                PurchaseCreatedProducerConfiguration.class
        }
)
public class TestConfiguration {
}
