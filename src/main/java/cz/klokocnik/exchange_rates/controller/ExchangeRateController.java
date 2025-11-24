package cz.klokocnik.exchange_rates.controller;

import cz.klokocnik.exchange_rates.dto.ExchangeRateDto;
import cz.klokocnik.exchange_rates.mapper.ExchangeRateMapper;
import cz.klokocnik.exchange_rates.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/exchangeRates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateController(ExchangeRateService exchangeRateService, ExchangeRateMapper exchangeRateMapper) {
        this.exchangeRateService = exchangeRateService;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    @GetMapping
    public ResponseEntity<List<ExchangeRateDto>> getAllExchangeRates(@RequestParam Boolean useDb) {
        return ResponseEntity.ok(exchangeRateMapper.toDto(exchangeRateService.getAllExchangeRates(useDb)));

    }
}
