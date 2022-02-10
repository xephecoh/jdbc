package org.khamutov;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QueryHandler {
    private static String URL = "jdbc:postgresql://localhost:5432/test_DB";
    private static String USER_NAME = "test_user";
    private static String PASSWORD = "12345";
    private final ReportGenerator reportGenerator;
    private final QueryParser parser;
    private final RowMapper mapper;
    private final ConsolePrinter consolePrinter;

    public QueryHandler() {
        if (InitializeService.processEnvironmentVariables()) {
            Map<String, String> getenv = System.getenv();
            URL = getenv.get("URL");
            USER_NAME = getenv.get("USER_NAME");
            PASSWORD = getenv.get("PASSWORD");
        }
        reportGenerator = new ReportGenerator();
        parser = new QueryParser();
        mapper = new RowMapper();
        consolePrinter = new ConsolePrinter();
    }

    public void handle() throws WrongQueryFormatException {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Started");
            String query = scanner.next();
            QueryType queryType = parser.parseQuery(query);
            Statement statement = connection.createStatement();
            if (queryType.equals(QueryType.SELECT)) {
                ResultSet resultSet = statement.executeQuery(query);
                Map<String, List<Object>> queryContent = mapper.parseQueryResult(resultSet);
                reportGenerator.generateReport(queryContent);
                consolePrinter.printToConsole(queryContent);
            } else {
                int numberOfAffectedRows = statement.executeUpdate(query);
                reportGenerator.generateReport(numberOfAffectedRows, queryType);
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
