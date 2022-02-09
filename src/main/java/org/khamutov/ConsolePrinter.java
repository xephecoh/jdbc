package org.khamutov;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsolePrinter {
    public void printToConsole(String response, String query) {
        if (query.startsWith("SELECT")) {
            System.out.printf("%-6s%-20s%6s\n", "IDs", "Names");
            String[] lines = response.split(System.getProperty("line.separator"));
            for (String line : lines) {
                String[] s = line.split(" ");
                System.out.printf("%-6d%-20s%6.2f\n", s[0], s[1]);
            }
        } else {
            System.out.println(response);
        }
    }
}
