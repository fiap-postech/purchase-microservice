package br.com.fiap.tech.challenge.purchase.driver.payment.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String PAYMENT_DONE_QUEUE = "aws.resources.sqs.payment-done.queue";

}
