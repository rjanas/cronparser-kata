package com.matso.cron.parser.expression;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ListParserTest {

    private ListParser listParser = new ListParser(0, 59);

    @Test
    void parse_returnsZero_whenCommaPassed() {
        Set<Integer> values = listParser.parse(",");
        assertThat(values).containsOnly(0);
    }

    @Test
    void parse_returnsListedValues_whenCommaSeparatedNumbersPassed() {
        Set<Integer> values = listParser.parse("1,5,40");
        assertThat(values).containsOnly(1, 5, 40);
    }

    @Test
    void parse_returnsRangeAndValue_whenBothPassed() {
        Set<Integer> values = listParser.parse("3,6-8");
        assertThat(values).containsOnly(3, 6, 7, 8);
    }

    @Test
    void parse_returnsRangeAndStep_whenBothPassed() {
        Set<Integer> values = listParser.parse("1-2,*/20");
        assertThat(values).containsOnly(0, 1, 2, 20, 40);
    }
}
