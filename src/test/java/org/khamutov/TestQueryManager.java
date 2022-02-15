package org.khamutov;

import org.junit.jupiter.api.Test;
import org.khamutov.enam.QueryType;
import org.khamutov.exceptions.WrongQueryFormatException;
import org.khamutov.services.ConsolePrinter;
import org.khamutov.services.QueryParser;
import org.khamutov.services.TableEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestQueryManager {
    @Test
    public void testValidator() {
        assertTrue((!System.getenv().get("URL").isEmpty() &
                !System.getenv().get("USER_NAME").isEmpty() &
                !System.getenv().get("PASSWORD").isEmpty()));
    }

    @Test
    public void testQueryParser() throws WrongQueryFormatException {
        QueryParser parser = new QueryParser();
        QueryType queryType = parser.parseQuery("SELECT * from person");
        assertEquals(QueryType.SELECT, queryType);
    }

    @Test
    public void testWrongQuery() {
        QueryParser parser = new QueryParser();
        assertThrows(WrongQueryFormatException.class, () -> parser.parseQuery("WRONG SELECT * from person"));
    }

    @Test
    public void testConsolePrinter() {
        ConsolePrinter cp = new ConsolePrinter();
        List<TableEntity> data = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            TableEntity tempTableEntity = new TableEntity();
            tempTableEntity.add(i);
            tempTableEntity.add("name" + i);
            data.add(tempTableEntity);
            tempTableEntity.injectColumnsNamesAndInitializeList(Arrays.asList("id", "name"));
        }
        cp.printToConsole(data);
    }


}
