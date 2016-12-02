package ru.solpro.model;

/**
 * Класс-модель для станции
 *
 * @author Protsvetov Danila
 */
public class Station {
    private static int count;
    private int id;
    private String nameStation;

    Station(String nameStation) {
        count += 1;
        id = count;
        this.nameStation = nameStation;
    }

    public int getId() {
        return id;
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
