package ru.solpro.controller;

/**
 * Created by Администратор on 30.11.2016.
 */
public interface Command {
    boolean execute(String[] args);
    void printHelp();
    String getName();
    String getDescription();
}
