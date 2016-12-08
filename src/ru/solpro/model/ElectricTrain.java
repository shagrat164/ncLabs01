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
    private TreeSet<Schedule> trainTimetable;   //расписание

    /**
     * Конструктор для создания поезда
     * @param trainNumber Номер поезда
     */
    public ElectricTrain(int trainNumber) {
        this.trainNumber = trainNumber;
        this.trainTimetable = new TreeSet<>();
    }

    /**
     * Очистить расписание
     */
    public void clearTrainTimetable() {
        if (!trainTimetable.isEmpty()) {
            trainTimetable.clear();
        }
    }

    public TreeSet<Schedule> getTrainTimetable() {
        return trainTimetable;
    }

    public void addScheduleLine(Route route, LocalDateTime departureDateTime, long hours, long min) {
        this.trainTimetable.add(new Schedule(route, departureDateTime, hours, min));
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
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
        if (trainTimetable.isEmpty()) {
            return "номер поезда=[" + trainNumber +
                    "] маршрут не определён";
        }
        return "номер поезда=[" + trainNumber +
                "] маршрут=[" + trainTimetable.first().getRoute().getId() +
                "] " + trainTimetable.first().getRoute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElectricTrain that = (ElectricTrain) o;

        if (trainNumber != that.trainNumber) return false;
        return trainTimetable != null ? trainTimetable.equals(that.trainTimetable) : that.trainTimetable == null;
    }

    @Override
    public int hashCode() {
        int result = trainNumber;
        result = 31 * result + (trainTimetable != null ? trainTimetable.hashCode() : 0);
        return result;
    }
}
