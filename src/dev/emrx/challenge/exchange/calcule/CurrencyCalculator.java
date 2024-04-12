package dev.emrx.challenge.exchange.calcule;

import dev.emrx.challenge.exchange.models.CurrencyConversion;
import dev.emrx.challenge.exchange.models.CurrencyType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCalculator {

    private List<CurrencyConversion> exchangeHistory;

    public CurrencyCalculator() {
        exchangeHistory = new ArrayList<>();
    }

    public void calculeCurrencyExchangeRate(CurrencyType baseCurrency, double baseValue, CurrencyType targetCurrency, double rate) {
        double valueTarget = baseValue * rate;
        exchangeHistory.add(
                new CurrencyConversion(
                        LocalDateTime.now(),
                        baseCurrency.name(),
                        baseValue,
                        targetCurrency.name(),
                        valueTarget,
                        rate));
    }

    public CurrencyConversion getLastCurrencyExchangeRate() {
        return exchangeHistory.get(exchangeHistory.size() - 1);
    }

    public List<CurrencyConversion> getExchangeHistory() {
        return exchangeHistory;
    }
}
