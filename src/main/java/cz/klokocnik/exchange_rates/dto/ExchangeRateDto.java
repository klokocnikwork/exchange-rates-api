package cz.klokocnik.exchange_rates.dto;

import java.time.LocalDateTime;

public record ExchangeRateDto(
        String shortName,
        LocalDateTime validFrom,
        String name,
        String country,
        Double move,
        Double amount,
        Double valBuy,
        Double valSell,
        Double valMid,
        Double currBuy,
        Double currSell,
        Double currMid,
        Double version,
        Double cnbMid,
        Double ecbMid) {
}
