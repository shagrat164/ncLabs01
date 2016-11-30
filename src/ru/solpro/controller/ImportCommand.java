package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public class ImportCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        return true;
    }

    @Override
    public void printHelp() {

    }

    @Override
    public String getName() {
        return "IMPORT";
    }

    @Override
    public String getDescription() {
        return "Импорт данных";
    }
}
