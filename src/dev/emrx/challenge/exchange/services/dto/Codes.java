package dev.emrx.challenge.exchange.services.dto;

import dev.emrx.challenge.exchange.models.CurrencyType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record Codes(
    String result,
    String documentation,
    String terms_of_use,
    String[][] supported_codes) {}
