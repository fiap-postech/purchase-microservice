package br.com.fiap.tech.challenge.purchase.driven.purchase.paid.producer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String PURCHASE_PAID_TOPIC = "aws.resources.sns.purchase-paid.topic";

}
