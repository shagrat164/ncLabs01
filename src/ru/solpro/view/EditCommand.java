package ru.solpro.view;

import ru.solpro.controller.SystemException;

import java.io.IOException;

/**
 * Created by danila on 06.12.2016.
 */
public class EditCommand implements Command {
    @Override
    public boolean execute(String[] args) throws SystemException, IOException {
        return false;
    }

    @Override
    public void printHelp() {

    }

    @Override
    public String getName() {
        return "EDIT";
    }

    @Override
    public String getDescription() {
        return "Редактирование данных.";
    }
}
