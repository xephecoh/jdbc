package org.khamutov.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowMapper {

    public Map<String, List<Object>> parseQueryResult(ResultSet resultSet) throws SQLException {
        Map<String, List<Object>> data = new HashMap<>();
        data.put("id",new ArrayList<Object>());
        data.put("name",new ArrayList<Object>());
        if (!resultSet.next()) {
            System.out.println("Empty result set");
        }
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            data.get("id").add(id);
            data.get("name").add(name);
        }
        return data;
    }
}
