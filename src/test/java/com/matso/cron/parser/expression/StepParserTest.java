package com.matso.cron.parser.expression;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class StepParserTest {

    private RangedNumberParser rangedNumberParser = new RangedNumberParser(0, 59);
    private StepParser parser = new StepParser(rangedNumberParser, new RangeParser(rangedNumberParser));

    @Test
    void parse_returnsValuesWithStep_whenAsteriskWithSlashPassed() {
        Set<Integer> values = parser.parse("*/15");
        assertThat(values).containsOnly(0, 15, 30, 45);
    }

    @Test
    void parse_returnsValuesByStepFromInitialValue_whenNumberWithSlashPassed() {
        Set<Integer> values = parser.parse("12/15");
        assertThat(values).containsOnly(12, 27, 42, 57);
    }

    @Test
    void parse_returnsValuesByStepFromRange_whenRangeWithSlashPassed() {
        Set<Integer> values = parser.parse("16-50/15");
        assertThat(values).containsOnly(16, 31, 46);
    }
}
