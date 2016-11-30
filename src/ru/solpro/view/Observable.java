package ru.solpro.view;

/**
 * Created by danila on 30.11.2016.
 * Наблюдатель.
 */
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
