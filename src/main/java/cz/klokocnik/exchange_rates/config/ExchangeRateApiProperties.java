package cz.klokocnik.exchange_rates.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "exchange.rate.api")
@Getter
@Setter
@Slf4j
public class ExchangeRateApiProperties {

    private String url;
    private String apiKey;

}
