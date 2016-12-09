package ru.solpro.view;

import ru.solpro.controller.SystemException;
import ru.solpro.controller.parser.DataParser;
import ru.solpro.controller.parser.SerializationData;
import ru.solpro.controller.parser.XmlData;

import java.io.IOException;

/**
 * @author Protsvetov Danila
 */
public class ExitCommand implements Command {

    @Override
    public boolean execute(String[] args)  throws SystemException, IOException {
        saveData();
        return false;
    }

    @Override
    public void printHelp() {

    }

    @Override
    public String getName() {
        return "EXIT";
    }

    @Override
    public String getDescription() {
        return "Выход из программы";
    }

    private void saveData() throws IOException {
//        DataParser dataParser = new SerializationData();
        DataParser dataParser = new XmlData();
        dataParser.save();
    }
}
