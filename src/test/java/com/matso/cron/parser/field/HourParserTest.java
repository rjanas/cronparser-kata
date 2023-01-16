package com.matso.cron.parser.field;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HourParserTest {

    private HourParser parser = new HourParser();

    @Test
    void parse_returnsValues_whenFromHoursRange() {
        Set<Integer> values = parser.parse("0,22-23");
        assertThat(values).containsOnly(0, 22, 23);
    }

    @Test
    void parse_throwsException_whenNumberOutOfHoursRange() {
        assertThatThrownBy(() -> parser.parse("24"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value out of range: 0-23");
    }
}
