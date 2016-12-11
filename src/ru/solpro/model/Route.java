package ru.solpro.model;

import ru.solpro.controller.StationModelController;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Класс-модель для маршрута (Начальная станция, конечная станция)
 *
 * @author Protsvetov Danila
 */

@XmlType(propOrder = {"idDeparture",
                      "idArrival"})
@XmlRootElement(name = "route")
public class Route implements Comparable<Route>, Serializable {
    private static int count;
    private int id;
    private int idDeparture;   //отправление
    private int idArrival;     //прибытие

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
     * @param idDeparture     станция отправления
     * @param idArrival       станция прибытия
     */
    public Route(int idDeparture, int idArrival) {
        this.idDeparture = idDeparture;
        this.idArrival = idArrival;
        count += 1;
        this.id = count;
    }

    @XmlAttribute
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
    @XmlElement
    public int getIdDeparture() {
        return idDeparture;
    }

    /**
     *
     * @return станция прибытия
     */
    @XmlElement
    public int getIdArrival() {
        return idArrival;
    }

    /**
     *
     * @param idDeparture
     */
    public void setIdDeparture(int idDeparture) {
        this.idDeparture = idDeparture;
    }

    /**
     *
     * @param idArrival
     */
    public void setIdArrival(int idArrival) {
        this.idArrival = idArrival;
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
        // Не уверен в правильности этого решения,
        // но это первое что пришло в голову.
        return StationModelController.getInstance().search(idDeparture).getNameStation() +
                "->" + StationModelController.getInstance().search(idArrival).getNameStation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        if (idDeparture != route.idDeparture) return false;
        return idArrival == route.idArrival;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idDeparture;
        result = 31 * result + idArrival;
        return result;
    }
}
