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

    public String showTransaction() {
        return "El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s] de la casa de cambio \'%s\' "
                .formatted(baseValue(), currencyPair().baseCode(), targetValue(), currencyPair().targetCode(), exchangeProvider());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "[%s] - [%s] %.2f [%s] ==> %.2f [%s]"
                .formatted(timestamp.format(formatter), exchangeProvider, baseValue, currencyPair.baseCode(), targetValue, currencyPair.targetCode());
    }
}
