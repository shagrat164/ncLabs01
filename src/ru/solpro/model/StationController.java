package ru.solpro.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StationController {
    private HashSet<Station> stations;

    public StationController() {
        stations = new HashSet<>();
        add("САРАТОВ-1");
        add("ТАТИЩЕВО-1");
        add("АТКАРСК-1");
        add("БАЛАКОВО-1");
    }

    /**
     * Метод осуществляет поиск станции по строке поиска.
     *
     * @param find Параметры поиска.<br/>
     *             Может включать [*] - для пропуска символа и [?] - для пропуска нескольких символов.
     * @return Список найденных станций
     */
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
            m = p.matcher(station.toString().toLowerCase());
            if (m.matches()) {
                result.add(station);
            }
        }
        return result;
    }

    public boolean del(Station station) {
        return stations.remove(station);
    }

    public boolean add(Station station) {
        return stations.add(station);
    }

    public boolean add(String string) {
        return stations.add(new Station(string.toUpperCase()));
    }

    public HashSet<Station> get() {
        return stations;
    }
}
