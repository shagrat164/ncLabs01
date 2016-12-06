package ru.solpro.view;

import ru.solpro.controller.SystemException;

/**
 * Created by danila on 04.12.2016.
 */
abstract class AlwaysCommand {
    void error(String message) throws SystemException {
        throw new SystemException(message);
    }
}
