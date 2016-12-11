package ru.solpro.controller.parser;

/**
 * Работа с данными
 * @author Protsvetov Danila
 */
public interface DataParser {

    /**
     * Маршаллизация
     */
    void save();

    /**
     * Демаршаллизация
     */
    void load();
}
