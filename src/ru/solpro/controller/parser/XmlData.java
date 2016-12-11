package ru.solpro.controller.parser;

import ru.solpro.controller.RouteModelController;
import ru.solpro.controller.StationModelController;
import ru.solpro.controller.TrainModelController;
import ru.solpro.model.Route;
import ru.solpro.model.Station;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Работа с данными в xml
 * @author Protsvetov Danila
 */
public class XmlData implements DataParser {
    private String stationsFileName = "stations.xml";
    private String routesFileName = "routes.xml";
    private String trainsFileName = "trains.xml";
    private String archiveFileName = "data.zip";

    @Override
    public void save() {
        try {
            //станции
            File file = new File(stationsFileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            JAXBContext context = JAXBContext.newInstance(StationModelController.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(StationModelController.getInstance(), fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

            //маршруты
            file = new File(routesFileName);
            fileOutputStream = new FileOutputStream(file);

            context = JAXBContext.newInstance(RouteModelController.class);
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(RouteModelController.getInstance(), fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

            //поезда
            file = new File(trainsFileName);
            fileOutputStream = new FileOutputStream(file);

            context = JAXBContext.newInstance(TrainModelController.class);
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(TrainModelController.getInstance(), fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

            zip();
        } catch (JAXBException e) {
            System.out.println("Error: " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void load() {
        try {
            unzip();

            //станции
            File fileStations = new File(stationsFileName);
            FileInputStream fileInputStream = new FileInputStream(fileStations);

            JAXBContext context = JAXBContext.newInstance(StationModelController.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StationModelController.setInstance((StationModelController) unmarshaller.unmarshal(fileInputStream));

            fileInputStream.close();

            //маршруты
            File fileRoutes = new File(routesFileName);
            fileInputStream = new FileInputStream(fileRoutes);

            context = JAXBContext.newInstance(RouteModelController.class);
            unmarshaller = context.createUnmarshaller();
            RouteModelController.setInstance((RouteModelController) unmarshaller.unmarshal(fileInputStream));

            fileInputStream.close();

            //поезда
            File fileTrains = new File(trainsFileName);
            fileInputStream = new FileInputStream(fileTrains);

            context = JAXBContext.newInstance(TrainModelController.class);
            unmarshaller = context.createUnmarshaller();
            TrainModelController.setInstance((TrainModelController) unmarshaller.unmarshal(fileInputStream));

            fileInputStream.close();

            fileStations.delete();
            fileRoutes.delete();
            fileTrains.delete();

            // хак для решения проблемы с присвоением
            // id при открытии данных из файла
            Station.setCount(StationModelController.getInstance().getStations().last().getId());
            Route.setCount(RouteModelController.getInstance().getRoutes().last().getId());
        } catch (JAXBException e) {
            System.out.println("Error: " + e);
        }  catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    //архивирование файлов
    private void zip() throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(
                new FileOutputStream(
                        new File(archiveFileName)));

        File stationsFile = new File(stationsFileName);
        zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(stationsFile.getName())));
        zipWrite(new FileInputStream(stationsFile), zipOutputStream);

        File routesFile = new File(routesFileName);
        zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(routesFile.getName())));
        zipWrite(new FileInputStream(routesFile), zipOutputStream);

        File trainsFile = new File(trainsFileName);
        zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(trainsFile.getName())));
        zipWrite(new FileInputStream(trainsFile), zipOutputStream);

        zipOutputStream.flush();
        zipOutputStream.close();

        stationsFile.delete();
        routesFile.delete();
        trainsFile.delete();
    }

    //разархивирование файлов
    private void unzip() throws IOException {
        File file = new File(archiveFileName);
        ZipFile zipFile = new ZipFile(archiveFileName);
        Enumeration entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            unzipWrite(zipFile.getInputStream(zipEntry),
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(file.getParent(), zipEntry.getName()))));
        }
        zipFile.close();
    }

    private void zipWrite(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        in.close();
    }

    private void unzipWrite(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }
}
