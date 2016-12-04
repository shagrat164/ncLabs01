package ru.solpro.controller;

public class SystemException extends Throwable {
    private String errorString;

    public SystemException(String message) {
        errorString = message;
    }

    @Override
    public String toString() {
        return errorString;
    }
}
