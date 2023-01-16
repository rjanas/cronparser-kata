package com.matso.cron.parser.field;

import com.matso.cron.parser.expression.StandardExpressionParser;

import java.util.Set;

public class DayOfWeekParser implements FieldParser {

    private static final int MIN_DAY_OF_WEEK_VALUE = 0;
    private static final int MAX_DAY_OF_WEEK_VALUE = 6;

    private StandardExpressionParser parser;

    public DayOfWeekParser() {
        this.parser = new StandardExpressionParser(MIN_DAY_OF_WEEK_VALUE, MAX_DAY_OF_WEEK_VALUE);
    }

    public Set<Integer> parse(String expression) {
        return parser.parse(expression);
    }
}
