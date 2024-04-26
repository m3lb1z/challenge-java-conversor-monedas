package dev.emrx.challenge.exchange.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record CurrencyRecord(
    LocalDateTime timestamp,
    double baseValue,
    CurrencyPair currencyPair,
    double targetValue,
    double rate,
    String exchangeProvider) {

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "[%s] - [%s] %.2f [%s] ==> %.2f [%s]"
                .formatted(timestamp.format(formatter), exchangeProvider, baseValue, currencyPair.baseCode(), targetValue, currencyPair.targetCode());
    }
}
