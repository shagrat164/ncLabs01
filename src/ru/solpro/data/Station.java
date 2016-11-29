package ru.solpro.data;

/**
 * Станция
 */
public class Station {
    private String nameStation;

    public Station(String nameStation) {
        this.nameStation = nameStation;
    }

    @Override
    public String toString() {
        return nameStation;
    }
}
