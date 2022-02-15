package org.khamutov.services;

import org.khamutov.entities.QueryType;
import org.khamutov.entities.Row;
import org.khamutov.entities.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class ReportGenerator {
    private int counter;
    private final String path;

    public ReportGenerator(String path) {
        File[] files = new File(path).listFiles();
        if (files != null) {
            File file = files[files.length - 1];
            String name = file.getName();
            String lastReportNumber = name.replace("report_", "").replace(".html", "");
            counter = Integer.parseInt(lastReportNumber);
        }
        this.path = path;
    }

    public String newReport() {
        return "report_" + (++counter) + ".html";
    }


    public void generateReport(Table table) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                path + newReport()))
        ) {
            writer.write("<table>\r\n");
            writer.write("<tr>\r\n");
            List<String> columnsNames = table.getColumnsNames();
            for (String columnsName : columnsNames) {
                writer.write("<th>" + columnsName + "</th>\r\n");
            }
            writer.write("</tr>\r\n");
            for (Row tableEntity : table.getRows()) {
                writer.write("<tr>\r\n");
                for (Object value : tableEntity.getRowValues()) {
                    writer.write("<td>" + value + "</td>\r\n");
                }
                writer.write("</tr>");
            }
            writer.write("</table>");
        }
    }


    private String response(QueryType type, int rowAffected) {
        StringBuilder reportContent = new StringBuilder();
        if (rowAffected > 0) {
            reportContent.append(type.getParticiple())
                    .append(" ")
                    .append(rowAffected)
                    .append(" rows");
        } else {
            reportContent.append("No rows ")
                    .append(type.getParticiple());
        }
        return reportContent.toString();
    }

    public String generateReport(int rowAffected, QueryType type) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                path + newReport()))) {
            String reportContent = response(type, rowAffected);
            writer.write(reportContent);
            return reportContent;
        }
    }
}
