package dev.emrx.challenge.exchange;

import dev.emrx.challenge.exchange.calcule.CurrencyCalculator;
import dev.emrx.challenge.exchange.models.CurrencyConversion;
import dev.emrx.challenge.exchange.models.CurrencyType;
import dev.emrx.challenge.exchange.services.ExchangeRateAPI;

import java.util.Scanner;

public class App {

    public static final String TOKEN_API = "619a9a9fe75fc8fea3073e51";
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        CurrencyCalculator calculator = new CurrencyCalculator();
        int operation = -1;
        double baseValue = 0.0;

        while (operation != 7) {
            menu();
            operation = keyboard.nextInt();
            if(operation == 7) continue;

            System.out.println("Escriba el valor que deseas convertir: ");
            baseValue = keyboard.nextDouble();

            if(processOperation(calculator, operation, baseValue)) {
                CurrencyConversion current = calculator.getLastCurrencyExchangeRate();
                System.out.println(
                    "El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]"
                    .formatted(current.baseValue(),
                                current.baseCurrency(),
                                current.targetValue(),
                                current.targetCurrency())
                );
                System.out.println("\nHistorial de Conversor de Moneda");
                calculator.getExchangeHistory().forEach(System.out::println);
            }
        }
        System.out.println("Finalizo: el programa de Conversor de Moneda");
    }

    public static boolean processOperation(CurrencyCalculator calculator, int operation, double baseValue) {
        ExchangeRateAPI exchange = new ExchangeRateAPI(TOKEN_API);
        CurrencyType baseCurrency = CurrencyType.USD;
        CurrencyType targetCurrency = CurrencyType.USD;

        switch (operation) {
            case 1:
                baseCurrency = CurrencyType.USD;
                targetCurrency = CurrencyType.ARS;
                break;
            case 2:
                baseCurrency = CurrencyType.ARS;
                targetCurrency = CurrencyType.USD;
                break;
            case 3:
                baseCurrency = CurrencyType.USD;
                targetCurrency = CurrencyType.BRL;
                break;
            case 4:
                baseCurrency = CurrencyType.BRL;
                targetCurrency = CurrencyType.USD;
                break;
            case 5:
                baseCurrency = CurrencyType.USD;
                targetCurrency = CurrencyType.COP;
                break;
            case 6:
                baseCurrency = CurrencyType.COP;
                targetCurrency = CurrencyType.USD;
                break;
            default:
                return false;
        }

        calculator.calculeCurrencyExchangeRate(
                baseCurrency,
                baseValue,
                targetCurrency,
                exchange.obtainCurrencyRate(baseCurrency.name(), targetCurrency.name()));
        return true;
    }
    public static void menu() {
        System.out.println("""
            
            *******************************************************************
            Sea bienvenido/a al Conversor de Moneda =]
            
            1) Dólar =>> Peso argentino
            2) Peso argentino =>> Dólar
            3) Dólar =>> Real brasileño
            4) Real brasileño =>> Dólar
            5) Dólar =>> Peso colombiano
            6) Peso colombinao =>> Dólar
            7) Salir
            Elija una opción válida:
            *******************************************************************
            """);
    }
}
