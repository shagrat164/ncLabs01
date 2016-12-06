package ru.solpro.controller;

import ru.solpro.model.Controller;
import ru.solpro.model.Station;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StationController implements Controller<Station> {
    private static StationController instance;
    private TreeSet<Station> stations;

    private StationController() {
        stations = new TreeSet<>();
        // заполнение данными для первоначального тестирования
        add("САРАТОВ-1");
        add("ТАТИЩЕВО");
        add("АТКАРСК");
        add("БАЛАКОВО");
        add("БАЛАШОВ");
        add("СЕННАЯ");
        add("ТАРАНЫ");
        add("ВОЛЬСК");
    }

    public static StationController getInstance() {
        if (instance == null) {
            instance = new StationController();
        }
        return instance;
    }

    /**
     * Метод осуществляет поиск станции по строке поиска.
     *
     * @param find Параметры поиска.<br/>
     *             Может включать [*] - для пропуска символа и [?] - для пропуска нескольких символов.
     * @return Список найденных станций
     */
    @Override
    public ArrayList<Station> search(String find) {
        ArrayList<Station> result = new ArrayList<>();
        if (find.contains("*")) {
            find = find.replace("*", ".");
        }
        if (find.contains("?")) {
            find = find.replace("?", "[а-яА-ЯёЁa-zA-Z0-9-\\s]*");
        }
        Pattern p = Pattern.compile("^" + find.toUpperCase() + "$");
        Matcher m;
        for (Station station : stations) {
            m = p.matcher(station.toString().toUpperCase());
            if (m.matches()) {
                result.add(station);
            }
        }
        return result;
    }

    /**
     * Метод осуществляет поиск станции по её id.
     *
     * @param id    id станции для поиска
     * @return      найденную станцию или null если станция не найдена
     */
    @Override
    public Station search(int id) {
        for (Station station : stations) {
            if (station.getId() == id) {
                return station;
            }
        }
        return null;
    }

    /**
     * Метод удаляет станцию из списка станций.
     * @param id Идентификатор станции
     * @return true - станция успешно удалена
     *         false - станция не найдена для удаления
     */
    @Override
    public boolean del(int id) {
        for (Station station : stations) {
            if (station.getId() == id) {
                return stations.remove(station);
            }
        }
        return false;
    }

    @Override
    public boolean del(Station station) {
        return stations.remove(station);
    }

    public boolean add(int id, String name) {
        return stations.add(new Station(id, name));
    }

    @Override
    public boolean add(Station station) {
        return stations.add(station);
    }

    public boolean add(String name) {
        return stations.add(new Station(name.toUpperCase()));
    }

    @Override
    public TreeSet<Station> get() {
        return stations;
    }
}
