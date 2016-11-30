package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public class ExitCommand implements Command {

    @Override
    public boolean execute(String[] args) {
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
}
