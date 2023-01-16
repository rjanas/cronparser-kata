package com.matso.cron.parser;

import com.matso.cron.parser.field.Field;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CronExpression {

    private Map<Field, Set<Integer>> fieldToExecutionValueMap;
    private String command;

    public CronExpression(Map<Field, Set<Integer>> fieldToExecutionValueMap, String command) {
        this.fieldToExecutionValueMap = fieldToExecutionValueMap;
        this.command = command;
    }

    public Map<Field, Set<Integer>> getFieldToExecutionValueMap() {
        return fieldToExecutionValueMap;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return String.join("\n",
                printField("minute", fieldToExecutionValueMap.get(Field.MINUTE)),
                printField("hour", fieldToExecutionValueMap.get(Field.HOUR)),
                printField("day of month", fieldToExecutionValueMap.get(Field.DAY_OF_MONTH)),
                printField("month", fieldToExecutionValueMap.get(Field.MONTH)),
                printField("day of week", fieldToExecutionValueMap.get(Field.DAY_OF_WEEK)),
                printRow("command", this.command)
        );
    }

    private String printField(String fieldName, Set<Integer> executionValues) {
        return printRow(fieldName, printValues(executionValues));
    }

    private String printRow(String label, String value) {
        return String.format("%-14s %s", label, value);
    }

    private String printValues(Set<Integer> executionValues) {
        return executionValues.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
