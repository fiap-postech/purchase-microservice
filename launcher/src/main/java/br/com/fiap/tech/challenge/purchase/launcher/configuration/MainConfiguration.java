package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.config.PurchaseCreatedProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.paid.producer.config.PurchasePaidProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.status.producer.config.PurchaseStatusProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.cart.closed.consumer.config.CartClosedConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.payment.consumer.config.PaymentDoneConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.rest.config.RestConfiguration;
import io.awspring.cloud.sns.core.TopicArnResolver;
import io.awspring.cloud.sns.core.TopicsListingTopicArnResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        PaymentDoneConsumerConfiguration.class,
        PurchaseCreatedProducerConfiguration.class,
        PurchasePaidProducerConfiguration.class,
        PurchaseStatusProducerConfiguration.class,
        CartClosedConsumerConfiguration.class,
})
public class MainConfiguration {

    @Bean
    public TopicArnResolver topicArnResolver(SnsClient snsClient) {
        return new TopicsListingTopicArnResolver(snsClient);
    }

}
