package com.matso.cron.parser.field;

import com.matso.cron.parser.expression.StandardExpressionParser;

import java.util.Set;

public class MonthParser implements FieldParser {

    private static final int MIN_MONTH_VALUE = 1;
    private static final int MAX_MONTH_VALUE = 12;

    private StandardExpressionParser parser;

    public MonthParser() {
        this.parser = new StandardExpressionParser(MIN_MONTH_VALUE, MAX_MONTH_VALUE);
    }

    public Set<Integer> parse(String expression) {
        return parser.parse(expression);
    }
}
