package ru.solpro.controller;

import ru.solpro.model.ElectricTrain;
import ru.solpro.model.Route;
import ru.solpro.model.Schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class ElectricTrainModelController implements ModelController<ElectricTrain> {
    private static ElectricTrainModelController instance;
    private TreeSet<ElectricTrain> electricTrains;

    private ElectricTrainModelController() {
        electricTrains = new TreeSet<>();
        // заполнение данными для первоначального тестирования
        add(1000);
        add(1001);
        add(1002);
        add(1003);
        add(1004);
        add(1005);
        add(1006);
        add(1206);

        addScheduleLine(1, 1000, LocalDateTime.now(), 1);
        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(1), 10);
        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(2), 10);
        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(3), 10);
        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(4), 10);

        addScheduleLine(2, 1001, LocalDateTime.now(), 4);
        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(1), 4);
        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(2), 4);
        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(3), 4);
        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(4), 4);

        addScheduleLine(3, 1002, LocalDateTime.now(), 4);
        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(1), 4);
        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(2), 4);
        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(3), 4);
        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(4), 4);

        addScheduleLine(4, 1003, LocalDateTime.now(), 4);
        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(1), 4);
        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(2), 4);
        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(3), 4);
        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(4), 4);

        addScheduleLine(5, 1004, LocalDateTime.now(), 4);
        addScheduleLine(5, 1004, LocalDateTime.now().plusDays(1), 4, 15);

        addScheduleLine(6, 1005, LocalDateTime.now().plusDays(2), 4, 20);
        addScheduleLine(6, 1005, LocalDateTime.now().plusDays(3), 4);

        addScheduleLine(6, 1206, LocalDateTime.now().plusDays(4), 10, 30);
    }

    public static ElectricTrainModelController getInstance() {
        if (instance == null) {
            instance = new ElectricTrainModelController();
        }
        return instance;
    }

    @Override
    public ArrayList<ElectricTrain> search(String find) {
        return null;
    }

    @Override
    public ElectricTrain search(int numberTrain) {
        for (ElectricTrain electricTrain : electricTrains) {
            if (electricTrain.getTrainNumber() == numberTrain) {
                return electricTrain;
            }
        }
        return null;
    }

    @Override
    public boolean add(ElectricTrain electricTrain) {
        return electricTrains.add(electricTrain);
    }

    public boolean add(int trainNumber) {
        return electricTrains.add(new ElectricTrain(trainNumber));
    }

    @Override
    public boolean del(int number) {
        for (ElectricTrain electricTrain : electricTrains) {
            if (electricTrain.getTrainNumber() == number) {
                return electricTrains.remove(electricTrain);
            }
        }
        return false;
    }

    @Override
    public boolean del(ElectricTrain electricTrain) {
        return electricTrains.remove(electricTrain);
    }

    @Override
    public TreeSet<ElectricTrain> get() {
        return electricTrains;
    }

    /**
     * Метод добавления строки расписания для поезда
     * @param routeId              id маршрута
     * @param numberTrains         номер поезда
     * @param departureDateTime    дата и время итправления
     * @param hours                время движения до пункта назначения (часов)
     */
    public boolean addScheduleLine(int routeId, int numberTrains, LocalDateTime departureDateTime, long hours) {
        Route route = RouteModelController.getInstance().search(routeId);
        ElectricTrain electricTrain = search(numberTrains);
        if (electricTrain != null && route != null) {
            electricTrain.addScheduleLine(route, departureDateTime, hours, 0);
            return true;
        }
        return false;
    }

    /**
     * Метод добавления строки расписания для поезда
     * @param numberTrains         номер поезда
     * @param departureDateTime    дата и время итправления
     * @param hours                время движения до пункта назначения (часов)
     * @param min                  время движения до пункта назначения (минут)
     */
    public boolean addScheduleLine(int routeId, int numberTrains, LocalDateTime departureDateTime, long hours, long min) {
        Route route = RouteModelController.getInstance().search(routeId);
        ElectricTrain electricTrain = search(numberTrains);
        if (electricTrain != null && route != null) {
            electricTrain.addScheduleLine(route, departureDateTime, hours, min);
            return true;
        }
        return false;
    }

    /**
     * Расписание поездов за ближайшие 24 часа
     * @return Массив с расписанием.
     */
    public LinkedHashMap<ElectricTrain, ArrayList<Schedule>> viewSchedule() {
        LinkedHashMap<ElectricTrain, ArrayList<Schedule>> result = new LinkedHashMap<>();
        LocalDateTime borderDateTime = LocalDateTime.now().plusHours(24);
        for (ElectricTrain electricTrain : electricTrains) {
            ArrayList<Schedule> resultSchedule = new ArrayList<>();
            for (Schedule schedule : electricTrain.getTrainTimetable()) {
                if (schedule.getDepartureDateTime().isBefore(borderDateTime)) {
                    resultSchedule.add(schedule);
                }
            }
            result.put(electricTrain, resultSchedule);
        }
        return result;
    }

    /**
     * Расписание определённого поезда
     * @param numberTrain    номер поезда
     */
    public ArrayList<Schedule> viewSchedule(int numberTrain) {
        ElectricTrain train = search(numberTrain);
        if (train == null) {
            return null;
        }
        return new ArrayList<>(train.getTrainTimetable());
    }
}
