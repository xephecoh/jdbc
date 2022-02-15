package org.khamutov.services;

import org.khamutov.enam.QueryType;

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
            counter = Integer.parseInt(lastReportNumber) + 1;
        }
        this.path = path;
    }

    public String newReport() {
        return "report_" + (++counter) + ".html";
    }


    public void generateReport(List<TableEntity> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                path + newReport()))
        ) {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("<table>\r\n")
                    .append("<tr>\r\n");
            List<String> columnsNames = data.get(0).getColumnsNames();
            for (String columnsName : columnsNames) {
                reportContent.append("<th>").append(columnsName).append("</th>\r\n");
            }
            reportContent.append("</tr>\r\n");
            for (TableEntity tableEntity : data) {
                reportContent.append("<tr>\r\n");
                for (Object value : tableEntity.getRowValues()) {
                    reportContent.append("<td>")
                            .append(value)
                            .append("</td>\r\n");
                }
                reportContent.append("</tr>");
            }
            reportContent.append("</table>");
            writer.write(reportContent.toString());
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
                "src/main/resources/reports/" + newReport()))) {
            String reportContent = response(type, rowAffected);
            writer.write(reportContent);
            return reportContent;
        }
    }
}
