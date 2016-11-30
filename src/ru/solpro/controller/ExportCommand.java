package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public class ExportCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        return true;
    }

    @Override
    public void printHelp() {

    }

    @Override
    public String getName() {
        return "EXPORT";
    }

    @Override
    public String getDescription() {
        return "Экспорт данных";
    }
}
