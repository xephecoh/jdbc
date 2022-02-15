package org.khamutov.services;


import org.khamutov.entities.QueryType;
import org.khamutov.entities.Row;
import org.khamutov.entities.Table;

import java.util.List;


public class ConsolePrinter {
    public void printToConsole(Table table) {
        printHeaders(table);
        System.out.print("\r\n" + "-" .repeat(25) + "\r\n");
        printContent(table);
    }

    private void printHeaders(Table table) {
        List<String> columnsNames =table.getColumnsNames();
        System.out.print("|");
        columnsNames.forEach(e -> {
                    System.out.print(" " + e + " " .repeat(10 - e.length()) + "|");
                }
        );
        System.out.print("\r\n" + "-" .repeat(25) + "\r\n");
    }

    private void printContent(Table table) {
        for (Row tableEntity : table.getRows()) {
            System.out.print("|");
            for (Object cell : tableEntity.getRowValues()) {
                System.out.print(" " + cell + " " .repeat(10 - cell.toString().length()) + "|");
            }
            System.out.println("\r\n" + "-" .repeat(25));
        }
    }


}
