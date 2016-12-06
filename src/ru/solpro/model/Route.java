package ru.solpro.model;

/**
 * Класс-модель для маршрута (Начальная станция, конечная станция)
 *
 * @author Protsvetov Danila
 */
public class Route implements Comparable<Route> {
    private static int count;
    private int id;
    private Station departure;   //отправление
    private Station arrival;     //прибытие

    /**
     *
     * @param id           идентификатор маршрута
     * @param departure    станция отправления
     * @param arrival      станция прибытия
     */
    Route(int id, Station departure, Station arrival) {
        count += 1;
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    /**
     *
     * @param departure     станция отправления
     * @param arrival       станция прибытия
     */
    public Route(Station departure, Station arrival) {
        this.departure = departure;
        this.arrival = arrival;
        count += 1;
        id = count;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @return станция отправления
     */
    Station getDeparture() {
        return departure;
    }

    /**
     *
     * @return станция прибытия
     */
    Station getArrival() {
        return arrival;
    }

    @Override
    public int compareTo(Route o) {
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
        return departure + "->" + arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (!departure.equals(route.departure)) return false;
        return arrival.equals(route.arrival);

    }

    @Override
    public int hashCode() {
        int result = departure.hashCode();
        result = 31 * result + arrival.hashCode();
        return result;
    }
}
