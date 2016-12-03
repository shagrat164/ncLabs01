package ru.solpro.model;

import java.util.ArrayList;
import java.util.TreeSet;

public class ElectricTrainController implements Controller<ElectricTrain> {
    private static ElectricTrainController instance;
    private TreeSet<ElectricTrain> electricTrains;

    private ElectricTrainController() {
        electricTrains = new TreeSet<>();
        // заполнение данными для первоначального тестирования
    }

    public static ElectricTrainController getInstance() {
        if (instance == null) {
            instance = new ElectricTrainController();
        }
        return instance;
    }

    @Override
    public ArrayList<ElectricTrain> search(String find) {
        return null;
    }

    @Override
    public ElectricTrain search(int numberTrain) {
        return null;
    }

    @Override
    public boolean add(ElectricTrain electricTrain) {
        return electricTrains.add(electricTrain);
    }

    public boolean add(int trainNumber) {
        ElectricTrain newTrain = new ElectricTrain(trainNumber);
        for (ElectricTrain electricTrain : electricTrains) {
            if (electricTrain.getTrainNumber() != newTrain.getTrainNumber()) {
                return electricTrains.add(newTrain);
            }
        }
        return false;
    }

    @Override
    public boolean del(int number) {
        for (ElectricTrain electricTrain : electricTrains) {
            if (electricTrain.getTrainNumber() == number) {
                return electricTrains.remove(electricTrain);
            }
        }
        return false;
    }

    @Override
    public boolean del(ElectricTrain electricTrain) {
        return electricTrains.remove(electricTrain);
    }

    @Override
    public TreeSet<ElectricTrain> get() {
        return electricTrains;
    }
}
