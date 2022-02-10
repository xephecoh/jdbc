package org.khamutov;

public class WrongQueryFormatException extends Exception {
    public WrongQueryFormatException(String message) {
        super(message);
    }
}
