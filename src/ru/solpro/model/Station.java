package ru.solpro.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Класс-модель для станции
 *
 * @author Protsvetov Danila
 */

@XmlRootElement(name = "station")
public class Station implements Comparable<Station>, Serializable {
    private static int count;
    private int id;
    private String nameStation;

    public static void serializeStatic(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(count);
    }

    public static void deserializeStatic(ObjectInputStream objectInputStream) throws IOException {
        count = objectInputStream.readInt();
    }

    public static void setCount(int count) {
        Station.count = count;
    }

    public Station() {
    }

    public Station(String name) {
        count += 1;
        this.id = count;
        this.nameStation = name;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    @Override
    public int compareTo(Station o) {
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
        return nameStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return nameStation.equals(station.nameStation);
    }

    @Override
    public int hashCode() {
        return nameStation.hashCode();
    }
}
