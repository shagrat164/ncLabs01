package ru.solpro.model;

/**
 * Класс-модель для маршрута (Начальная станция, конечная станция)
 *
 * @author Protsvetov Danila
 */
public class Route {
    private Station departure;   //отправление
    private Station arrival;     //прибытие

    /**
     * @param departure Станция отправления
     * @param arrival Станция прибытия
     */
    public Route(Station departure, Station arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    /**
     * @return Станция отправления
     */
    public Station getDeparture() {
        return departure;
    }

    /**
     * @return Станция прибытия
     */
    public Station getArrival() {
        return arrival;
    }

    @Override
    public String toString() {
        return "{" + departure + "->" + arrival + "}";
    }
}
