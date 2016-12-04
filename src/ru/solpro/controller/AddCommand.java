package ru.solpro.controller;

import ru.solpro.model.RouteController;
import ru.solpro.model.StationController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddCommand extends AlwaysCommand implements Command {
    @Override
    public boolean execute(String[] args) throws SystemException, IOException {
        if (args == null) {
            printHelp();
            return true;
        }
        for (int i = 0; i < args.length; i++) {
            switch (args[i].toUpperCase()) {
                case "STATION":
                    addStation();
                    break;
                case "ROUTE":
                    addRoute();
                    break;
                case "TRAIN":
                    addTrain();
                    break;
                case "SCHEDULE":
                    //TODO как добавлять расписание?
                    //TODO перенести работу с расписанием в комманду правки?
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

    }

    @Override
    public String getName() {
        return "ADD";
    }

    @Override
    public String getDescription() {
        return "Добавление информации";
    }

    /**
     * Добавление новой станции.
     * @throws SystemException
     * @throws IOException
     */
    private void addStation() throws SystemException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StationController stationController = StationController.getInstance();
        while (true) {
            System.out.print("\tНаименование станции: ");
            String nameStation = reader.readLine();
            if (nameStation.equals("exit")) {
                return;
            }
            if (stationController.search(nameStation).isEmpty() && stationController.add(nameStation)) {
                System.out.println("Станция успешно добавлена. Для завершения операции добавления введите exit.");
            } else {
                error("Невозможно добавить станцию. Станция с таким названием существует.");
            }
        }
    }

    /**
     * Добавление нового маршрута
     * @throws SystemException
     * @throws IOException
     */
    private void addRoute() throws SystemException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RouteController routeController = RouteController.getInstance();
        while (true) {
            System.out.print("\tid станции отправления: ");
            String s = reader.readLine();
            if (s.equals("exit")) {
                return;
            }
            System.out.print("\tid станции назначения: ");
            Integer idArrStation = Integer.parseInt(reader.readLine());
            Integer idDepStation = Integer.parseInt(s);
            if (routeController.add(idDepStation, idArrStation)) {
                System.out.println("Маршрут успешно добавлен. Для завершения операции добавления введите exit.");
            } else {
                error("Невозможно добавить маршрут.");
            }
        }
    }

    /**
     * Добавление нового поезда
     * @throws SystemException
     * @throws IOException
     */
    private void addTrain() throws SystemException, IOException {
        //TODO запилить метод добавления поезда
    }
}
