package org.khamutov;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestReader {
    List<String> fileList = new ArrayList<>();

    public String getLast() {
        String lastElement = fileList.get(fileList.size() - 1);
        String[] s1 = lastElement.split("_");
        String s2 = s1[1];
        int fileNumber = Integer.parseInt(s2.replace(".html", ""));
        int nextInt = fileNumber + 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s1[0])
                .append("_")
                .append(nextInt)
                .append(".html");
        return stringBuilder.toString();
    }

    public void processRequest(String input, String URL, String user, String password) throws SQLException, IOException {
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(
                             "C:\\Users\\xephe_000\\IdeaProjects\\Jdbc\\src\\main\\resources\\" + getLast()))
        ) {
            if (input.startsWith("DELETE")) {
                StringBuilder stringBuilder = new StringBuilder();
                Statement statement = connection.createStatement();
                int numberOfAffectedRows = statement.executeUpdate(input);
                if (numberOfAffectedRows > 0) {
                    stringBuilder.append("Deleted ").append(numberOfAffectedRows).append(" rows");
                } else {
                    stringBuilder.append("No rows affected");
                }
                writer.write(stringBuilder.toString());
            }
        }
    }
}
