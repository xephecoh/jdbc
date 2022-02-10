package org.khamutov;

public class QueryParser {

    public QueryType parseQuery(String query) throws WrongQueryFormatException {
        if (query.startsWith("SELECT")) {
            return QueryType.SELECT;
        } else if (query.startsWith("DELETE")) {
            return QueryType.DELETE;
        } else if (query.startsWith("INSERT")) {
            return QueryType.INSERT;
        } else if (query.startsWith("UPDATE")) {
            return QueryType.UPDATE;
        }
        throw new WrongQueryFormatException(query + "has wrong structure");
    }
}
