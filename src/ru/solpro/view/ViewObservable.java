package ru.solpro.view;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by danila on 30.11.2016.
 */
public class ViewObservable implements Observable {
    private List<Observer> observerList;

    public ViewObservable() {
        this.observerList = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}
