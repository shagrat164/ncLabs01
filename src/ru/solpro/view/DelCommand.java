package ru.solpro.view;

/**
 * Команда удаления данных
 * @author Protsvetov Danila
 */
public class DelCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        if (args == null || args.length < 1 || args.length > 1) {
            System.out.println("Неверный аргумент у команды.");
            printHelp();
            return true;
        }
        switch (args[0].toUpperCase()) {
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
                printHelp();
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
        //TODO запилить метод
    }

    /**
     * Удаление поезда
     */
    private void delTrain() {
        //TODO запилить метод
    }

    /**
     * Удаление маршрута
     */
    private void delRoute() {
        //TODO запилить метод
    }

    /**
     * удаление станции
     */
    private void delStation() {
        //TODO запилить метод
    }
}
