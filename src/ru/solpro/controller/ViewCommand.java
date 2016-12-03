package ru.solpro.controller;

import ru.solpro.model.*;

public class ViewCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        if (args == null) {
            System.out.println("Расписание на текущий день.");
            return true;
        }
        for (String arg : args) {
            switch (arg.toUpperCase()) {
                case "STATIONS":
                    StationController stationController = StationController.getInstance();
                    if (stationController.get().isEmpty()) {
                        System.out.println("Не определено ни одной станции.");
                        break;
                    }
                    for (Station station : stationController.get()) {
                        System.out.println(station.getId() + ". " + station);
                    }
                    break;
                case "ROUTES":
                    RouteController routeController = RouteController.getInstance();
                    if (routeController.get().isEmpty()) {
                        System.out.println("Не определено ни одного маршрута.");
                        break;
                    }
                    for (Route route : routeController.get()) {
                        System.out.println(route.getId() + ". " + route);
                    }
                    break;
                case "TRAINS":
                    ElectricTrainController electricTrainController = ElectricTrainController.getInstance();
                    if (electricTrainController.get().isEmpty()) {
                        System.out.println("Не определено ни одного поезда.");
                        break;
                    }
                    for (ElectricTrain electricTrain : electricTrainController.get()) {
                        System.out.println(electricTrain);
                    }
                    break;
                default:
                    System.out.print("Неверный аргумент у команды. ");
                    printHelp();
            }
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println("Список параметров команды:");
        System.out.println("STATIONS - выводит список станций.");
    }

    @Override
    public String getName() {
        return "VIEW";
    }

    @Override
    public String getDescription() {
        return "Отображение расписания электропоездов.";
    }
}
