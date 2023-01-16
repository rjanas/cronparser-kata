package com.matso.cron.parser.field;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MonthParserTest {

    private MonthParser parser = new MonthParser();

    @Test
    void parse_returnsValues_whenNumberFromMonthRange() {
        Set<Integer> values = parser.parse("1,11-12");
        assertThat(values).containsOnly(1, 11, 12);
    }

    @Test
    void parse_throwsException_whenNumberOutOfMonthRange() {
        assertThatThrownBy(() -> parser.parse("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value out of range: 1-12");
    }
}
