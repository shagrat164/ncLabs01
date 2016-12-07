package ru.solpro.view;

import ru.solpro.controller.*;
import ru.solpro.controller.ElectricTrainModelController;
import ru.solpro.controller.StationModelController;
import ru.solpro.model.ElectricTrain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Protsvetov Danila
 */
public class AddCommand extends AlwaysCommand implements Command {
    @Override
    public boolean execute(String[] args) throws SystemException, IOException {
        if (args == null || args.length < 1 || args.length > 1) {
            printHelp();
            return true;
        }
        switch (args[0].toUpperCase()) {
            case "STATION":
                addStation();
                break;
            case "ROUTE":
                addRoute();
                break;
            case "TRAIN":
                addTrain();
                break;
            case "SCHEDULE":
                addSchedule();
                break;
            default:
                System.out.print("Неверный аргумент у команды. ");
                printHelp();
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println("Данная команда позволяет добавлять данные в систему.");
        System.out.println("Список параметров команды:");
        System.out.println("STATION - добавление новой станций.");
        System.out.println("ROUTE - добавление нового маршрута.");
        System.out.println("TRAIN - добавление нового поезда.");
        System.out.println("SCHEDULE - добавление расписания у определённого поезда.");
    }

    @Override
    public String getName() {
        return "ADD";
    }

    @Override
    public String getDescription() {
        return "Добавление информации";
    }

    /**
     * Добавление новой станции.
     * @throws SystemException
     * @throws IOException
     */
    private void addStation() throws SystemException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StationModelController stationController = StationModelController.getInstance();

        System.out.println("Для завершения операции добавления введите exit.");

        while (true) {
            System.out.print("\tНаименование станции: ");
            String nameStation = reader.readLine();
            if (isExitOperation(nameStation)) {
                return;
            }
            if (stationController.search(nameStation).isEmpty() && stationController.add(nameStation)) {
                System.out.println("Станция успешно добавлена.");
                if (!isAddMore()) {
                    return;
                }
            } else {
                error("Невозможно добавить станцию. Станция с таким названием существует.");
            }
        }
    }

    /**
     * Добавление нового маршрута
     * @throws SystemException
     * @throws IOException
     */
    private void addRoute() throws SystemException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RouteModelController routeController = RouteModelController.getInstance();

        System.out.println("Для завершения операции добавления введите exit.");

        while (true) {
            System.out.print("\tid станции отправления: ");
            String s = reader.readLine();
            if (isExitOperation(s)) {
                return;
            }
            Integer idDepStation = Integer.parseInt(s);

            System.out.print("\tid станции назначения: ");
            String s2 = reader.readLine();
            if (isExitOperation(s2)) {
                return;
            }
            Integer idArrStation = Integer.parseInt(s2);

            if (routeController.add(idDepStation, idArrStation)) {
                System.out.println("Маршрут успешно добавлен.");
                if (!isAddMore()) {
                    return;
                }
            } else {
                error("Невозможно добавить маршрут.");
            }
        }
    }

    /**
     * Добавление нового поезда
     * @throws SystemException
     * @throws IOException
     */
    private void addTrain() throws SystemException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ElectricTrainModelController electricTrainController = ElectricTrainModelController.getInstance();

        System.out.println("Для завершения операции добавления введите exit.");

        while (true) {
            System.out.print("\tНомер поезда: ");
            String strNumberTrain = reader.readLine();
            if (isExitOperation(strNumberTrain)) {
                return;
            }
            Integer numberTrain = Integer.parseInt(strNumberTrain);

//            System.out.print("\tid маршрута: ");
//            String strIdRoute = reader.readLine();
//            if (isExitOperation(strIdRoute)) {
//                return;
//            }
//            Integer idRoute = Integer.parseInt(strIdRoute);

            if (electricTrainController.search(numberTrain) == null && electricTrainController.add(numberTrain)) {
                System.out.println("Поезд успешно добавлен.");
                if (!isAddMore()) {
                    return;
                }
            } else {
                error("Невозможно добавить поезд.");
            }
        }
    }

    /**
     * Добавление расписания у определённого поезда
     * @throws SystemException
     * @throws IOException
     */
    private void addSchedule() throws SystemException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ElectricTrainModelController electricTrainController = ElectricTrainModelController.getInstance();

        System.out.println("Для завершения операции добавления введите exit.");

        while (true) {
            System.out.print("\tНомер поезда: ");
            String strNumberTrain = reader.readLine();
            if (isExitOperation(strNumberTrain)) {
                return;
            }
            Integer numberTrain = Integer.parseInt(strNumberTrain);
            ElectricTrain electricTrain = electricTrainController.search(numberTrain);
            if (electricTrain == null) {
                error("Невозможно добавить расписание. Поезда с номером " + numberTrain + " не существует.");
            }

            int routeId = 0;
            if (electricTrain.getTrainTimetable().isEmpty()) {
                System.out.print("У данного поезда отсутствует маршрут. Введите id маршрута: ");
                routeId = Integer.parseInt(reader.readLine());
            } else {
                routeId = electricTrain.getTrainTimetable().first().getRoute().getId();
            }

            System.out.print("\tДата отправления (dd.mm.yyyy): ");
            String strDateDep = reader.readLine();
            if (isExitOperation(strDateDep)) {
                return;
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate dateDep = LocalDate.parse(strDateDep, dateFormatter);

            System.out.print("\tВремя отправления (hh:mm): ");
            String strTimeDep = reader.readLine();
            if (isExitOperation(strTimeDep)) {
                return;
            }
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime timeDep = LocalTime.parse(strTimeDep, timeFormatter);

            LocalDateTime depDateTime = LocalDateTime.of(dateDep, timeDep);

            System.out.print("\tВремя движения до конечного пункта (часов): ");
            String strTimeArrHours = reader.readLine();
            if (isExitOperation(strTimeArrHours)) {
                return;
            }
            Integer timeArrHours = Integer.parseInt(strTimeArrHours);
            System.out.print("\tВремя движения до конечного пункта (минут): ");
            String strTimeArrMinutes = reader.readLine();
            if (strTimeArrMinutes.equals("")) {
                strTimeArrMinutes = "0";
            }
            Integer timeArrMinutes = Integer.parseInt(strTimeArrMinutes);

            if (timeArrMinutes == 0) {
                electricTrainController.addScheduleLine(routeId, numberTrain, depDateTime, timeArrHours);
                System.out.println("Расписание успешно добавлено.");
            } else {
                electricTrainController.addScheduleLine(routeId, numberTrain, depDateTime, timeArrHours, timeArrMinutes);
                System.out.println("Расписание успешно добавлено.");
            }

            if (!isAddMore()) {
                    return;
            }
        }
    }

    //запрос на еще одно добавление в базу
    private boolean isAddMore() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Добавить еще(y/n): ");
            String str = reader.readLine();
            if (str.equals("y")) {
                return true;
            } else if (str.equals("n")) {
                return false;
            }
        }
    }

    //проверка на команду выхода из процесса добавления
    private boolean isExitOperation(String string) {
        return string.length() == 0 || string.equals("exit") || string.equals("");
    }
}
