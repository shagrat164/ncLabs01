package ru.solpro.controller;

import ru.solpro.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ViewCommand implements Command {
    @Override
    public boolean execute(String[] args) throws SystemException, IOException{
        if (args == null) {
            printHelp();
            return true;
        }
        for (int i = 0; i < args.length; i++) {
            switch (args[i].toUpperCase()) {
                case "STATIONS":
                    viewStations();
                    break;
                case "ROUTES":
                    viewRoutes();
                    break;
                case "TRAINS":
                    viewTrains();
                    break;
                case "SCHEDULE":
                    if ((args.length == 2) && isNumber(args[i+1])) {
                        viewSchedule(Integer.parseInt(args[i+1]));
                        return true;
                    } else {
                        viewSchedule();
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

    /**
     * Вывод списка станций в системе
     */
    private void viewStations() {
        StationController stationController = StationController.getInstance();
        if (stationController.get().isEmpty()) {
            System.out.println("Не определено ни одной станции.");
            return;
        }
        for (Station station : stationController.get()) {
            System.out.println(station.getId() + ". " + station);
        }
    }

    /**
     * Вывод списка маршрутов в системе
     */
    private void viewRoutes() {
        RouteController routeController = RouteController.getInstance();
        if (routeController.get().isEmpty()) {
            System.out.println("Не определено ни одного маршрута.");
            return;
        }
        for (Route route : routeController.get()) {
            System.out.println(route.getId() + ". " + route);
        }
    }

    /**
     * Вывод списка поездов в системе
     */
    private void viewTrains() {
        ElectricTrainController electricTrainController = ElectricTrainController.getInstance();
        if (electricTrainController.get().isEmpty()) {
            System.out.println("Не определено ни одного поезда.");
            return;
        }
        for (ElectricTrain electricTrain : electricTrainController.get()) {
            System.out.println(electricTrain);
        }
    }

    /**
     * Расписание поездов за ближайшие 24 часа
     */
    private void viewSchedule() {
        ElectricTrainController electricTrainController = ElectricTrainController.getInstance();
        if (electricTrainController.get().isEmpty()) {
            System.out.println("Не определено ни одного поезда.");
            return;
        }
        LinkedHashMap<ElectricTrain, ArrayList<Schedule>> result = electricTrainController.viewSchedule();
        Iterator<Map.Entry<ElectricTrain, ArrayList<Schedule>>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<ElectricTrain, ArrayList<Schedule>> listEntry = iterator.next();
            for (Schedule schedule : listEntry.getValue()) {
                System.out.println(listEntry.getKey() + " " + schedule);
            }
        }
    }

    /**
     * Вывод расписания у определённого поезда
     * @param numberTrain   номер поезда
     */
    private void viewSchedule(int numberTrain) {
        ElectricTrainController electricTrainController = ElectricTrainController.getInstance();
        if (electricTrainController.get().isEmpty()) {
            System.out.println("Не определено ни одного поезда.");
            return;
        }
        for (Schedule schedule : electricTrainController.viewSchedule(numberTrain)) {
            System.out.println(schedule);
        }
    }

    /**
     * Метод проверяет, является ли переданная строка числом
     * @param s    строка для проверки
     * @return     true - число, иначе false
     */
    private boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar < '0' || aChar > '9') {
                return false;
            }
        }
        return true;
    }
}
