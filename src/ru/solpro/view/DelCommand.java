package ru.solpro.view;

import ru.solpro.controller.TrainModelController;
import ru.solpro.controller.RouteModelController;
import ru.solpro.controller.StationModelController;
import ru.solpro.controller.SystemException;
import ru.solpro.model.Train;
import ru.solpro.model.Route;
import ru.solpro.model.Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Команда удаления данных
 * @author Protsvetov Danila
 */
public class DelCommand extends AlwaysCommand implements Command {
    @Override
    public boolean execute(String[] args) throws IOException, SystemException {
        if (args == null || args.length < 1 || args.length > 1) {
            System.out.println("Неверный аргумент у команды.");
            printHelp();
            return true;
        }
        switch (args[0].toUpperCase()) {
            case "STATION":
                delStation();
                break;
            case "ROUTE":
                delRoute();
                break;
            case "TRAIN":
                delTrain();
                break;
            case "SCHEDULE":
                delSchedule();
                break;
            default:
                printHelp();
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println("Данная команда позволяет удалять данные из системы.");
        System.out.println("Список параметров команды:");
        System.out.println("STATION - удаление станции.");
        System.out.println("ROUTE - удаление маршрута.");
        System.out.println("TRAIN - удаление поезда.");
        System.out.println("SCHEDULE - удаление расписания у определённого поезда.");
    }

    @Override
    public String getName() {
        return "DEL";
    }

    @Override
    public String getDescription() {
        return "Удаление информации";
    }

    /**
     * Удаление расписания у поезда
     */
    private void delSchedule() throws IOException, SystemException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TrainModelController trainModelController = TrainModelController.getInstance();

        System.out.print("\tВведите номер поезда: ");
        int numberTrain = Integer.parseInt(reader.readLine());
        System.out.print("\tВведите id расписания: ");
        int idSchedule = Integer.parseInt(reader.readLine());
        if (!trainModelController.delScheduleLine(idSchedule, numberTrain)) {
            error("Невозможно удалить строку расписания.");
        }
    }

    /**
     * Удаление поезда
     */
    private void delTrain() throws IOException, SystemException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TrainModelController trainModelController = TrainModelController.getInstance();

        System.out.print("\tВведите номер поезда: ");
        int numberTrain = Integer.parseInt(reader.readLine());
        if (!trainModelController.remove(numberTrain)) {
            error("Не найден поезд для удаления.");
        }
    }

    /**
     * Удаление маршрута
     */
    private void delRoute() throws IOException, SystemException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RouteModelController routeModelController = RouteModelController.getInstance();
        TrainModelController trainModelController = TrainModelController.getInstance();

        System.out.print("Введите id маршрута который требуется удалить: ");
        int idRoute = Integer.parseInt(reader.readLine());
        Route route = routeModelController.search(idRoute);
        if (route == null) {
            error("Не найден маршрут для удаления.");
        }

        ArrayList<Train> trains = new ArrayList<>();
        //проверяются все поезда на совпадение с удаляемой станцией
        for (Train train : trainModelController.getData()) {
            if (train.getTrainTimetable().isEmpty() || train.getTrainTimetable() == null) {
                continue;
            }
            if (train.getTrainTimetable().first().getRoute().equals(route)) {
                trains.add(train);
            }
        }

        if (!trains.isEmpty()) {
            System.out.println("\tПо маршруту " + route + " передвигаются поезда с номерами:");
            for (Train train : trains) {
                System.out.print(" " + train.getTrainNumber());
            }
            error("Удаление маршрута не возможно.");
        }

        routeModelController.remove(route);
    }

    /**
     * удаление станции
     */
    private void delStation() throws IOException, SystemException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StationModelController stationModelController = StationModelController.getInstance();
        RouteModelController routeModelController = RouteModelController.getInstance();

        System.out.print("Введите id станции которую требуется удалить: ");
        int idStation = Integer.parseInt(reader.readLine());
        Station station = stationModelController.search(idStation);
        if (station == null) {
            error("Не найдена станция для удаления.");
        }

        ArrayList<Route> routes = new ArrayList<>();
        //проверяются все маршруты на совпадение с удаляемой станцией
        for (Route route : routeModelController.getData()) {
            if (route == null) {
                continue;
            }
            if (route.getIdDeparture() == idStation || route.getIdArrival() == idStation) {
                routes.add(route);
            }
        }

        if (!routes.isEmpty()) {
            System.out.println("\tСтанция " + station + " содержится в маршрутах:");
            for (Route route : routes) {
                System.out.print(" [" + route.getId() + "] " + route);
            }
            error("Удаление станции не возможно.");
        }

        stationModelController.remove(station);
    }
}
