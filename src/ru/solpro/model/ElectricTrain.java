package ru.solpro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TreeSet;

/**
 * Класс-модель для электропоезда (Номер состава, маршрут, время отправления, путевое время)
 *
 * @author Protsvetov Danila
 */
public class ElectricTrain {
    private int trainNumber;    //номер поезда
    private Route route;        //маршрут
    private TreeSet<Schedule> trainTimetable;

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

    /**
     *
     * @param departureDateTime в формате yyyy-mm-dd hh:mm
     * @param hours Время движения в часах
     */
    public void addTimetable(String departureDateTime, long hours) {
        this.addTimetable(departureDateTime, hours, 0);
    }

    /**
     *
     * @param departureDateTime в формате yyyy-mm-dd hh:mm
     * @param hours Время движения в часах
     * @param min Время движения минут
     */
    public void addTimetable(String departureDateTime, long hours, long min) {
        String[] stringsDateTime = departureDateTime.split(" ");
        String[] stringsDate = stringsDateTime[0].split("-");
        String[] stringsTime = stringsDateTime[1].split(":");

        LocalDate localDate = LocalDate.of(Integer.parseInt(stringsDate[0]), Integer.parseInt(stringsDate[1]), Integer.parseInt(stringsDate[2]));
        LocalTime localTime = LocalTime.of(Integer.parseInt(stringsTime[0]), Integer.parseInt(stringsTime[1]));

        this.trainTimetable.add(new Schedule(LocalDateTime.of(localDate, localTime), hours, min));
    }

    public void addTimetable(LocalDateTime departureDateTime, long hours) {
        this.addTimetable(departureDateTime, hours, 0);
    }

    public void addTimetable(LocalDateTime departureDateTime, long hours, long min) {
        this.trainTimetable.add(new Schedule(departureDateTime, hours, min));
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "ElectricTrain{" +
                "trainNumber=" + trainNumber +
                ", route=" + route +
                ", trainTimetable=" + trainTimetable +
                '}';
    }
}
