package dev.emrx.challenge.exchange.services;

import com.google.gson.Gson;
import dev.emrx.challenge.exchange.services.dto.ERPairConversion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI implements ExchangeService {

    public static final String ENTRY_POINT = "https://v6.exchangerate-api.com/v6/";
    private String tokenAPI;

    public ExchangeRateAPI(String tokenAPI) {
        this.tokenAPI = tokenAPI;
    }

    @Override
    public RateResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        URI uriExchangeRateAPI = URI.create(ENTRY_POINT + tokenAPI + "/pair/" + baseCurrency + "/" + targetCurrency);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriExchangeRateAPI)
                .build();
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            ERPairConversion result = gson.fromJson(response.body(), ERPairConversion.class);
            return new RateResponse("Exchange Rate", result.conversion_rate());
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la divisa.");
        }
    }

}
