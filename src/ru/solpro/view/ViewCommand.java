package ru.solpro.view;

import ru.solpro.controller.*;
import ru.solpro.controller.TrainModelController;
import ru.solpro.controller.StationModelController;
import ru.solpro.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ViewCommand extends AlwaysCommand implements Command {
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
                    if ((args.length == 2)) {
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
        getDescription();
        System.out.println("Данная команда позволяет просматривать данные в системе.");
        System.out.println("Список параметров команды:");
        System.out.println("STATIONS - выводит список станций.");
        System.out.println("ROUTES - выводит список маршрутов.");
        System.out.println("TRAINS - выводит список поездов.");
        System.out.println("SCHEDULE - выводит расписание на ближайшие 24 часа.");
        System.out.println("SCHEDULE [номер поезда]- выводит расписание определённого поезда.");
    }

    @Override
    public String getName() {
        return "VIEW";
    }

    @Override
    public String getDescription() {
        return "Отображение данных системы.";
    }

    /**
     * Вывод списка станций в системе
     */
    private void viewStations() throws SystemException {
        StationModelController stationController = StationModelController.getInstance();
        if (stationController.getData().isEmpty()) {
            error("Не определено ни одной станции.");
            return;
        }
        for (Station station : stationController.getData()) {
            System.out.println(station.getId() + ". " + station);
        }
    }

    /**
     * Вывод списка маршрутов в системе
     */
    private void viewRoutes() throws SystemException {
        RouteModelController routeController = RouteModelController.getInstance();
        if (routeController.getData().isEmpty()) {
            error("Не определено ни одного маршрута.");
            return;
        }
        for (Route route : routeController.getData()) {
            System.out.println(route.getId() + ". " + route);
        }
    }

    /**
     * Вывод списка поездов в системе
     */
    private void viewTrains() throws SystemException {
        TrainModelController trainModelController = TrainModelController.getInstance();
        if (trainModelController.getData().isEmpty()) {
            error("Не определено ни одного поезда.");
            return;
        }
        for (Train train : trainModelController.getData()) {
            System.out.println(train);
        }
    }

    /**
     * Расписание поездов за ближайшие 24 часа
     */
    private void viewSchedule() throws SystemException {
        TrainModelController trainModelController = TrainModelController.getInstance();
        if (trainModelController.getData().isEmpty()) {
            error("Не определено ни одного поезда.");
            return;
        }
        LinkedHashMap<Train, ArrayList<Schedule>> result = trainModelController.viewSchedule();
        Iterator<Map.Entry<Train, ArrayList<Schedule>>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Train, ArrayList<Schedule>> listEntry = iterator.next();
            for (Schedule schedule : listEntry.getValue()) {
                System.out.println(listEntry.getKey() + " " + schedule);
            }
        }
    }

    /**
     * Вывод расписания у определённого поезда
     * @param numberTrain   номер поезда
     */
    private void viewSchedule(int numberTrain) throws SystemException {
        TrainModelController trainModelController = TrainModelController.getInstance();
        if (trainModelController.getData().isEmpty()) {
            error("Не определено ни одного поезда.");
            return;
        }
        if (trainModelController.viewSchedule(numberTrain) == null) {
            error("Поезда с данным номером не существует.");
            return;
        }
        if (trainModelController.viewSchedule(numberTrain).isEmpty()) {
            error("У поезда нет расписания.");
            return;
        }
        for (Schedule schedule : trainModelController.viewSchedule(numberTrain)) {
            System.out.println(schedule);
        }
    }
}
