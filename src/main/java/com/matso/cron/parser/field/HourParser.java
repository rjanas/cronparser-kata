package com.matso.cron.parser.field;

import com.matso.cron.parser.expression.StandardExpressionParser;

import java.util.Set;

public class HourParser implements FieldParser {

    private static final int MIN_HOUR_VALUE = 0;
    private static final int MAX_HOUR_VALUE = 23;

    private StandardExpressionParser parser;

    public HourParser() {
        this.parser = new StandardExpressionParser(MIN_HOUR_VALUE, MAX_HOUR_VALUE);
    }

    public Set<Integer> parse(String expression) {
        return parser.parse(expression);
    }
}
