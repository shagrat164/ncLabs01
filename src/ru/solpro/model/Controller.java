package ru.solpro.model;

import java.util.ArrayList;
import java.util.TreeSet;

public interface Controller<T> {

    /**
     * Метод осуществляет поиск по строке поиска.
     *
     * @param find Параметры поиска.<br/>
     *             Может включать [*] - для пропуска символа и [?] - для пропуска нескольких символов.
     * @return Список найденных экземпляров данного типа
     */
    ArrayList<T> search(String find);

    /**
     * Метод поиска по идентификатору
     * @param id    Идентификатор для поиска
     * @return
     */
    T search(int id);

    boolean del(int id);

    boolean del(T o);

    boolean add(T o);

    TreeSet<T> get();
}
