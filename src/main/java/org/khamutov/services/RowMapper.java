package org.khamutov.services;


import org.khamutov.entities.Row;
import org.khamutov.entities.Table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMapper {

    public Table parseQueryResult(ResultSet resultSet) throws SQLException {
        List<String> columnNames = getColumnNames(resultSet);
        List<Row> rows = getRows(resultSet, columnNames);
        return new Table(columnNames, rows);
    }

    private List<Row> getRows(ResultSet resultSet, List<String> columnNames) throws SQLException {
        List<Row> rows = new ArrayList<>();
        while (resultSet.next()) {
            Row row = new Row();
            List<Object> rowValues = row.getRowValues();
            for (String columnName : columnNames) {
                Object cellValue = resultSet.getObject(columnName);
                rowValues.add(cellValue);
            }
            rows.add(row);
        }
        return rows;
    }

    private List<String> getColumnNames(ResultSet resultSet) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String columnName = rsMetaData.getColumnName(i);
            columnNames.add(columnName);
        }
        return columnNames;
    }


}
