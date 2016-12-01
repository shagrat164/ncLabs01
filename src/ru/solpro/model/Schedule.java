package ru.solpro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс-модель для расписания каждой электрички
 *
 * @author Protsvetov Danila
 */
public class Schedule {
    private LocalDateTime departureDateTime; //дата/время отправления
    private LocalDateTime arrivalDateTime;   //дата/время прибытия
    private long hour;   //время в пути
    private long min;    //время в пути

    Schedule(LocalDateTime departureDateTime, long hour) {
        this(departureDateTime, hour, 0);
    }

    Schedule(LocalDateTime departureDateTime, long hour, long min) {
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
}
