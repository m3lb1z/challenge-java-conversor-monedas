package dev.emrx.challenge.exchange.services;

import com.google.gson.Gson;
import dev.emrx.challenge.exchange.models.MoneyPairRate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {

    public static final String ENTRY_POINT = "https://v6.exchangerate-api.com/v6/";
    private String tokenAPI;

    public ExchangeRateAPI(String tokenAPI) {
        this.tokenAPI = tokenAPI;
    }
    public double calculeToAnotherCurrency(String base, String target, double value) {
        URI uriExchangeRateAPI = URI.create(ENTRY_POINT + tokenAPI + "/pair/" + base + "/" + target);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriExchangeRateAPI)
                .build();

        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            MoneyPairRate rate = gson.fromJson(response.body(), MoneyPairRate.class);
            return value * rate.conversion_rate();
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la divisa.");
        }
    }

    public double obtainCurrencyRate(String base, String target) {
        URI uriExchangeRateAPI = URI.create(ENTRY_POINT + tokenAPI + "/pair/" + base + "/" + target);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriExchangeRateAPI)
                .build();
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            MoneyPairRate result = gson.fromJson(response.body(), MoneyPairRate.class);
            return result.conversion_rate();
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la divisa.");
        }
    }

}
