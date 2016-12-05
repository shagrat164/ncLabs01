package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public class DelCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        if (args == null) {
            printHelp();
            return true;
        }
        for (String arg : args) {
            switch (arg.toUpperCase()) {
                case "STATION":
                    delStation();
                    break;
                case "ROUTE":
                    delRoute();
                    break;
                case "TRAIN":
                    delTrain();
                    break;
                case "SCHEDULE":
                    delSchedule();
                    break;
                default:
                    System.out.print("Неверный аргумент у команды. ");
                    printHelp();
            }
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println("Данная команда позволяет удалять данные из системы.");
        System.out.println("Список параметров команды:");
        System.out.println("STATION - удаление станции.");
        System.out.println("ROUTE - удаление маршрута.");
        System.out.println("TRAIN - удаление поезда.");
        System.out.println("SCHEDULE - удаление расписания у определённого поезда.");
    }

    @Override
    public String getName() {
        return "DEL";
    }

    @Override
    public String getDescription() {
        return "Удаление информации";
    }

    /**
     * Удаление расписания у поезда
     */
    private void delSchedule() {

    }

    /**
     * Удаление поезда
     */
    private void delTrain() {

    }

    /**
     * Удаление маршрута
     */
    private void delRoute() {

    }

    /**
     * удаление станции
     */
    private void delStation() {

    }
}
