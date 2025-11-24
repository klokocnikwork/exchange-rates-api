package cz.klokocnik.exchange_rates.mapper;

import cz.klokocnik.exchange_rates.dto.ExchangeRateDto;
import cz.klokocnik.exchange_rates.entity.ExchangeRate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {


    ExchangeRate toEntity(ExchangeRateDto exchangeRateDto);

    ExchangeRateDto toDto(ExchangeRate exchangeRate);

    List<ExchangeRateDto> toDto (List<ExchangeRate> exchangeRates);

    List<ExchangeRate> toEntity (List<ExchangeRateDto> exchangeRates);

}
