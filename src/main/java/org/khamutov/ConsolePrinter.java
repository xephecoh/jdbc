package org.khamutov;


import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Precision;
import io.bretty.console.table.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsolePrinter {
    public void printToConsole(Map<String, List<Object>> data) {


        List<Object> id = data.get("id");
        List<Object> name = data.get("name");
        Integer[] arrayOfIds= id.toArray(new Integer[0]);
        String[] arrayOfNames= name.toArray(new String[0]);
        ColumnFormatter<String> nameFormatter = ColumnFormatter.text(Alignment.LEFT, 10);
        ColumnFormatter<Number> ageFormatter = ColumnFormatter.number(Alignment.RIGHT, 3, Precision.ZERO);

        Table.Builder builder = new Table.Builder("Id", arrayOfIds, ageFormatter);
        builder.addColumn("Name", arrayOfNames, nameFormatter);
        Table table = builder.build();
        System.out.println(table);
    }

    public static void main(String[] args) {
        ConsolePrinter cp = new ConsolePrinter();
        Map<String, List<Object>> data = new HashMap<>();
        data.put("id",new ArrayList<>());
        data.put("name",new ArrayList<>());
        data.get("id").add(1);
        data.get("id").add(2);
        data.get("id").add(3);
        data.get("name").add("ivan");
        data.get("name").add("andrey");
        data.get("name").add("vasiliy");
        cp.printToConsole(data);
    }
}
