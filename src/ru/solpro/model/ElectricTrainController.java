package ru.solpro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class ElectricTrainController implements Controller<ElectricTrain> {
    private static ElectricTrainController instance;
    private TreeSet<ElectricTrain> electricTrains;

    private ElectricTrainController() {
        electricTrains = new TreeSet<>();
        // заполнение данными для первоначального тестирования
        add(1000, 1);
        add(1001, 2);
        add(1002, 3);
        add(1003, 4);
        add(1004, 5);
        add(1005, 6);
        add(1006, 7);
        add(1206, 7);

        addScheduleLine(1000, LocalDateTime.now(), 1);
        addScheduleLine(1000, LocalDateTime.now().plusDays(1), 10);
        addScheduleLine(1000, LocalDateTime.now().plusDays(2), 10);
        addScheduleLine(1000, LocalDateTime.now().plusDays(3), 10);
        addScheduleLine(1000, LocalDateTime.now().plusDays(4), 10);

        addScheduleLine(1001, LocalDateTime.now(), 4);
        addScheduleLine(1001, LocalDateTime.now().plusDays(1), 4);
        addScheduleLine(1001, LocalDateTime.now().plusDays(2), 4);
        addScheduleLine(1001, LocalDateTime.now().plusDays(3), 4);
        addScheduleLine(1001, LocalDateTime.now().plusDays(4), 4);

        addScheduleLine(1002, LocalDateTime.now(), 4);
        addScheduleLine(1002, LocalDateTime.now().plusDays(1), 4);
        addScheduleLine(1002, LocalDateTime.now().plusDays(2), 4);
        addScheduleLine(1002, LocalDateTime.now().plusDays(3), 4);
        addScheduleLine(1002, LocalDateTime.now().plusDays(4), 4);

        addScheduleLine(1003, LocalDateTime.now(), 4);
        addScheduleLine(1003, LocalDateTime.now().plusDays(1), 4);
        addScheduleLine(1003, LocalDateTime.now().plusDays(2), 4);
        addScheduleLine(1003, LocalDateTime.now().plusDays(3), 4);
        addScheduleLine(1003, LocalDateTime.now().plusDays(4), 4);

        addScheduleLine(1004, LocalDateTime.now(), 4);
        addScheduleLine(1004, LocalDateTime.now().plusDays(1), 4, 15);

        addScheduleLine(1005, LocalDateTime.now().plusDays(2), 4, 20);
        addScheduleLine(1005, LocalDateTime.now().plusDays(3), 4);

        addScheduleLine(1206, LocalDateTime.now().plusDays(4), 10, 30);
    }

    public static ElectricTrainController getInstance() {
        if (instance == null) {
            instance = new ElectricTrainController();
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

    public boolean add(int trainNumber, int routeId) {
        Route route = RouteController.getInstance().search(routeId);
        if (route != null) {
            return electricTrains.add(new ElectricTrain(trainNumber, route));
        }
        return false;
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
     * @param numberTrains         номер поезда
     * @param departureDateTime    дата и время итправления
     * @param hours                время движения до пункта назначения (часов)
     */
    public boolean addScheduleLine(int numberTrains, LocalDateTime departureDateTime, long hours) {
        ElectricTrain electricTrain = search(numberTrains);
        if (electricTrain != null) {
            electricTrain.addScheduleLine(departureDateTime, hours, 0);
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
    public boolean addScheduleLine(int numberTrains, LocalDateTime departureDateTime, long hours, long min) {
        ElectricTrain electricTrain = search(numberTrains);
        if (electricTrain != null) {
            electricTrain.addScheduleLine(departureDateTime, hours, min);
            return true;
        }
        return false;
    }

    /**
     * Расписание поездов за ближайшие 24 часа
     * @return Массив с расписанием.
     */
    public ArrayList<Schedule> viewSchedule() {
        ArrayList<Schedule> result = new ArrayList<>();

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
