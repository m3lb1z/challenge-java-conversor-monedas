package dev.emrx.challenge.exchange.services.dto;

public record ERPairConversion(
        String result,
        String base_code,
        String target_code,
        double conversion_rate) {}