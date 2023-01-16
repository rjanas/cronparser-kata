package com.matso.cron.parser.field;

import com.matso.cron.parser.expression.StandardExpressionParser;

import java.util.Set;

public class DayOfMonthParser implements FieldParser {

    private static final int MIN_DAY_OF_MONTH_VALUE = 1;
    private static final int MAX_DAY_OF_MONTH_VALUE = 31;

    private StandardExpressionParser parser;

    public DayOfMonthParser() {
        this.parser = new StandardExpressionParser(MIN_DAY_OF_MONTH_VALUE, MAX_DAY_OF_MONTH_VALUE);
    }

    @Override
    public Set<Integer> parse(String expression) {
        return parser.parse(expression);
    }
}
