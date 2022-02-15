package org.khamutov.services;

import org.khamutov.entities.QueryType;
import org.khamutov.entities.Table;
import org.khamutov.exceptions.WrongQueryFormatException;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class QueryHandler {

    private final ReportGenerator reportGenerator;
    private final QueryParser parser;
    private final RowMapper mapper;
    private final ConsolePrinter consolePrinter;
    private final InitializeService initializeService;

    public QueryHandler() {
        initializeService = new InitializeService();
        reportGenerator = new ReportGenerator(initializeService.getPath());
        parser = new QueryParser();
        mapper = new RowMapper();
        consolePrinter = new ConsolePrinter();
    }

    public void handle() throws WrongQueryFormatException {
        System.out.println("Started");
        try (Connection connection = DriverManager.getConnection(initializeService.getUrl(),
                initializeService.getUserName(), initializeService.getPassword());
             Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                try (Statement statement = connection.createStatement()) {
                    String query = scanner.nextLine();
                    QueryType queryType = parser.parseQuery(query);
                    if (queryType.equals(QueryType.SELECT)) {
                        ResultSet resultSet = statement.executeQuery(query);
                        Table queryContent = mapper.parseQueryResult(resultSet);
                        resultSet.close();
                        reportGenerator.generateReport(queryContent);
                        consolePrinter.printToConsole(queryContent);
                    } else {
                        int numberOfAffectedRows = statement.executeUpdate(query);
                        String report = reportGenerator.generateReport(numberOfAffectedRows, queryType);
                        System.out.println(report);
                    }
                }
                System.out.println("Query processed,enter new query");
            }
        } catch ( IOException | WrongQueryFormatException e) {
            System.out.println(e.getMessage());
        }catch (SQLException y){
            throw new RuntimeException("Unable to connect to db" + initializeService.getUrl());
        }
    }
}

