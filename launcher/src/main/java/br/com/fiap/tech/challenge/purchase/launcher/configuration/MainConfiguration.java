package br.com.fiap.tech.challenge.purchase.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.driven.customer.data.removal.consumer.config.CustomerDataRemovalConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.customer.removed.producer.config.CustomerRemovedProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.config.PurchaseCreatedProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.paid.producer.config.PurchasePaidProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driven.purchase.status.producer.config.PurchaseStatusProducerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.cart.closed.consumer.config.CartClosedConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.payment.created.consumer.config.PaymentCreatedConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.driver.payment.done.consumer.config.PaymentDoneConsumerConfiguration;
import br.com.fiap.tech.challenge.purchase.rest.config.RestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sns.core.TopicArnResolver;
import io.awspring.cloud.sns.core.TopicsListingTopicArnResolver;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        PaymentCreatedConsumerConfiguration.class,
        PaymentDoneConsumerConfiguration.class,
        PurchaseCreatedProducerConfiguration.class,
        PurchasePaidProducerConfiguration.class,
        PurchaseStatusProducerConfiguration.class,
        CartClosedConsumerConfiguration.class,
        CustomerDataRemovalConsumerConfiguration.class,
        CustomerRemovedProducerConfiguration.class
})
public class MainConfiguration {

    @Bean
    public TopicArnResolver topicArnResolver(SnsClient snsClient) {
        return new TopicsListingTopicArnResolver(snsClient);
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient client, ObjectMapper mapper) {
        return SqsTemplate.builder()
                .sqsAsyncClient(client)
                .configureDefaultConverter(converter -> {
                    converter.setObjectMapper(mapper);
                    converter.setPayloadTypeHeaderValueFunction(m -> null);
                })
                .build();
    }

}
