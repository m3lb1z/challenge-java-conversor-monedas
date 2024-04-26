package dev.emrx.challenge.exchange.models;

public record CurrencyType(
    String code,
    String name) {

    @Override
    public String toString() {
        return "%s : %-25s".formatted(code, name.substring(0,name.length() > 20 ? 20 : name.length()));
    }
}
