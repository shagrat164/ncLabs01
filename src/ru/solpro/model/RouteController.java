package ru.solpro.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteController {
    private HashSet<Route> routes;

    public RouteController() {
        routes = new HashSet<>();
    }

    /**
     * Метод осуществляет поиск маршрута по строке поиска.
     *
     * @param find Параметры поиска.<br/>
     *             Может включать [*] - для пропуска символа и [?] - для пропуска нескольких символов.
     * @return Список найденных маршрутов
     */
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
            m = p.matcher(route.toString().toLowerCase());
            if (m.matches()) {
                result.add(route);
            }
        }
        return result;
    }

    public boolean del(Route route) {
        return routes.remove(route);
    }

    public boolean add(Route route) {
        return routes.add(route);
    }

    public HashSet<Route> get() {
        return routes;
    }
}
