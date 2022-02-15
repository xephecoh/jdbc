package org.khamutov.services;

import org.khamutov.entities.Table;

import java.sql.*;


public class QueryExecutor {

    private final ConnectionService service;
    private final RowMapper mapper;

    public QueryExecutor() {
        this.mapper = new RowMapper();
        service = new ConnectionService();

    }

    public Table executeSelectQuery(String query) throws SQLException {
        try (Connection connection = service.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            return mapper.parseQueryResult(resultSet);
        }
    }

    public Integer executeUpdateQuery(String query) throws SQLException {
        try (Connection connection = service.createConnection();
             Statement statement = connection.createStatement()
        ) {
            return statement.executeUpdate(query);
        }
    }


}
