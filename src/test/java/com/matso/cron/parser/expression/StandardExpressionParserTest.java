package com.matso.cron.parser.expression;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardExpressionParserTest {

    private StandardExpressionParser parser = new StandardExpressionParser(0, 5);

    @Test
    void parse_returnsAllPossibleValues_whenAsteriskPassed() {
        Set<Integer> values = parser.parse("*");
        assertThat(values).containsOnly(0, 1, 2, 3, 4, 5);
    }

    @Test
    void parse_returnsResultOfListParsing_whenNoAsteriskPassed() {
        Set<Integer> values = parser.parse("1,3");
        assertThat(values).containsOnly(1, 3);
    }
}
