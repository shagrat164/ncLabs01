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
//    private Set<Station> stations = new HashSet<>();
//    private Set<Route> routes = new HashSet<>();
//    private ArrayList<ElectricTrain> electricTrains = new ArrayList<>();
//    private MainApp mainApp;

    public static void main(String[] args) {
        try {
            CommandController commandController = new CommandController();
            commandController.execute();
        } catch (IOException e) {/*NOP*/}
    }

//    /**
//     * Конструктор заполняет данными основные коллекции для хранения данных
//     */
//    public MainApp() {
//    }
//
//    public Set<Station> getStations() {
//        return stations;
//    }
//
//    public Set<Route> getRoutes() {
//        return routes;
//    }
//
//    public ArrayList<ElectricTrain> getElectricTrains() {
//        return electricTrains;
//    }
//
//    public MainApp getMainApp() {
//        return mainApp;
//    }
}
