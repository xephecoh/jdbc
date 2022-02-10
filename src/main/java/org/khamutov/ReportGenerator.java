package org.khamutov;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ReportGenerator {


    public String newReport() {
        File[] files = new File("C:\\Users\\xephe_000\\IdeaProjects\\Jdbc\\src\\main\\resources\\reports\\").listFiles();
        if (files != null) {
            File file = files[files.length - 1];
            String name = file.getName();
            String lastReportNumber = name.replace("report_", "").replace(".html", "");
            int newReportNumber = Integer.parseInt(lastReportNumber) + 1;
            return "report_" + newReportNumber + ".html";
        } else {
            return "report_1.html";
        }
    }

    public String generateReport(String query, Connection connection) throws IOException, SQLException, WrongQueryFormatException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                "C:\\Users\\xephe_000\\IdeaProjects\\Jdbc\\src\\main\\resources\\reports\\" + newReport()))
        ) {
            StringBuilder reportContent;
            if (query.startsWith("SELECT")) {
                StringBuilder printWriterContent = new StringBuilder();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                reportContent = new StringBuilder();
                reportContent.append("<table>\r\n")
                        .append("<tr>\r\n")
                        .append("<th>Id</th>\r\n")
                        .append("<th>Name</th>\r\n")
                        .append("</tr>\r\n");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    reportContent.append("<tr>\r\n")
                            .append("<td>")
                            .append(id)
                            .append("</td>\r\n")
                            .append("<td>")
                            .append(name)
                            .append("</td>\r\n");
                    printWriterContent.append(id)
                            .append(" ")
                            .append(name)
                            .append("\r\n");
                }
                reportContent.append("</table>");
                writer.write(reportContent.toString());
                return printWriterContent.toString();
            } else if (query.startsWith("DELETE")) {
                reportContent = new StringBuilder();
                Statement statement = connection.createStatement();
                int numberOfAffectedRows = statement.executeUpdate(query);
                if (numberOfAffectedRows == 1) {
                    reportContent.append("Deleted 1 row");
                    writer.write(reportContent.toString());
                    return reportContent.toString();
                } else if (numberOfAffectedRows > 0) {
                    reportContent.append("Deleted ")
                            .append(numberOfAffectedRows)
                            .append(" rows");
                } else {
                    reportContent.append("No rows deleted");
                }
                writer.write(reportContent.toString());
                return reportContent.toString();
            } else if (query.startsWith("UPDATE")) {
                reportContent = new StringBuilder();
                Statement statement = connection.createStatement();
                int numberOfAffectedRows = statement.executeUpdate(query);
                if (numberOfAffectedRows == 1) {
                    reportContent.append("Updated 1 row");
                    writer.write(reportContent.toString());
                    return reportContent.toString();
                } else if (numberOfAffectedRows > 0) {
                    reportContent.append("Updated ")
                            .append(numberOfAffectedRows)
                            .append(" rows");
                } else {
                    reportContent.append("No rows updated");
                }
                writer.write(reportContent.toString());
                return reportContent.toString();
            } else if (query.startsWith("INSERT")) {
                reportContent = new StringBuilder();
                Statement statement = connection.createStatement();
                int numberOfAffectedRows = statement.executeUpdate(query);
                if (numberOfAffectedRows > 0) {
                    reportContent.append("Insert  ")
                            .append(numberOfAffectedRows)
                            .append(" rows");
                } else {
                    reportContent.append("No rows affected");
                }
                writer.write(reportContent.toString());
                return reportContent.toString();
            }
        }
        throw new WrongQueryFormatException("Wrong structure of query :" + query);
    }

}
