package ru.solpro.data;

import java.util.ArrayList;

/**
 * Маршрут (Начальная станция, конечная станция)
 */
public class Route {
    private Station departure;   //отправление
    private Station arrival;     //прибытие
//    private ArrayList<Station> stations;

    public Route() {

    }

    public Station getDeparture() {
        return departure;
    }

    public Station getArrival() {
        return arrival;
    }
}
