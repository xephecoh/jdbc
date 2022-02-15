package org.khamutov.services;

import org.khamutov.exceptions.WrongQueryFormatException;

public class Connector {
    public static void main(String[] args) throws WrongQueryFormatException {
        QueryHandler handler = new QueryHandler();
        handler.handle();
    }
}
