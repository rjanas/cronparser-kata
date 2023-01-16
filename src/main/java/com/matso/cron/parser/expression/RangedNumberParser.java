package com.matso.cron.parser.expression;

/**
 * Parses number from string and expects it be in a given range
 */
public class RangedNumberParser {

    private int minValue;
    private int maxValue;

    /**
     * @param minValue (inclusive) lower boundary of accepted values
     * @param maxValue (inclusive) upper boundary of accepted values
     */
    public RangedNumberParser(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int parse(String valueString) {
        int value;

        try {
            value = Integer.parseInt(valueString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format("Cannot parse '%s' to a number", valueString)
            );
        }

        if (!isInAcceptedRange(value)) {
            throw new IllegalArgumentException(
                    String.format("Value out of range: %d-%d", this.minValue, this.maxValue)
            );
        }
        return value;
    }

    private boolean isInAcceptedRange(int value) {
        return value >= this.minValue && value <= this.maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
