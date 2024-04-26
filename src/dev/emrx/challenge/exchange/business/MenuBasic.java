package dev.emrx.challenge.exchange.business;

import dev.emrx.challenge.exchange.calcule.CurrencyExchangeCalculator;
import dev.emrx.challenge.exchange.models.CurrencyPair;
import dev.emrx.challenge.exchange.models.CurrencyRecord;

import java.util.Scanner;

public class MenuBasic implements MenuService {
    @Override
    public CurrencyRecord processOperationExchange(Scanner keyboard, CurrencyExchangeCalculator calculator, int operation) {
        CurrencyPair currencyPair = selectCurrency(operation);

        System.out.println("Escriba el valor que deseas convertir: ");
        double baseValue = keyboard.nextDouble();
        calculator.processExchangeRate(baseValue, currencyPair);
        CurrencyRecord currency = calculator.getLastCurrencyExchangeRate();

        return currency;
    }

    private CurrencyPair selectCurrency(int operation) {
        CurrencyPair current = new CurrencyPair("USD", "USD");

        switch (operation) {
            case 1:
                current = new CurrencyPair("USD", "ARS");
                break;
            case 2:
                current = new CurrencyPair("ARS", "USD");
                break;
            case 3:
                current = new CurrencyPair("USD", "BRL");
                break;
            case 4:
                current = new CurrencyPair("BRL", "USD");
                break;
            case 5:
                current = new CurrencyPair("USD", "COP");
                break;
            case 6:
                current = new CurrencyPair("COP", "USD");
                break;
        }

        return current;
    }
}
