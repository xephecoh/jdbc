package org.khamutov.services;

import org.khamutov.exceptions.WrongQueryFormatException;
import org.khamutov.services.QueryHandler;

public class Connector {
    public static void main(String[] args) throws WrongQueryFormatException {
       start();
    }

    private static void start() throws WrongQueryFormatException {
        QueryHandler handler = new QueryHandler();
        handler.handle();
    }
}
