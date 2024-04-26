package dev.emrx.challenge.exchange.services;

public record RateResponse(
        String exchangeProvider,
        double value
) {}
