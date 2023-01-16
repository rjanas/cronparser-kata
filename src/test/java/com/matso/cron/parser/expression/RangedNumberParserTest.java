package com.matso.cron.parser.expression;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RangedNumberParserTest {

    @Test
    void parse_returnsParsedInt() {
        RangedNumberParser parser = new RangedNumberParser(0, 5);
        int value = parser.parse("1");
        assertThat(value).isEqualTo(1);
    }

    @Test
    void parse_throwsException_whenNumberOutOfRange() {
        RangedNumberParser parser = new RangedNumberParser(0, 2);
        assertThatThrownBy(() -> parser.parse("4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Value out of range: 0-2");
    }

    @Test
    void parse_throwsException_whenNonNumericStringPassed() {
        RangedNumberParser parser = new RangedNumberParser(0, 1);
        assertThatThrownBy(() -> parser.parse("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot parse 'a' to a number");
    }
}
