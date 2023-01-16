package com.matso.cron.parser.expression;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ListParser {

    private static final String TOKEN_SEPARATOR = ",";

    private RangedNumberParser rangedNumberParser;
    private RangeParser rangeParser;
    private StepParser stepParser;

    /**
     * @param minValue (inclusive) lower boundary of accepted values
     * @param maxValue (inclusive) upper boundary of accepted values
     */
    public ListParser(int minValue, int maxValue) {
        this.rangedNumberParser = new RangedNumberParser(minValue, maxValue);
        this.rangeParser = new RangeParser(this.rangedNumberParser);
        this.stepParser = new StepParser(this.rangedNumberParser, this.rangeParser);
    }

    public Set<Integer> parse(String expression) {
        Set<Integer> values = new HashSet<>();

        Stream.of(expression.split(TOKEN_SEPARATOR))
                .map(this::parseElement)
                .forEach(values::addAll);

        if (values.isEmpty()) {
            values.add(rangedNumberParser.getMinValue());
        }
        return values;
    }

    private Set<Integer> parseElement(String token) {
        if (token.contains(StepParser.STEP_SEPARATOR)) {
            return stepParser.parse(token);
        }

        if (token.contains(RangeParser.RANGE_SEPARATOR)) {
            return rangeParser.parse(token);
        }

        return Set.of(rangedNumberParser.parse(token));
    }
}
