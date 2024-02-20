package br.com.fiap.tech.challenge.purchase.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "br.com.fiap.tech.challenge.purchase.rest",
        "br.com.fiap.tech.challenge.rest"
})
public class RestConfiguration {


}
