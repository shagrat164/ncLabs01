package ru.solpro;

import ru.solpro.controller.CommandController;
import ru.solpro.model.ElectricTrain;
import ru.solpro.model.Route;
import ru.solpro.model.Station;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Информационная система "Расписание электричек"
 * Структура:
 *          Электропоезд (Номер состава, маршрут, время отправления, путевое время)
 *          Маршрут (Начальная станция, конечная станция)
 *
 * Детальное описание
 * Данные разделены на два (или более) типа, связанные между собой. Обработка каждого типа данных осуществляется
 * в отдельном классе (Model class). После обработки информация сохраняется на диск в определенном формате.
 * Каждый класс должен уметь создавать, удалять и модифицировать свой тип данных, при этом корректно взаимодействуя
 * с классом, занимающимся обработкой зависимых типов. Управление справочной системой осуществляется из консоли с
 * помощью набора команд, которые обрабатываются специальным классом-контроллером (Controller class), а он в свою
 * очередь обращается к классам, работающими с данными, для осуществления конкретной операции с данными.
 * Вывод запрашиваемой информации на экран осуществляется отдельным классом (View class), который получает данные
 * от Model классов. Реализация конкретной справочной системы производится по шаблону MVC(Model-View- Controller).
 *
 * Требования (* помечены обязательные требования)
 * 1. Реализация Справочной системы должны соответствовать шаблону MVC.*
 * 2. Интуитивно понятный интерфейс работы с пользователем.*
 * 3. Наличие функций 1) добавления, 2) удаления, 3) изменения и 4) просмотра данных.*
 * 4. Реализация поиска данных в соответствии с некоторым шаблоном, введенным пользователем. Шаблон включает в себя
 *    все разрешенные символы с точки зрения хранимых данных и символы заменяющие один и несколько любых символов (* и ?)
 * 5. Сохранение и загрузка данных через Serialization/XML. Желательно реализовать сжатие данных.
 * 6. Возможность добавления всех данных из другого файла в текущий с проверкой на наличие дубликатов, т.е. не должно
 *    быть абсолютно одинаковых данных.
 * 7. программный код должен удовлетворять Code Conventions* и снабжен JavaDoc.
 *
 * @author Protsvetov Danila
 */

public class MainApp {
    private Set<Station> stations = new HashSet<>();
    private Set<Route> routes = new HashSet<>();
    private ArrayList<ElectricTrain> electricTrains = new ArrayList<>();

    public static void main(String[] args) {
        try {
            CommandController commandController = new CommandController();
            commandController.execute();
        } catch (IOException e) {/*NOP*/}
    }

    public MainApp() {
        Station station1 = new Station("САРАТОВ-1");
        Station station2 = new Station("ТАТИЩЕВО-1");
        Station station3 = new Station("МОСКВА-ПАВ");
        Station station4 = new Station("МОСКВА-КИЕВ");
        Station station5 = new Station("АТКАРСК-1");
        Station station6 = new Station("БАЛАКОВО-1");
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);

        Route route1 = new Route(station1, station2);
        Route route2 = new Route(station1, station3);
        Route route3 = new Route(station1, station4);
        Route route4 = new Route(station1, station5);
        Route route5 = new Route(station1, station6);
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        routes.add(route4);
        routes.add(route5);

        ElectricTrain train1 = new ElectricTrain(1290, route1);
        train1.addTimetable("2016-12-05 16:05", 2);
        train1.addTimetable("2016-12-06 16:05", 2);
        train1.addTimetable("2016-12-07 16:05", 2);
        train1.addTimetable("2016-12-08 16:05", 2);
        train1.addTimetable("2016-12-09 16:05", 2);
        ElectricTrain train2 = new ElectricTrain(9432, route2);
        train2.addTimetable("2016-12-05 12:05", 0, 15);
        train2.addTimetable("2016-12-05 13:05", 0, 15);
        train2.addTimetable("2016-12-06 12:05", 0, 15);
        train2.addTimetable("2016-12-06 13:05", 0, 15);
        train2.addTimetable("2016-12-07 12:05", 0, 15);
        train2.addTimetable("2016-12-07 13:05", 0, 15);
        train2.addTimetable("2016-12-08 12:05", 0, 15);
        train2.addTimetable("2016-12-08 13:05", 0, 15);
        train2.addTimetable("2016-12-08 14:05", 0, 15);
        train2.addTimetable("2016-12-09 12:05", 0, 15);
        train2.addTimetable("2016-12-09 13:05", 0, 15);
        train2.addTimetable("2016-12-09 14:05", 0, 15);
        train2.addTimetable("2016-12-09 15:05", 0, 15);
        ElectricTrain train3 = new ElectricTrain(1560, route3);
        train3.addTimetable("2016-12-05 10:00", 4, 45);
        train3.addTimetable("2016-12-07 10:00", 4, 45);
        train3.addTimetable("2016-12-09 10:00", 4, 45);
        train3.addTimetable("2016-12-11 10:00", 4, 45);
        train3.addTimetable("2016-12-13 10:00", 4, 45);
        train3.addTimetable("2016-12-15 10:00", 4, 45);
        ElectricTrain train4 = new ElectricTrain(1430, route3);
        train4.addTimetable(LocalDateTime.now(), 15);
        ElectricTrain train5 = new ElectricTrain(1550, route4);
        train5.addTimetable(LocalDateTime.now(), 20);
        ElectricTrain train6 = new ElectricTrain(1670, route5);
        train6.addTimetable(LocalDateTime.now(), 12);
    }
}
