package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.purchase.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.payment.gateway.config.PaymentGatewayConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisConfiguration;
import br.com.fiap.tech.challenge.purchase.rest.config.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        RedisConfiguration.class,
        PaymentGatewayConfiguration.class
})
public class MainConfiguration {
}
