package ru.solpro.controller.parser;

import java.io.IOException;

/**
 * @author Protsvetov Danila
 */
public interface DataParser {
    void save() throws IOException;
    void load() throws IOException;
}
