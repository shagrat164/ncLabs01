package ru.solpro;

import ru.solpro.controller.CommandController;
import ru.solpro.controller.parser.DataParser;
import ru.solpro.controller.parser.SerializationData;

import java.io.IOException;

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
        DataParser dataParser = new SerializationData();
        try {
            dataParser.load();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
//        init();
        CommandController commandController = new CommandController();
        commandController.execute();
    }
}
