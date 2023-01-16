package com.matso.cron.parser.expression;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RangeParserTest {

    private RangeParser parser = new RangeParser(new RangedNumberParser(0, 100));

    @Test
    void parse_returnsValuesFromRange_whenTwoDashSeparatedNumbersPassed() {
        Set<Integer> values = parser.parse("5-7");
        assertThat(values).containsOnly(5, 6, 7);
    }

    @Test
    void parse_throwsException_whenIncompleteRangePassed() {
        assertThatThrownBy(() -> parser.parse("-7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot parse '' to a number");
    }

    @Test
    void parse_throwsException_whenEmptyRangePassed() {
        assertThatThrownBy(() -> parser.parse("5-3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid range '5-3'");
    }
}
