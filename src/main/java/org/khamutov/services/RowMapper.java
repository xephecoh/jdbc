package org.khamutov.services;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMapper {

    public List<TableEntity> parseQueryResult(ResultSet resultSet) throws SQLException {

        List<String> columnNames = new ArrayList<>();
        List<TableEntity> entities = new ArrayList<>();
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String columnName = rsMetaData.getColumnName(i);
            columnNames.add(columnName);
        }

        while (resultSet.next()) {
            TableEntity tableEntity = new TableEntity();
            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);
                Object columnValue = resultSet.getObject(columnName);
                tableEntity.add(columnValue);
            }
            entities.add(tableEntity);
        }
        TableEntity tableEntity = entities.get(0);
        if (tableEntity != null) {
            tableEntity.injectColumnsNamesAndInitializeList(columnNames);
        }
        return entities;
    }
}
