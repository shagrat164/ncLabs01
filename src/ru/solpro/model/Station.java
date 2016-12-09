package ru.solpro.model;

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

@XmlType(propOrder = {"id", "nameStation"}, name = "station")
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

    public Station(int id, String name) {
        count += 1;
        this.id = id;
        this.nameStation = name;
    }

    public Station(String name) {
        count += 1;
        this.id = count;
        this.nameStation = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public int getId() {
        return id;
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
