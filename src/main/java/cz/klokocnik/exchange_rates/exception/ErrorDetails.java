package cz.klokocnik.exchange_rates.exception;

import java.time.Instant;

public record ErrorDetails(
        Instant timestamp,
        String message
) {
}
