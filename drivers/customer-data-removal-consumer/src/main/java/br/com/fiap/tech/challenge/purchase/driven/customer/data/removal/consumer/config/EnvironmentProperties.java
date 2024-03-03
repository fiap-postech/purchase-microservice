package br.com.fiap.tech.challenge.purchase.driven.customer.data.removal.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String CUSTOMER_DATA_REMOVAL_QUEUE = "aws.resources.sqs.customer-remove-data.queue";

}
