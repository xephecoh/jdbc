package org.khamutov.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConsolePrinter {
    public void printToConsole(List<TableEntity> data) {
        printHeaders(data);
        System.out.print("\r\n" + "-" .repeat(25) + "\r\n");
        printContent(data);
    }

    private void printHeaders(List<TableEntity> data) {
        List<String> columnsNames = data.get(0).getColumnsNames();
        System.out.print("|");
        columnsNames.forEach(e -> {
                    System.out.print(" " + e + " " .repeat(10 - e.length()) + "|");
                }
        );
        System.out.print("\r\n" + "-" .repeat(25) + "\r\n");
    }

    private void printContent(List<TableEntity> data) {
        for (TableEntity tableEntity : data) {
            System.out.print("|");
            for (Object cell : tableEntity.getRowValues()) {
                System.out.print(" " + cell + " " .repeat(10 - cell.toString().length()) + "|");
            }
            System.out.println("\r\n" + "-" .repeat(25));
        }
    }


}
