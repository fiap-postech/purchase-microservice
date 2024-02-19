package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.config.PurchaseCreatedProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.cart.closed.consumer.config.CartClosedConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.payment.consumer.config.PaymentDoneConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.rest.config.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        PaymentDoneConsumerConfiguration.class,
        PurchaseCreatedProducerConfiguration.class,
        CartClosedConsumerConfiguration.class,
})
public class MainConfiguration {
}
