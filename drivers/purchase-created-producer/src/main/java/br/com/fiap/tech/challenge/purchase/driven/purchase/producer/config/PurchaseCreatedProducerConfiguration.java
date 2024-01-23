package br.com.fiap.tech.challenge.purchase.driven.purchase.producer.config;

import io.awspring.cloud.sns.core.TopicArnResolver;
import io.awspring.cloud.sns.core.TopicsListingTopicArnResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
@ComponentScan(basePackages = "br.com.fiap.tech.challenge.purchase.driven.purchase.producer")
public class PurchaseCreatedProducerConfiguration {

    @Bean
    public TopicArnResolver topicArnResolver(SnsClient snsClient) {
        return new TopicsListingTopicArnResolver(snsClient);
    }

}
