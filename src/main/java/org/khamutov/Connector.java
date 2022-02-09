package org.khamutov;


import java.io.*;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class Connector {
    private static String URL = "jdbc:postgresql://localhost:5432/test_DB";
    private static String USER_NAME = "test_user";
    private static String PASSWORD = "12345";
    private final ReportGenerator reportGenerator;
    private final ConsolePrinter consolePrinter;

    public Connector() {
        consolePrinter = new ConsolePrinter();
        this.reportGenerator = new ReportGenerator();
        if (InitializeService.processEnvironmentVariables()) {
            Map<String, String> getenv = System.getenv();
            URL = getenv.get("URL");
            USER_NAME = getenv.get("user");
            PASSWORD = getenv.get("password");
        }
    }

    public static void main(String[] args) throws IOException {
        new Connector().startPostgresAdminDemo();
    }

    public void startPostgresAdminDemo()  {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            Class.forName("com.postgresql.jdbc.driver");
            String query = scanner.next();
            String report = reportGenerator.generateReport(query, connection);
            consolePrinter.printToConsole(report, query);
        } catch (SQLException e) {
            System.out.println("Unable to connect to database with url " + URL);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
