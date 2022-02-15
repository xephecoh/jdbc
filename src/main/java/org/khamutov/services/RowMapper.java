package org.khamutov.services;

import org.khamutov.entities.QueryType;
import org.khamutov.entities.Row;
import org.khamutov.entities.Table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMapper {

    public Table parseQueryResult(ResultSet resultSet) throws SQLException {

        List<String> columnNames = new ArrayList<>();
        List<Row> rows = new ArrayList<>();
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String columnName = rsMetaData.getColumnName(i);
            columnNames.add(columnName);
        }
        while (resultSet.next()) {
            Row row = new Row();
            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);
                Object cellValue = resultSet.getObject(columnName);
                List<Object> rowValues = row.getRowValues();
                rowValues.add(cellValue);
            }
            rows.add(row);
        }
        return new Table(columnNames,rows);
    }
}
