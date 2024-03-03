package br.com.fiap.tech.challenge.purchase.driver.payment.created.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String PAYMENT_CREATED_QUEUE = "aws.resources.sqs.payment-created.queue";

}
