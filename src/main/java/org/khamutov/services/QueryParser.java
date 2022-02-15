package org.khamutov.services;

import org.khamutov.entities.QueryType;
import org.khamutov.exceptions.WrongQueryFormatException;

public class QueryParser {

    public QueryType parseQuery(String query) throws WrongQueryFormatException {

        if (startsWithIgnoreCase(query, "select")) {
            return QueryType.SELECT;
        } else if (startsWithIgnoreCase(query, "delete")) {
            return QueryType.DELETE;
        } else if (startsWithIgnoreCase(query, "insert")) {
            return QueryType.INSERT;
        } else if (startsWithIgnoreCase(query, "update")) {
            return QueryType.UPDATE;
        }
        throw new WrongQueryFormatException(query + " has wrong structure");
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}
