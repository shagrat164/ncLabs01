package ru.solpro.model;

/**
 * Класс-модель для маршрута (Начальная станция, конечная станция)
 *
 * @author Protsvetov Danila
 */
public class Route {
    private static int count;
    private int id;
    private Station departure;   //отправление
    private Station arrival;     //прибытие

    /**
     * @param departure Станция отправления
     * @param arrival Станция прибытия
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
        return "[" + id + "{" + departure + "->" + arrival + "}]";
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
