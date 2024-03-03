package br.com.fiap.tech.challenge.purchase.driver.cart.closed.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String CART_CLOSED_QUEUE = "aws.resources.sqs.cart-closed.queue";

}
