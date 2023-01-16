package com.matso.cron.parser.field;

import com.matso.cron.parser.expression.StandardExpressionParser;

import java.util.Set;

public class MinuteParser implements FieldParser {

    private static final int MIN_MINUTE_VALUE = 0;
    private static final int MAX_MINUTE_VALUE = 59;

    private StandardExpressionParser parser;

    public MinuteParser() {
        this.parser = new StandardExpressionParser(MIN_MINUTE_VALUE, MAX_MINUTE_VALUE);
    }

    public Set<Integer> parse(String expression) {
        return parser.parse(expression);
    }
}
