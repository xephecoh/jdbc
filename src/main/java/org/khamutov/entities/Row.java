package org.khamutov.entities;

import java.util.ArrayList;
import java.util.List;

public  class Row {
    private final List<Object> rowValues;
    public Row() {
        this.rowValues = new ArrayList<>();
    }
    public List<Object> getRowValues() {
        return rowValues;
    }
}