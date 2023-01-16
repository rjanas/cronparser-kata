package com.matso.cron.parser.field;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayOfMonthParserTest {

    private FieldParser parser = new DayOfMonthParser();

    @Test
    void parse_returnsValues_whenFromDayOfMonthRange() {
        Set<Integer> values = parser.parse("1,30-31");
        assertThat(values).containsOnly(1, 30, 31);
    }

    @Test
    void parse_throwsException_whenNumberOutOfDayOfMonthRange() {
        assertThatThrownBy(() -> parser.parse("32"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value out of range: 1-31");
    }
}
