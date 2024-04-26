package dev.emrx.challenge.exchange.business;

import dev.emrx.challenge.exchange.calcule.CurrencyExchangeCalculator;
import dev.emrx.challenge.exchange.models.CurrencyPair;
import dev.emrx.challenge.exchange.models.CurrencyRecord;
import dev.emrx.challenge.exchange.models.CurrencyType;
import dev.emrx.challenge.exchange.services.CurrencyBeaconAPI;
import dev.emrx.challenge.exchange.services.ExchangeRateAPI;
import dev.emrx.challenge.exchange.services.ExchangeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static final int MENU_BASIC = 1;
    public static final int MENU_ADVANCED = 2;
    private MenuService menuService;
    private MenuService menuBasic;
    private MenuService menuAdvanced;
    private List<CurrencyType> listCurrencyTypes;
    private List<CurrencyType> favorityCurrencyTypes;
    private List<ExchangeService> providers;
    private CurrencyExchangeCalculator calculator;

    public Menu() {
        ExchangeRateAPI mainExchange = new ExchangeRateAPI("619a9a9fe75fc8fea3073e51");
        listCurrencyTypes = mainExchange.getListCodes();
        if(listCurrencyTypes == null)
            listCurrencyTypes = new ArrayList<>();

        providers = new ArrayList<>();
        providers.add(mainExchange);
        providers.add(new CurrencyBeaconAPI("oKC1gPIFWxy5FZFX0NUEm5Q8c0Sq4paI"));
        calculator = new CurrencyExchangeCalculator(providers);

        menuBasic = new MenuBasic();
        menuAdvanced = new MenuAdvanced();

        pressMenuBasic();
    }

    public boolean proccesOperation(Scanner keyboard) {
        int operation = keyboard.nextInt();
        if(operation == 9) return false;

        if(operation >= 1 && operation <= 6) {
            CurrencyRecord currency = menuService.processOperationExchange(keyboard, calculator, operation);
            System.out.println(currency.showTransaction());
        } else if(operation == 7) {
            calculator.showTransactionHistory();
        } else {
            showSettings(keyboard);
        }

        return true;
    }

    public void showMenu() {
        if (menuService instanceof MenuBasic) {
            showMenuBasic();
        } else {
            showMenuAdvanced();
        }
    }

    private void showSettings(Scanner keyboard) {
        System.out.println("""
                1) Menu basico
                2) Menu Avanzado
                3) Cambiar Divisas de Acceso Directo
                Elija una opción válida: 
                """);
        int operationSettings = keyboard.nextInt();

        switch (operationSettings) {
            case 1:
                pressMenuBasic();
                break;
            case 2:
                pressMenuAdvanced();
                break;
            case 3:
                break;
        }
    }

    private void pressMenuBasic() {
        menuService = menuBasic;
    }

    private void pressMenuAdvanced() {
        menuService = menuAdvanced;
    }

    private void showMenuBasic() {
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

    private void showMenuAdvanced() {
        // lista de divisas
        for (int indice = 0; indice < listCurrencyTypes.size(); indice++) {
            System.out.print(listCurrencyTypes.get(indice));
            if((indice + 1) % 6 == 0)
                System.out.println();
        }
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
