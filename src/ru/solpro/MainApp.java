package ru.solpro;

import ru.solpro.controller.CommandController;
import ru.solpro.controller.parser.DataParser;
import ru.solpro.controller.parser.SerializationData;
import ru.solpro.controller.parser.XmlData;

/**
 * Задание
 * Информационная система "Расписание электричек"
 * Структура:
 *          Электропоезд (Номер состава, маршрут, время отправления, путевое время)
 *          Маршрут (Начальная станция, конечная станция)
 *
 * @author Protsvetov Danila
 */

public class MainApp {
    private static void init() {
//        DataParser dataParser = new SerializationData();
        DataParser dataParser = new XmlData();
        dataParser.load();
    }

    public static void main(String[] args) {
        init();
        CommandController commandController = new CommandController();
        commandController.execute();
    }
}
