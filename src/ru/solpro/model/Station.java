package ru.solpro.model;

/**
 * Класс-модель для станции
 *
 * @author Protsvetov Danila
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
