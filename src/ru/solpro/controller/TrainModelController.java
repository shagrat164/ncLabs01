package ru.solpro.controller;

import ru.solpro.model.Train;
import ru.solpro.model.Route;
import ru.solpro.model.Schedule;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class TrainModelController implements ModelController<Train>, Serializable {
    private static TrainModelController instance;
    private TreeSet<Train> trains;

    private TrainModelController() {
        trains = new TreeSet<>();
        // заполнение данными для первоначального тестирования
//        add(1000);
//        add(1001);
//        add(1002);
//        add(1003);
//        add(1004);
//        add(1005);
//        add(1006);
//        add(1206);
//
//        addScheduleLine(1, 1000, LocalDateTime.now(), 1);
//        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(1), 10);
//        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(2), 10);
//        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(3), 10);
//        addScheduleLine(1, 1000, LocalDateTime.now().plusDays(4), 10);
//
//        addScheduleLine(2, 1001, LocalDateTime.now(), 4);
//        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(1), 4);
//        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(2), 4);
//        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(3), 4);
//        addScheduleLine(2, 1001, LocalDateTime.now().plusDays(4), 4);
//
//        addScheduleLine(3, 1002, LocalDateTime.now(), 4);
//        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(1), 4);
//        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(2), 4);
//        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(3), 4);
//        addScheduleLine(3, 1002, LocalDateTime.now().plusDays(4), 4);
//
//        addScheduleLine(4, 1003, LocalDateTime.now(), 4);
//        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(1), 4);
//        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(2), 4);
//        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(3), 4);
//        addScheduleLine(4, 1003, LocalDateTime.now().plusDays(4), 4);
//
//        addScheduleLine(5, 1004, LocalDateTime.now(), 4);
//        addScheduleLine(5, 1004, LocalDateTime.now().plusDays(1), 4, 15);
//
//        addScheduleLine(6, 1005, LocalDateTime.now().plusDays(2), 4, 20);
//        addScheduleLine(6, 1005, LocalDateTime.now().plusDays(3), 4);
//
//        addScheduleLine(6, 1206, LocalDateTime.now().plusDays(4), 10, 30);
    }

    public static TrainModelController getInstance() {
        if (instance == null) {
            instance = new TrainModelController();
        }
        return instance;
    }

    public static void setInstance(TrainModelController instance) {
        TrainModelController.instance = instance;
    }

    @Override
    public ArrayList<Train> search(String find) {
        return null;
    }

    @Override
    public Train search(int numberTrain) {
        for (Train train : trains) {
            if (train.getTrainNumber() == numberTrain) {
                return train;
            }
        }
        return null;
    }

    @Override
    public boolean add(Train train) {
        return trains.add(train);
    }

    public boolean add(int trainNumber) {
        return trains.add(new Train(trainNumber));
    }

    @Override
    public boolean remove(int number) {
        for (Train train : trains) {
            if (train.getTrainNumber() == number) {
                return trains.remove(train);
            }
        }
        return false;
    }

    @Override
    public boolean remove(Train train) {
        return trains.remove(train);
    }

    @Override
    public TreeSet<Train> getData() {
        return trains;
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
        Train train = search(numberTrains);
        if (train != null && route != null) {
            train.addScheduleLine(route, departureDateTime, hours, 0);
            return true;
        }
        return false;
    }

    /**
     * Метод добавления строки расписания для поезда
     * @param numberTrain         номер поезда
     * @param departureDateTime    дата и время итправления
     * @param hours                время движения до пункта назначения (часов)
     * @param min                  время движения до пункта назначения (минут)
     */
    public boolean addScheduleLine(int routeId, int numberTrain, LocalDateTime departureDateTime, long hours, long min) {
        Route route = RouteModelController.getInstance().search(routeId);
        Train train = search(numberTrain);
        if (train != null && route != null) {
            train.addScheduleLine(route, departureDateTime, hours, min);
            return true;
        }
        return false;
    }

    /**
     * Метод удаления строки в расписании поезда
     * @param scheduleId
     * @param numberTrain
     */
    public void delScheduleLine(int scheduleId, int numberTrain) {
        Train train = search(numberTrain);
        if (train != null) {
            //TODO допилить
        }
    }

    /**
     * Расписание поездов за ближайшие 24 часа
     * @return Массив с расписанием.
     */
    public LinkedHashMap<Train, ArrayList<Schedule>> viewSchedule() {
        LinkedHashMap<Train, ArrayList<Schedule>> result = new LinkedHashMap<>();
        LocalDateTime borderDateTime = LocalDateTime.now().plusHours(24);
        for (Train train : trains) {
            ArrayList<Schedule> resultSchedule = new ArrayList<>();
            for (Schedule schedule : train.getTrainTimetable()) {
                if (schedule.getDepartureDateTime().isBefore(borderDateTime)) {
                    resultSchedule.add(schedule);
                }
            }
            result.put(train, resultSchedule);
        }
        return result;
    }

    /**
     * Расписание определённого поезда
     * @param numberTrain    номер поезда
     */
    public ArrayList<Schedule> viewSchedule(int numberTrain) {
        Train train = search(numberTrain);
        if (train == null) {
            return null;
        }
        return new ArrayList<>(train.getTrainTimetable());
    }
}
