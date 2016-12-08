package ru.solpro.model;

import javax.xml.bind.annotation.*;

/**
 * Класс-модель для станции
 *
 * @author Protsvetov Danila
 */

@XmlAccessorType(XmlAccessType.FIELD)
//корневой элемент
@XmlRootElement(name = "model")
//последовательность тегов в xml
@XmlType(propOrder = {"nameStation"})
public class Station implements Comparable<Station> {
    private static int count;
    private int id;
    private String nameStation;

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

    @XmlElement(name = "name")
    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    //id как атрибут
    @XmlAttribute
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
