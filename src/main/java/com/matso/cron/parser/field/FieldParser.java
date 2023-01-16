package com.matso.cron.parser.field;

import java.util.Set;

public interface FieldParser {

    Set<Integer> parse(String expression);
}
