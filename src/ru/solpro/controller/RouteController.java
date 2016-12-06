package ru.solpro.controller;

import ru.solpro.model.Controller;
import ru.solpro.model.Route;
import ru.solpro.model.Station;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Protsvetov Danila
 */
public class RouteController implements Controller<Route> {
    private static RouteController instance;
    private TreeSet<Route> routes;

    private RouteController() {
        routes = new TreeSet<>();
        // заполнение данными для первоначального тестирования
        add(1, 2);
        add(1, 3);
        add(1, 4);
        add(1, 5);
        add(1, 6);
        add(1, 7);
        add(1, 8);
    }

    public static RouteController getInstance() {
        if (instance == null) {
            instance = new RouteController();
        }
        return instance;
    }

    /**
     * Метод осуществляет поиск маршрута по строке поиска.
     *
     * @param find Параметры поиска.<br/>
     *             Может включать [*] - для пропуска символа и [?] - для пропуска нескольких символов.
     * @return Список найденных маршрутов
     */
    @Override
    public ArrayList<Route> search(String find) {
        ArrayList<Route> result = new ArrayList<>();
        if (find.contains("*")) {
            find = find.replace("*", ".");
        }
        if (find.contains("?")) {
            find = find.replace("?", "[а-яА-ЯёЁa-zA-Z0-9-\\s]*");
        }
        Pattern p = Pattern.compile("^" + find.toUpperCase() + "$");
        Matcher m;
        for (Route route : routes) {
            m = p.matcher(route.toString().toUpperCase());
            if (m.matches()) {
                result.add(route);
            }
        }
        return result;
    }

    /**
     * Метод поиска по идентификатору
     *
     * @param id Идентификатор для поиска
     * @return
     */
    @Override
    public Route search(int id) {
        for (Route route : routes) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    /**
     * Метод удаляет маршрут из списка маршрутов.
     * @param id Идентификатор маршрута
     * @return true - маршрут успешно удалён
     *         false - маршрут не найден для удаления
     */
    @Override
    public boolean del(int id) {
        for (Route route : routes) {
            if (route.getId() == id) {
                return routes.remove(route);
            }
        }
        return false;
    }

    @Override
    public boolean del(Route route) {
        return routes.remove(route);
    }

    @Override
    public boolean add(Route route) {
        return routes.add(route);
    }

    public boolean add(int idDeparture, int idArrival) {
        StationController stationController = StationController.getInstance();
        Station departure = stationController.search(idDeparture);
        Station arrival = stationController.search(idArrival);
        if (departure == null || arrival == null || departure.equals(arrival)) {
            return false;
        }
        return this.routes.add(new Route(departure, arrival));
    }

    @Override
    public TreeSet<Route> get() {
        return routes;
    }
}
