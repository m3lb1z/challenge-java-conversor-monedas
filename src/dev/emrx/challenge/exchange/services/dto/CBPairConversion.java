package dev.emrx.challenge.exchange.services.dto;

public record CBPairConversion(
        String date,
        String from,
        String to,
        double amount,
        double value
) {}
