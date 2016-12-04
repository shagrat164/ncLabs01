package ru.solpro.controller;

import java.io.IOException;

/**
 * Created by Администратор on 30.11.2016.
 */
public interface Command {
    boolean execute(String[] args) throws SystemException, IOException;
    void printHelp();
    String getName();
    String getDescription();
}
