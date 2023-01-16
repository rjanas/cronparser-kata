package com.matso.cron.parser;

import com.matso.cron.parser.field.DayOfMonthParser;
import com.matso.cron.parser.field.DayOfWeekParser;
import com.matso.cron.parser.field.Field;
import com.matso.cron.parser.field.FieldParser;
import com.matso.cron.parser.field.HourParser;
import com.matso.cron.parser.field.MinuteParser;
import com.matso.cron.parser.field.MonthParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CronParser {

    private LinkedHashMap<Field, FieldParser> fieldParsers;

    public CronParser() {
        this.fieldParsers = new LinkedHashMap<>();
        this.fieldParsers.put(Field.MINUTE, new MinuteParser());
        this.fieldParsers.put(Field.HOUR, new HourParser());
        this.fieldParsers.put(Field.DAY_OF_MONTH, new DayOfMonthParser());
        this.fieldParsers.put(Field.MONTH, new MonthParser());
        this.fieldParsers.put(Field.DAY_OF_WEEK, new DayOfWeekParser());
    }

    public CronExpression parse(String cronExpression) {
        String[] fieldExpressions = cronExpression.split("\s");
        if (fieldExpressions.length != 6) {
            throw new IllegalArgumentException("Invalid argument");
        }

        Map<Field, Set<Integer>> fieldToResultMap = new HashMap<>();
        final AtomicInteger counter = new AtomicInteger();
        fieldParsers.forEach((field, fieldParser) -> {
            String fieldExpression = fieldExpressions[counter.getAndIncrement()];
            fieldToResultMap.put(field, fieldParser.parse(fieldExpression));
        });

        return new CronExpression(fieldToResultMap, fieldExpressions[5]);
    }

    public static void main(String[] args) {
        CronParser cronParser = new CronParser();
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing parameter");
        }
        CronExpression cronExpression = cronParser.parse(args[0]);
        System.out.println(cronExpression);
    }
}
