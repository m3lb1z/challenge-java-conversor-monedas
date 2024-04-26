package dev.emrx.challenge.exchange.business;

import dev.emrx.challenge.exchange.calcule.CurrencyExchangeCalculator;
import dev.emrx.challenge.exchange.models.CurrencyRecord;

import java.util.Scanner;

public interface MenuService {

    CurrencyRecord processOperationExchange(Scanner keyboard, CurrencyExchangeCalculator calculator, int operation);
}
