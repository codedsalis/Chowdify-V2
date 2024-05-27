package com.codedsalis.chowdify.orderservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.ClientResponse;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(this.logRequest())
                .filter(this.logResponse());
    }

    public ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {} {}", clientRequest.method(), clientRequest.url(), clientRequest.body().toString());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}: {}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    public ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            return clientResponse.bodyToMono(String.class)
                    .flatMap(body -> {
                        log.info("Response Status: {}", clientResponse.statusCode());
                        clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{}: {}", name, value)));
                        log.info("Response Body: {}", body);
                        return Mono.just(ClientResponse.from(clientResponse).body(body).build());
                    });
        });
    }
}
