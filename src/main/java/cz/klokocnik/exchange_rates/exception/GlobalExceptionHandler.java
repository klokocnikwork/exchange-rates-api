package cz.klokocnik.exchange_rates.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(ExchangeRateApiFetchException.class)
    public ErrorDetails exchangeRateApiFetchExceptionHandling() {
        return new ErrorDetails(Instant.now(), HttpStatus.BAD_GATEWAY.getReasonPhrase());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExchangeRateException.class)
    public ErrorDetails exchangeRateExceptionHandling() {
        return new ErrorDetails(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
