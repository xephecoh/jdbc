package org.khamutov.services;

import org.khamutov.enam.QueryType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ReportGenerator {

    public String newReport() {
        File[] files = new File("src/main/resources/reports/").listFiles();
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

    public void generateReport(Map<String, List<Object>> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                "src/main/resources/reports/" + newReport()))
        ) {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("<table>\r\n")
                    .append("<tr>\r\n")
                    .append("<th>Id</th>\r\n")
                    .append("<th>Name</th>\r\n")
                    .append("</tr>\r\n");
            System.out.println(data.get("id").get(0)); // problem line
            for (int i = 0; i < data.get("id").size() - 1; i++) {
                int id = (Integer) data.get("id").get(i);
                String name = (String) data.get("name").get(i);
                reportContent.append("<tr>\r\n")
                        .append("<td>")
                        .append(id)
                        .append("</td>\r\n")
                        .append("<td>")
                        .append(name)
                        .append("</td>\r\n");
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
