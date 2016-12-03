package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public class SaveCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        return true;
    }

    @Override
    public void printHelp() {

    }

    @Override
    public String getName() {
        return "SAVE";
    }

    @Override
    public String getDescription() {
        return "Импорт данных";
    }
}
