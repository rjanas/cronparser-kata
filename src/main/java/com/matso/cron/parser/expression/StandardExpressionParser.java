package com.matso.cron.parser.expression;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StandardExpressionParser {

    private int minValue;
    private int maxValue;
    private ListParser listParser;

    public StandardExpressionParser(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.listParser = new ListParser(minValue, maxValue);
    }

    public Set<Integer> parse(String expression) {
        if (expression.equals(Wildcard.ALL_VALUES)) {
            return IntStream.rangeClosed(this.minValue, this.maxValue)
                    .boxed()
                    .collect(Collectors.toSet());
        }

        return this.listParser.parse(expression);
    }
}
