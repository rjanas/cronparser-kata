package com.matso.cron.parser.expression;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Parser a range of numbers. E.g. 5-8 gives 5,6,7,8
 */
public class RangeParser {

    public static final String RANGE_SEPARATOR = "-";

    private RangedNumberParser valueParser;

    public RangeParser(RangedNumberParser valueParser) {
        this.valueParser = valueParser;
    }

    public Set<Integer> parse(String token) {
        NumberRange numberRange = parseToNumberRange(token);
        return IntStream.rangeClosed(numberRange.start(), numberRange.end())
                .boxed()
                .collect(Collectors.toSet());
    }

    public NumberRange parseToNumberRange(String token) {
        String[] rangeTokens = token.split(RANGE_SEPARATOR, 2);

        if (rangeTokens.length != 2) {
            throw new IllegalArgumentException(token);
        }

        int rangeStart = valueParser.parse(rangeTokens[0]);
        int rangeEnd = valueParser.parse(rangeTokens[1]);

        if (rangeStart >= rangeEnd) {
            throw new IllegalArgumentException(String.format("Invalid range '%s'", token));
        }
        return new NumberRange(rangeStart, rangeEnd);
    }
}
