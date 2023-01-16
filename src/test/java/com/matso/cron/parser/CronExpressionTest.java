package com.matso.cron.parser;

import com.matso.cron.parser.field.Field;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CronExpressionTest {

    @Test
    void toString_returnsCronInProperFormat() {
        String command = "/usr/bin/find";
        Map<Field, Set<Integer>> executionValues = new HashMap<>();
        executionValues.put(Field.MINUTE, Set.of(0, 15, 30, 45));
        executionValues.put(Field.HOUR, Set.of(0));
        executionValues.put(Field.DAY_OF_MONTH, Set.of(1, 15));
        executionValues.put(Field.MONTH, Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        executionValues.put(Field.DAY_OF_WEEK, Set.of(1, 2, 3, 4, 5));

        CronExpression cronExpression = new CronExpression(executionValues, command);
        assertThat(cronExpression.toString()).isEqualTo("""
                minute         0 15 30 45
                hour           0
                day of month   1 15
                month          1 2 3 4 5 6 7 8 9 10 11 12
                day of week    1 2 3 4 5
                command        /usr/bin/find
                """);
    }
}
