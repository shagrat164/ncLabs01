package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public class SearchCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        return true;
    }

    @Override
    public void printHelp() {

    }

    @Override
    public String getName() {
        return "SEARCH";
    }

    @Override
    public String getDescription() {
        return "Поиск в расписании.";
    }
}
