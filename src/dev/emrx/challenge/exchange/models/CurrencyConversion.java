package dev.emrx.challenge.exchange.models;

import java.time.LocalDateTime;

public record CurrencyConversion(
    LocalDateTime timestamp,
    String baseCurrency,
    double baseValue,
    String targetCurrency,
    double targetValue,
    double rate) {

    @Override
    public String toString() {
        return "[%s] %.2f [%s] ==> %.2f [%s]"
                .formatted(timestamp.toString(), baseValue, baseCurrency, targetValue, targetCurrency);
    }
}
