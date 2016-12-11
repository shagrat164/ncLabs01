package ru.solpro.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Класс-модель для электропоезда (Номер состава, маршрут, <viev>Schedule</viev>)
 *
 * @author Protsvetov Danila
 */

@XmlRootElement(name = "train")
public class Train implements Comparable<Train>, Serializable {
    private int trainNumber;                    //номер поезда
    private TreeSet<Schedule> trainTimetable;   //расписание

    public Train() {
        this.trainTimetable = new TreeSet<>();
    }

    /**
     * Конструктор для создания поезда
     * @param trainNumber Номер поезда
     */
    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
        this.trainTimetable = new TreeSet<>();
    }

    @XmlElement
    public TreeSet<Schedule> getTrainTimetable() {
        return trainTimetable;
    }

    public void setTrainTimetable(TreeSet<Schedule> trainTimetable) {
        this.trainTimetable = trainTimetable;
    }

    @XmlElement(name = "number")
    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    /**
     * Очистить расписание
     */
    public void clearTrainTimetable() {
        if (!trainTimetable.isEmpty()) {
            trainTimetable.clear();
        }
    }

    public void addScheduleLine(Route route, LocalDateTime departureDateTime, long hours, long min) {
        int idSchedule = 1;
        if (!this.trainTimetable.isEmpty()) {
            idSchedule = this.trainTimetable.last().getId() + 1;
        }
        this.trainTimetable.add(new Schedule(idSchedule, route, departureDateTime, hours, min));
    }

    public boolean delScheduleLine(Schedule schedule) {
        return this.trainTimetable.remove(schedule);
    }

    @Override
    public int compareTo(Train o) {
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

        Train that = (Train) o;

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
