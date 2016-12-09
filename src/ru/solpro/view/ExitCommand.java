package ru.solpro.view;

import ru.solpro.controller.SystemException;
import ru.solpro.controller.parser.DataParser;
import ru.solpro.controller.parser.SerializationData;

import java.io.IOException;

/**
 * Created by Администратор on 30.11.2016.
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
        DataParser dataParser = new SerializationData();
        dataParser.save();
    }
}
