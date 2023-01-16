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
        return """
                minute         %s
                hour           %s
                day of month   %s
                month          %s
                day of week    %s
                command        %s
                """.formatted(
                printValues(fieldToExecutionValueMap.get(Field.MINUTE)),
                printValues(fieldToExecutionValueMap.get(Field.HOUR)),
                printValues(fieldToExecutionValueMap.get(Field.DAY_OF_MONTH)),
                printValues(fieldToExecutionValueMap.get(Field.MONTH)),
                printValues(fieldToExecutionValueMap.get(Field.DAY_OF_WEEK)),
                this.command
        );
    }

    private String printValues(Set<Integer> executionValues) {
        return executionValues.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
