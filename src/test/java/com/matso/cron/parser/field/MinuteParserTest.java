package com.matso.cron.parser.field;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MinuteParserTest {

    private MinuteParser parser = new MinuteParser();

    @Test
    void parse_returnsValues_whenFromMinutesRange() {
        Set<Integer> values = parser.parse("0,58-59");
        assertThat(values).containsOnly(0, 58, 59);
    }

    @Test
    void parse_throwsException_whenNumberOutOfMinutesRange() {
        assertThatThrownBy(() -> parser.parse("60"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value out of range: 0-59");
    }
}
