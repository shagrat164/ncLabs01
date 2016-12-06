package ru.solpro.model;

import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Класс-модель для электропоезда (Номер состава, маршрут, <viev>Schedule</viev>)
 *
 * @author Protsvetov Danila
 */
public class ElectricTrain implements Comparable<ElectricTrain> {
    private int trainNumber;                    //номер поезда
    private Route route;                        //маршрут
    private TreeSet<Schedule> trainTimetable;   //расписание

    /**
     * Конструктор для создания поезда
     *
     * @param trainNumber Номер поезда
     * @param route Маршрут
     */
    public ElectricTrain(int trainNumber, Route route) {
        this.trainNumber = trainNumber;
        this.route = route;
        this.trainTimetable = new TreeSet<>();
    }

    public TreeSet<Schedule> getTrainTimetable() {
        return trainTimetable;
    }

    public void addScheduleLine(LocalDateTime departureDateTime, long hours, long min) {
        this.trainTimetable.add(new Schedule(this.trainNumber, departureDateTime, hours, min));
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    Route getRoute() {
        return route;
    }

    void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public int compareTo(ElectricTrain o) {
        if (trainNumber > o.getTrainNumber()) {
            return 1;
        } else if (trainNumber < o.getTrainNumber()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "номер поезда=[" + trainNumber +
                "] маршрут=[" + route.getId() +
                "] " + route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElectricTrain that = (ElectricTrain) o;

        if (trainNumber != that.trainNumber) return false;
        return route != null ? route.equals(that.route) : that.route == null;
    }

    @Override
    public int hashCode() {
        int result = trainNumber;
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }
}
