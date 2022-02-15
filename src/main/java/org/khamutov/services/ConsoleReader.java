package org.khamutov.services;

import java.util.Scanner;

public class ConsoleReader {

    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
