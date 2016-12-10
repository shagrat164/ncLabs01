package ru.solpro.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Класс-модель для маршрута (Начальная станция, конечная станция)
 *
 * @author Protsvetov Danila
 */

@XmlRootElement(name = "route")
public class Route implements Comparable<Route>, Serializable {
    private static int count;
    private int id;
    private Station departure;   //отправление
    private Station arrival;     //прибытие

    public static void serializeStatic(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(count);
    }

    public static void deserializeStatic(ObjectInputStream objectInputStream) throws IOException {
        count = objectInputStream.readInt();
    }

    public Route() {
    }

    /**
     *
     * @param departure     станция отправления
     * @param arrival       станция прибытия
     */
    public Route(Station departure, Station arrival) {
        this.departure = departure;
        this.arrival = arrival;
        count += 1;
        this.id = count;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Route.count = count;
    }

    /**
     *
     * @return станция отправления
     */
    @XmlElement(name = "dep")
    public Station getDeparture() {
        return departure;
    }

    /**
     *
     * @return станция прибытия
     */
    @XmlElement(name = "arr")
    public Station getArrival() {
        return arrival;
    }

    /**
     *
     * @param departure
     */
    public void setDeparture(Station departure) {
        this.departure = departure;
    }

    /**
     *
     * @param arrival
     */
    public void setArrival(Station arrival) {
        this.arrival = arrival;
    }

    @Override
    public int compareTo(Route o) {
        if (id > o.getId()) {
            return 1;
        } else if (id < o.getId()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return departure + "->" + arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (!departure.equals(route.departure)) return false;
        return arrival.equals(route.arrival);

    }

    @Override
    public int hashCode() {
        int result = departure.hashCode();
        result = 31 * result + arrival.hashCode();
        return result;
    }
}
