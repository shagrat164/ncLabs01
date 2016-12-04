package ru.solpro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс-модель для расписания каждой электрички
 *
 * @author Protsvetov Danila
 */
public class Schedule implements Comparable<Schedule> {
    private int numberTrain;
    private LocalDateTime departureDateTime;    //дата/время отправления
    private long hour;                          //время в пути
    private long min;                           //время в пути

    Schedule(int numberTrain, LocalDateTime departureDateTime, long hour) {
        this(numberTrain, departureDateTime, hour, 0);
    }

    Schedule(int numberTrain, LocalDateTime departureDateTime, long hour, long min) {
        this.numberTrain = numberTrain;
        this.departureDateTime = departureDateTime;
        this.hour = hour;
        this.min = min;
    }

    public int getNumberTrain() {
        return numberTrain;
    }

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
    public int compareTo(Schedule o) {
        if (departureDateTime.isAfter(o.departureDateTime)) {
            return 1;
        } else if (departureDateTime.isBefore(o.departureDateTime)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Отправление " +
                departureDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
                "; Прибытие " +
                getArrivalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
