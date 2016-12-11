package ru.solpro.model;

import ru.solpro.controller.adapter.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс-модель для расписания каждой электрички
 *
 * @author Protsvetov Danila
 */

@XmlType(name = "schedule", propOrder = {
        "route",
        "departureDateTime",
        "hour",
        "min"})
public class Schedule implements Comparable<Schedule>, Serializable {
//    private static int count;
    private int id;
    private Route route;
    private LocalDateTime departureDateTime;    //дата/время отправления
    private long hour;                          //время в пути
    private long min;                           //время в пути

//    public static void serializeStatic(ObjectOutputStream objectOutputStream) throws IOException {
//        objectOutputStream.writeInt(count);
//    }
//
//    public static void deserializeStatic(ObjectInputStream objectInputStream) throws IOException {
//        count = objectInputStream.readInt();
//    }

    public Schedule() {
    }

    Schedule(int id, Route route, LocalDateTime departureDateTime, long hour, long min) {
        this.id = id;
        this.route = route;
        this.departureDateTime = departureDateTime;
        this.hour = hour;
        this.min = min;
    }

    @XmlElement
    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    @XmlElement
    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    private LocalDateTime getArrivalDateTime() {
        return departureDateTime.plusHours(hour).plusMinutes(min);
    }

    private String getTravelTime() {
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
        return "[" + id + "] Отправление " +
                departureDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
                "; Прибытие " +
                getArrivalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
