package ru.solpro.data;

/**
 * Маршрут (Начальная станция, конечная станция)
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
