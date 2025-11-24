package cz.klokocnik.exchange_rates.service;

import cz.klokocnik.exchange_rates.config.ExchangeRateApiProperties;
import cz.klokocnik.exchange_rates.dto.ExchangeRateDto;
import cz.klokocnik.exchange_rates.entity.ExchangeRate;
import cz.klokocnik.exchange_rates.exception.ExchangeRateApiFetchException;
import cz.klokocnik.exchange_rates.exception.ExchangeRateException;
import cz.klokocnik.exchange_rates.mapper.ExchangeRateMapper;
import cz.klokocnik.exchange_rates.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class ExchangeRateService {
    private final RestClient restClient = RestClient.create();
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateApiProperties exchangeRateApiProperties;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, ExchangeRateApiProperties exchangeRateApiProperties) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateApiProperties = exchangeRateApiProperties;
    }

    /**
     * @param useDb if true, returns data directly from db. Else it does the fetch from external api and save it to db
     * @return most recent exchange rates
     */
    public List<ExchangeRate> getAllExchangeRates(Boolean useDb) {
        if (useDb) {
            return exchangeRateRepository.findMostRecentExchangeRates();
        }
        final var exchangeRates = exchangeRateMapper.toEntity(fetchExchangeRates());
        exchangeRateRepository.saveAll(exchangeRates);
        return exchangeRates;
    }

    public List<ExchangeRateDto> fetchExchangeRates() {
        try {
            final var url = UriComponentsBuilder
                    .fromUri(new URI(exchangeRateApiProperties.getUrl()))
                    .queryParam("web-api-key", exchangeRateApiProperties.getApiKey())
                    .toUriString();

            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .onStatus(status -> status.value() != HttpStatus.OK.value(), (request, response) -> {
                        log.error("Fetching of exchange rates failed. Response: {}", new String(response.getBody().readAllBytes()));
                        throw new ExchangeRateApiFetchException(response.getStatusText());
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

        } catch (RestClientException e) {
            log.error("Unexpected error while trying to fetch data.", e);
            throw new ExchangeRateException();


        } catch (URISyntaxException e) {
            log.error("Invalid uri for exchange api: {}", exchangeRateApiProperties.getUrl());
            throw new ExchangeRateException();

        }
    }
}
