package br.com.fiap.tech.challenge.purchase.driven.customer.removed.producer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String CUSTOMER_REMOVED_QUEUE = "aws.resources.sqs.customer-remove-data-response.queue";

}
