package ru.solpro.controller;

import java.util.Map;

/**
 * Created by Администратор on 30.11.2016.
 */

public class HelpCommand implements Command {
    private static final String MSG_COMMAND_NOT_FOUND = "Команда не найдена.";
    private static final String MSG_SPLIT = "==========================================";

    private Map<String, Command> commands = CommandController.getCommands();

    @Override
    public boolean execute(String[] args) {
        if (args == null) {
            System.out.println("Available commands:\n" + MSG_SPLIT);
            for (Command cmd : commands.values()) {
                System.out.println(cmd.getName() + ": " + cmd.getDescription());
            }
            System.out.println(MSG_SPLIT);
        } else {
            for (String cmd : args) {
                System.out.println("Help for command " + cmd + ":\n" + MSG_SPLIT);
                Command command = commands.get(cmd.toUpperCase());
                if (command == null) {
                    System.out.println(MSG_COMMAND_NOT_FOUND);
                } else {
                    command.printHelp();
                }
                System.out.println(MSG_SPLIT);
            }
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "HELP";
    }

    @Override
    public String getDescription() {
        return "Выводит список всех доступных комманд.";
    }
}
