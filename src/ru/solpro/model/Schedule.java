package ru.solpro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс-модель для расписания каждой электрички
 *
 * @author Protsvetov Danila
 */
public class Schedule {
    private static int count;
    private int id;
    private LocalDateTime departureDateTime;    //дата/время отправления
    private long hour;                          //время в пути
    private long min;                           //время в пути

    Schedule(int id, LocalDateTime departureDateTime, long hour) {
        this(id, departureDateTime, hour, 0);
    }

    Schedule(int id, LocalDateTime departureDateTime, long hour, long min) {
        count += 1;
        this.id = id;
        this.departureDateTime = departureDateTime;
        this.hour = hour;
        this.min = min;
    }

    Schedule(LocalDateTime departureDateTime, long hour) {
        this(departureDateTime, hour, 0);
    }

    Schedule(LocalDateTime departureDateTime, long hour, long min) {
        count += 1;
        this.id = count;
        this.departureDateTime = departureDateTime;
        this.hour = hour;
        this.min = min;
    }

    int getId() {
        return id;
    }

//    public int getNumberTrain() {
//        return numberTrain;
//    }

    LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    LocalDateTime getArrivalDateTime() {
        return departureDateTime.plusHours(hour).plusMinutes(min);
    }

    String getTravelTime() {
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
