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

    private void saveData() {
        DataParser serializationData = new SerializationData();
        try {
            serializationData.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
