package converter;

import static converter.ConversionUtilities.*;

public class Conversion {
    private static final int DECIMAL_BASE = 10;

    public static Value convertDecimalToBase(Value value, int radix) {
        var result = "";
        int number = value.get();

        do {
            final var quotient = number / radix;
            final var remainder = number % radix;
            number = quotient;
            result = asDigit(remainder) + result;
        } while (number != 0);

        return Value.of(result);
    }

    public static Value convertToDecimalFromBase(Value value, int radix) {
        var currentPower = 1L;
        var result = 0L;
        final String s = value.get();
        for (var i = s.length() - 1; i >= 0; --i, currentPower *= radix) {
            result += asValue(s.charAt(i)) * currentPower;
        }
        return Value.of(result);
    }

    private static int asValue(char digit) {
        return Character.isDigit(digit) ? digit - '0' : Character.toUpperCase(digit) - 'A' + DECIMAL_BASE;
    }

    private static char asDigit(int value) {
        return (char) (value < DECIMAL_BASE ? '0' + value : 'A' + (value - DECIMAL_BASE));
    }
}
