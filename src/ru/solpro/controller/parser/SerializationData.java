package ru.solpro.controller.parser;

import ru.solpro.controller.TrainModelController;
import ru.solpro.controller.RouteModelController;
import ru.solpro.controller.StationModelController;
import ru.solpro.model.Route;
import ru.solpro.model.Schedule;
import ru.solpro.model.Station;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Protsvetov Danila
 */
public class SerializationData implements DataParser {
    private String stationsFileName = "src\\ru\\solpro\\res\\stations.data";
    private String routesFileName = "src\\ru\\solpro\\res\\routes.data";
    private String trainsFileName = "src\\ru\\solpro\\res\\trains.data";

    @Override
    public void save() throws IOException {
        StationModelController stationModelController = StationModelController.getInstance();
        RouteModelController routeModelController = RouteModelController.getInstance();
        TrainModelController trainModelController = TrainModelController.getInstance();

        FileOutputStream fileOutputStream;
        GZIPOutputStream gzipOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(stationsFileName);
        gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        objectOutputStream = new ObjectOutputStream(gzipOutputStream);
        Station.serializeStatic(objectOutputStream);
        objectOutputStream.writeObject(stationModelController);
        objectOutputStream.flush();
        objectOutputStream.close();

        fileOutputStream = new FileOutputStream(routesFileName);
        gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        objectOutputStream = new ObjectOutputStream(gzipOutputStream);
        Route.serializeStatic(objectOutputStream);
        objectOutputStream.writeObject(routeModelController);
        objectOutputStream.flush();
        objectOutputStream.close();

        fileOutputStream = new FileOutputStream(trainsFileName);
        gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        objectOutputStream = new ObjectOutputStream(gzipOutputStream);
        Schedule.serializeStatic(objectOutputStream);
        objectOutputStream.writeObject(trainModelController);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public void load() throws IOException  {
        FileInputStream fileInputStream;
        GZIPInputStream gzipInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(stationsFileName);
            gzipInputStream = new GZIPInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(gzipInputStream);
            Station.deserializeStatic(objectInputStream);
            StationModelController.setInstance((StationModelController) objectInputStream.readObject());
            fileInputStream.close();

            fileInputStream = new FileInputStream(routesFileName);
            gzipInputStream = new GZIPInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(gzipInputStream);
            Route.deserializeStatic(objectInputStream);
            RouteModelController.setInstance((RouteModelController) objectInputStream.readObject());
            fileInputStream.close();

            fileInputStream = new FileInputStream(trainsFileName);
            gzipInputStream = new GZIPInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(gzipInputStream);
            Schedule.deserializeStatic(objectInputStream);
            TrainModelController.setInstance((TrainModelController) objectInputStream.readObject());
            fileInputStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }
}