package ru.solpro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    ElectricTrain(int trainNumber, Route route) {
        this.trainNumber = trainNumber;
        this.route = route;
        this.trainTimetable = new TreeSet<>();
    }

    /**
     * Конструктор для создания поезда без маршрута
     *
     * @param trainNumber Номер поезда
     */
    ElectricTrain(int trainNumber) {
        this.trainNumber = trainNumber;
        this.route = null;
        this.trainTimetable = new TreeSet<>();
    }

    TreeSet<Schedule> getTrainTimetable() {
        return trainTimetable;
    }

    /**
     *
     * @param departureDateTime в формате yyyy-mm-dd hh:mm
     * @param hours Время движения в часах
     */
    @Deprecated
    void addTimetable(String departureDateTime, long hours) {
        String[] stringsDateTime = departureDateTime.split(" ");
        String[] stringsDate = stringsDateTime[0].split("-");
        String[] stringsTime = stringsDateTime[1].split(":");

        LocalDate localDate = LocalDate.of(Integer.parseInt(stringsDate[0]), Integer.parseInt(stringsDate[1]), Integer.parseInt(stringsDate[2]));
        LocalTime localTime = LocalTime.of(Integer.parseInt(stringsTime[0]), Integer.parseInt(stringsTime[1]));

        this.trainTimetable.add(new Schedule(LocalDateTime.of(localDate, localTime), hours));
    }

    /**
     *
     * @param departureDateTime в формате yyyy-mm-dd hh:mm
     * @param hours Время движения в часах
     * @param min Время движения минут
     */
    @Deprecated
    void addTimetable(String departureDateTime, long hours, long min) {
        String[] stringsDateTime = departureDateTime.split(" ");
        String[] stringsDate = stringsDateTime[0].split("-");
        String[] stringsTime = stringsDateTime[1].split(":");

        LocalDate localDate = LocalDate.of(Integer.parseInt(stringsDate[0]), Integer.parseInt(stringsDate[1]), Integer.parseInt(stringsDate[2]));
        LocalTime localTime = LocalTime.of(Integer.parseInt(stringsTime[0]), Integer.parseInt(stringsTime[1]));

        this.trainTimetable.add(new Schedule(LocalDateTime.of(localDate, localTime), hours, min));
    }

    void addTimetable(LocalDateTime departureDateTime, long hours) {
        this.trainTimetable.add(new Schedule(departureDateTime, hours));
    }

    void addTimetable(LocalDateTime departureDateTime, long hours, long min) {
        this.trainTimetable.add(new Schedule(departureDateTime, hours, min));
    }

    int getTrainNumber() {
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
        return "ElectricTrain{" +
                "trainNumber=" + trainNumber +
                ", route=" + route +
                '}';
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
