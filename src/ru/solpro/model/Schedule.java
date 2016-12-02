package ru.solpro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс-модель для расписания каждой электрички
 *
 * @author Protsvetov Danila
 */
public class Schedule {
    private ElectricTrain electricTrain;        //принадлежность к поезду
    private LocalDateTime departureDateTime;    //дата/время отправления
    private LocalDateTime arrivalDateTime;      //дата/время прибытия
    private long hour;                          //время в пути
    private long min;                           //время в пути

    Schedule(ElectricTrain electricTrain, LocalDateTime departureDateTime, long hour) {
        this(electricTrain, departureDateTime, hour, 0);
    }

    Schedule(ElectricTrain electricTrain, LocalDateTime departureDateTime, long hour, long min) {
        this.electricTrain = electricTrain;
        this.departureDateTime = departureDateTime;
        this.hour = hour;
        this.min = min;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        this.arrivalDateTime = departureDateTime.plusHours(hour);
        this.arrivalDateTime = departureDateTime.plusMinutes(min);
        return arrivalDateTime;
    }

    private String getTravelTime() {
        return hour + ":" + min;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                departureDateTime.format(DateTimeFormatter.ofPattern("dd.mm.yyyy hh:mm:ss")) +
                ", " + this.getTravelTime() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;
        return electricTrain.equals(schedule.electricTrain);
    }

    @Override
    public int hashCode() {
        return electricTrain.hashCode();
    }
}
