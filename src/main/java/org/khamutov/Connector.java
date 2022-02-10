package org.khamutov;

public class Connector {
    public static void main(String[] args) throws WrongQueryFormatException {
       start();
    }

    private static void start() throws WrongQueryFormatException {
        QueryHandler handler = new QueryHandler();
        handler.handle();
    }
}
