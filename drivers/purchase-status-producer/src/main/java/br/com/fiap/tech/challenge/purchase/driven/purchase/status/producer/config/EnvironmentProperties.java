package br.com.fiap.tech.challenge.purchase.driven.purchase.status.producer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String PURCHASE_NOTIFICATION_TOPIC = "aws.resources.sns.purchase-notification.topic";

}
