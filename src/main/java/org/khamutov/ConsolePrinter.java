package org.khamutov;


public class ConsolePrinter {
    public void printToConsole(String response, String query) {
        if (query.startsWith("SELECT")) {
            System.out.printf("%-6s%20s\n", "IDs", "Names");
            String[] lines = response.split(System.getProperty("line.separator"));
            for (String line : lines) {
                String[] s = line.split(" ");
                System.out.printf("%-6d%\n", s[0], s[1]," ");
            }
        } else {
            System.out.println(response);
        }
    }
}
