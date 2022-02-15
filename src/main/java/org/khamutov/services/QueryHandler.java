package org.khamutov.services;

import org.khamutov.entities.QueryType;
import org.khamutov.entities.Table;
import org.khamutov.exceptions.WrongQueryFormatException;

import java.io.IOException;
import java.sql.*;


public class QueryHandler {

    private final ReportGenerator reportGenerator;
    private final QueryParser parser;
    private final ConsolePrinter consolePrinter;
    private final QueryExecutor queryExecutor;
    private final ConsoleReader reader;

    public QueryHandler() {
        InitializeService initializeService = new InitializeService();
        reportGenerator = new ReportGenerator(initializeService.getReportPath());
        parser = new QueryParser();

        consolePrinter = new ConsolePrinter();
        queryExecutor = new QueryExecutor();
        reader = new ConsoleReader();
    }

    public void handle() {
        System.out.println("Started");
        try{
            while (true) {
                String query = reader.readInput();
                QueryType queryType = parser.parseQuery(query);
                if (queryType.equals(QueryType.SELECT)) {
                    Table table = queryExecutor.executeSelectQuery( query);
                    reportGenerator.generateReport(table);
                    consolePrinter.printToConsole(table);
                } else {
                    Integer rowsAffected = queryExecutor.executeUpdateQuery( query);
                    reportGenerator.generateReport(rowsAffected, queryType);
                    System.out.println(queryType.getParticiple() + " " + rowsAffected + " " + "row (s)");
                }
                System.out.println("Query processed,enter new query");
            }
        } catch (IOException | WrongQueryFormatException | SQLException e) {
            System.out.println(e.getMessage() + e.getCause());
        }
    }
}

