package com.matso.cron.parser.field;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayOfWeekParserTest {

    private DayOfWeekParser parser = new DayOfWeekParser();

    @Test
    void parse_returnsValues_whenValuesFromDayOfWeekRange() {
        Set<Integer> values = parser.parse("0,5-6");
        assertThat(values).containsOnly(0, 5, 6);
    }

    @Test
    void parse_throwsException_whenValueOutOfDayOfMonthRange() {
        assertThatThrownBy(() -> parser.parse("9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value out of range: 0-6");
    }
}
