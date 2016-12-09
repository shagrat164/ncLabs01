package ru.solpro.controller.parser;

import javafx.application.Application;
import ru.solpro.controller.StationModelController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Работа с данными в xml
 * @author Protsvetov Danila
 */
public class XmlData implements DataParser {
    @Override
    public void save() throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(StationModelController.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(StationModelController.getInstance(), System.out);
        } catch (JAXBException exception) {
            Logger.getLogger(Application.class.getName()).
                    log(Level.SEVERE, "marshallExample threw JAXBException", exception);
        }
    }

    @Override
    public void load() throws IOException {

    }
}
