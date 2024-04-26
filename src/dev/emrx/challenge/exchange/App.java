package dev.emrx.challenge.exchange;

import dev.emrx.challenge.exchange.calcule.CurrencyExchangeCalculator;
import dev.emrx.challenge.exchange.models.CurrencyRecord;
import dev.emrx.challenge.exchange.models.CurrencyPair;

import java.util.Scanner;

public class App {

    public static CurrencyExchangeCalculator calculator = new CurrencyExchangeCalculator();
    public static int OPERATION_EXIT = 9;
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int operation = -1;

        while (operation != OPERATION_EXIT) {
            menu();
            operation = keyboard.nextInt();
            if(operation == OPERATION_EXIT) continue;

            if(operation >= 1 && operation <= 6) {
                System.out.println("Escriba el valor que deseas convertir: ");
                double baseValue = keyboard.nextDouble();
                CurrencyPair currencyPair = processOperationExchange(operation);
                calculator.processExchangeRate(baseValue, currencyPair);
                CurrencyRecord current = calculator.getLastCurrencyExchangeRate();

                System.out.println(
                        "El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s] de la casa de cambio \'%s\' "
                                .formatted(current.baseValue(),
                                        current.currencyPair().baseCode(),
                                        current.targetValue(),
                                        current.currencyPair().targetCode(),
                                        current.exchangeProvider())
                );
            } else if(operation == 7) {
                calculator.showTransactionHistory();
            } else {
                System.out.println("Configurares");
            }
        }
        System.out.println("Finalizo: el programa de Conversor de Moneda");
    }

    public static CurrencyPair processOperationExchange(int operation) {
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
            7) Historial
            8) Configuraciones
            9) Salir
            Elija una opción válida:
            *******************************************************************
            """);
    }
    public static void menuAdvanced() {
        System.out.println("""
            
            *******************************************************************
            Sea bienvenido/a al Conversor de Moneda =]
            
            1) Cambiar divisas
            7) Historial
            8) Configuraciones
            9) Salir
            Elija una opción válida:
            *******************************************************************
            """);
    }
}
