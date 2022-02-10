package org.khamutov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestQueryManager {
    @Test
    public void testValidator(){
        assertTrue((!System.getenv().get("URL").isEmpty() &
                !System.getenv().get("USER_NAME").isEmpty() &
                !System.getenv().get("PASSWORD").isEmpty()));
    }
    @Test
    public void testQueryParser() throws WrongQueryFormatException {
        QueryParser parser = new QueryParser();
        QueryType queryType = parser.parseQuery("SELECT * from person");
        assertEquals(QueryType.SELECT,queryType);
    }
    @Test
    public void testWrongQuery() throws WrongQueryFormatException {
        QueryParser parser = new QueryParser();
        assertThrows(WrongQueryFormatException.class,()->parser.parseQuery("WRONG SELECT * from person"));
    }


}
