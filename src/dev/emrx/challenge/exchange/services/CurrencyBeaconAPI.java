package dev.emrx.challenge.exchange.services;

import com.google.gson.Gson;
import dev.emrx.challenge.exchange.services.dto.CBPairConversion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyBeaconAPI implements ExchangeService {

    public static final String ENTRY_POINT = "https://api.currencybeacon.com/v1/";
    private String tokenAPI;

    public CurrencyBeaconAPI(String tokenAPI) {
        this.tokenAPI = tokenAPI;
    }

    @Override
    public RateResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        URI uriExchangeRateAPI =
                URI.create(ENTRY_POINT
                        + "convert?from=" + baseCurrency
                        + "&to=" + targetCurrency
                        + "&amount=1"
                        + "&api_key=" + tokenAPI);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriExchangeRateAPI)
                .build();
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            CBPairConversion result = gson.fromJson(response.body(), CBPairConversion.class);
            return new RateResponse("Currency Beacon", result.value());
        } catch (Exception e) {
            throw new RuntimeException("No se encontro la divisa.");
        }
    }
}
