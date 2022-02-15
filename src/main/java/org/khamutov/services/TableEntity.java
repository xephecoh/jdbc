package org.khamutov.services;

import java.util.ArrayList;
import java.util.List;


public class TableEntity {
    private List<String> columnsNames;

    private final List<Object> rowValues;

    public TableEntity() {
        this.rowValues = new ArrayList<>();

    }

    public void injectColumnsNamesAndInitializeList(List<String> columnsNames) {
        this.columnsNames = new ArrayList<>();
        this.columnsNames.addAll(columnsNames);
    }

    public List<Object> getRowValues() {
        return rowValues;
    }

    public void add(Object columnValue) {
        rowValues.add(columnValue);
    }

    public List<String> getColumnsNames() {
        return columnsNames;
    }
}
