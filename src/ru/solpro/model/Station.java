package ru.solpro.model;

/**
 * Класс-модель для станции
 *
 * @author Protsvetov Danila
 */
public class Station implements Comparable<Station> {
    private static int count;
    private int id;
    private String nameStation;

    Station(int id, String name) {
        count += 1;
        this.id = id;
        this.nameStation = name;
    }

    Station(String name) {
        count += 1;
        this.id = count;
        this.nameStation = name;
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
