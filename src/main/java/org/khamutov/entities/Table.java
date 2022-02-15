package org.khamutov.entities;

import java.util.List;

public  class Table {
    private final List<String> columnsNames;
    private final List<Row> rows;
    public Table(List<String> columnsNames, List<Row> rows) {
        this.columnsNames = columnsNames;
        this.rows = rows;
    }

    public List<String> getColumnsNames() {
        return columnsNames;
    }

    public List<Row> getRows() {
        return rows;
    }
}