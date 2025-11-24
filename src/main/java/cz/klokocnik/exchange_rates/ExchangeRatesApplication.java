package cz.klokocnik.exchange_rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ExchangeRatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesApplication.class, args);
    }

}
