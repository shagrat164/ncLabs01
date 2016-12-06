package ru.solpro.view;

import ru.solpro.controller.ElectricTrainController;
import ru.solpro.controller.RouteController;
import ru.solpro.controller.StationController;
import ru.solpro.model.ElectricTrain;
import ru.solpro.model.Route;
import ru.solpro.model.Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Protsvetov Danila
 */
public class SearchCommand implements Command {
    @Override
    public boolean execute(String[] args) throws IOException {
        if (args == null || args.length < 1 || args.length > 1) {
            printHelp();
            return true;
        }
        switch (args[0].toUpperCase()) {
            case "STATION":
                searchStation();
                break;
            case "ROUTE":
                searchRoute();
                break;
            case "TRAIN":
                searchTrain();
                break;
            default:
                printHelp();
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println("Поддерживаются символы * и ?");
        System.out.println("Список параметров команды:");
        System.out.println("STATION - поиск станции.");
        System.out.println("ROUTE - поиск маршрута.");
        System.out.println("TRAIN - поиск поезда по его номеру.");
    }

    @Override
    public String getName() {
        return "SEARCH";
    }

    @Override
    public String getDescription() {
        return "Поиск данных в системе.";
    }

    private void searchStation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StationController stationController = StationController.getInstance();

        System.out.print("\tВведите строку для поиска: ");
        String string = reader.readLine();
        ArrayList<Station> result = stationController.search(string);
        System.out.println("\tРезультат поиска:");
        if (result.isEmpty()) {
            System.out.println("\tНичего не найдено.");
            return;
        }
        for (Station station : result) {
            System.out.println("\t[" + station.getId() + "] " + station);
        }
    }

    private void searchRoute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RouteController routeController = RouteController.getInstance();

        System.out.print("\tВведите строку для поиска: ");
        String string = reader.readLine();
        ArrayList<Route> result = routeController.search(string);
        System.out.println("\tРезультат поиска:");
        if (result.isEmpty()) {
            System.out.println("\tНичего не найдено.");
            return;
        }
        for (Route route : result) {
            System.out.println("\t[" + route.getId() + "] " + route);
        }
    }

    private void searchTrain() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ElectricTrainController electricTrainController = ElectricTrainController.getInstance();

        System.out.print("\tВведите номер поезда: ");
        Integer integer = Integer.parseInt(reader.readLine());
        ElectricTrain result = electricTrainController.search(integer);
        if (result == null) {
            System.out.println("\tНичего не найдено.");
            return;
        }
        System.out.println("\t" + result);
    }
}
