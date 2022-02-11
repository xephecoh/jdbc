package org.khamutov.exceptions;

public class WrongQueryFormatException extends Exception {
    public WrongQueryFormatException(String message) {
        super(message);
    }
}
