package com.matso.cron.parser;

import com.matso.cron.parser.field.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CronParserTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void parse_returnsCronExpression() {
        String cronExpressionStr = "1-2 */12 4 10 5-6 /usr/bin/find";

        CronParser cronParser = new CronParser();
        CronExpression cronExpression = cronParser.parse(cronExpressionStr);

        assertThat(cronExpression.getCommand()).isEqualTo("/usr/bin/find");
        Map<Field, Set<Integer>> fieldToExecutionValueMap = cronExpression.getFieldToExecutionValueMap();
        assertThat(fieldToExecutionValueMap.get(Field.MINUTE)).containsOnly(1, 2);
        assertThat(fieldToExecutionValueMap.get(Field.HOUR)).containsOnly(0, 12);
        assertThat(fieldToExecutionValueMap.get(Field.DAY_OF_MONTH)).containsOnly(4);
        assertThat(fieldToExecutionValueMap.get(Field.MONTH)).containsOnly(10);
        assertThat(fieldToExecutionValueMap.get(Field.DAY_OF_WEEK)).containsOnly(5, 6);
    }

    @Test
    public void parse_throwsException_whenInvalidExpression() {
        String cronExpressionStr = "1 2 3 4 /usr/bin/find";
        CronParser cronParser = new CronParser();

        assertThatThrownBy(() -> cronParser.parse(cronExpressionStr))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid argument");
    }
}
