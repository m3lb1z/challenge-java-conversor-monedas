package dev.emrx.challenge.exchange.services;

public interface ExchangeService {

    RateResponse getExchangeRate(String baseCurrency, String targetCurrency);


}
