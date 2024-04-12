package dev.emrx.challenge.exchange.models;

public record MoneyPairRate(
    String result,
    String base_code,
    String target_code,
    double conversion_rate) {}
