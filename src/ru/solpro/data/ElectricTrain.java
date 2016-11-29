package ru.solpro.data;

import java.util.Date;

/**
 * Электропоезд (Номер состава, маршрут, время отправления, путевое время)
 */
public class ElectricTrain {
    private int trainNumber;    //номер поезда
    private Route route;        //маршрут
    private Date departureTime; //время отправления
    private Date arrivalTime;   //время прибытия

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}
