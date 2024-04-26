package dev.emrx.challenge.exchange.calcule;

import dev.emrx.challenge.exchange.models.CurrencyRecord;
import dev.emrx.challenge.exchange.models.CurrencyPair;
import dev.emrx.challenge.exchange.services.CurrencyBeaconAPI;
import dev.emrx.challenge.exchange.services.ExchangeRateAPI;
import dev.emrx.challenge.exchange.services.ExchangeService;
import dev.emrx.challenge.exchange.services.RateResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeCalculator {

    private List<ExchangeService> providers;
    private List<CurrencyRecord> exchangeHistory;

    public CurrencyExchangeCalculator() {
        exchangeHistory = new ArrayList<>();
        providers = new ArrayList<>();
        providers.add(new ExchangeRateAPI("619a9a9fe75fc8fea3073e51"));
        providers.add(new CurrencyBeaconAPI("oKC1gPIFWxy5FZFX0NUEm5Q8c0Sq4paI"));
    }

    public CurrencyExchangeCalculator(List<ExchangeService> providers) {
        exchangeHistory = new ArrayList<>();
        this.providers = providers;
    }

    public RateResponse findBestRate(CurrencyPair currencyPair) {
        String bestExchangeProvider = "";
        double bestRate = Double.MIN_VALUE;

        for (var provider : providers) {
            RateResponse rateResponse = provider.getExchangeRate(currencyPair.baseCode(), currencyPair.targetCode());
            if (rateResponse.value() > bestRate) {
                bestExchangeProvider = rateResponse.exchangeProvider();
                bestRate = rateResponse.value();
            }
        }

        return new RateResponse(bestExchangeProvider, bestRate);
    }

    public double calculeExchangeRate(double currentValue, double rate) {
        return currentValue * rate;
    }

    public void processExchangeRate(double currentValue, CurrencyPair currencyPair) {
        RateResponse rate = findBestRate(currencyPair);
        double targetValue = calculeExchangeRate(currentValue, rate.value());

        exchangeHistory.add(
                new CurrencyRecord(LocalDateTime.now(), currentValue, currencyPair, targetValue, rate.value(), rate.exchangeProvider()));
    }

    public void showTransactionHistory() {
        System.out.println("\nHistorial de Conversor de Moneda");
        getExchangeHistory().forEach(System.out::println);
    }

    public List<CurrencyRecord> getExchangeHistory() {
        return exchangeHistory;
    }

    public CurrencyRecord getLastCurrencyExchangeRate() {
        return exchangeHistory.get(exchangeHistory.size() - 1);
    }
}
