package com.matso.cron.parser.expression;

import java.util.HashSet;
import java.util.Set;

/**
 * Parser a range of numbers with step. E.g. 0-20/10 gives 0, 10, 20
 */
public class StepParser {

    public static final String STEP_SEPARATOR = "/";

    private RangedNumberParser numberParser;
    private RangeParser rangeParser;

    public StepParser(RangedNumberParser rangedNumberParser, RangeParser rangeParser) {
        this.numberParser = rangedNumberParser;
        this.rangeParser = rangeParser;
    }

    public Set<Integer> parse(String token) {
        String[] rangeTokens = token.split(STEP_SEPARATOR, 2);

        NumberRange numberRange = getNumberRange(rangeTokens[0]);
        int step = numberParser.parse(rangeTokens[1]);

        Set<Integer> values = new HashSet<>();
        int currentValue = numberRange.start();
        while (currentValue < numberRange.end()) {
            values.add(currentValue);
            currentValue += step;
        }
        return values;
    }

    private NumberRange getNumberRange(String rangeToken) {
        if (rangeToken.equals(Wildcard.ALL_VALUES)) {
            return new NumberRange(numberParser.getMinValue(), numberParser.getMaxValue());
        }
        if (rangeToken.contains(RangeParser.RANGE_SEPARATOR)) {
            return this.rangeParser.parseToNumberRange(rangeToken);
        } else {
            int value = numberParser.parse(rangeToken);
            return new NumberRange(value, numberParser.getMaxValue());
        }
    }
}
