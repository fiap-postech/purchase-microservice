package br.com.fiap.tech.challenge.purchase.driven.purchase.created.producer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String PURCHASE_CREATED_TOPIC = "aws.resources.sns.purchase-created.topic";

}
