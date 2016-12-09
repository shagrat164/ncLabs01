package ru.solpro.controller.parser;

import java.io.IOException;

/**
 * Работа с данными
 * @author Protsvetov Danila
 */
public interface DataParser {

    /**
     * Маршаллизация
     * @throws IOException
     */
    void save() throws IOException;

    /**
     * Демаршаллизация
     * @throws IOException
     */
    void load() throws IOException;
}
