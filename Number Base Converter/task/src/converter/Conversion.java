package converter;

import java.math.BigInteger;

import static converter.ConversionUtilities.*;

public class Conversion {
    private static final int DECIMAL_BASE = 10;

    public static Value convertDecimalToBase(Value value, BigInteger radix) {
        var result = "";
        BigInteger number = value.get();

        do {
            final var divisionResult = number.divideAndRemainder(radix);
            number = divisionResult[0];
            result = asDigit(divisionResult[1].intValue()) + result;
        } while (!BigInteger.ZERO.equals(number));

        return Value.of(result);
    }

    public static Value convertToDecimalFromBase(Value value, BigInteger radix) {
        var currentPower = BigInteger.ONE;
        var result = BigInteger.ZERO;
        final String s = value.get();
        for (var i = s.length() - 1; i >= 0; --i, currentPower = currentPower.multiply(radix)) {
            result = result.add(asValue(s.charAt(i)).multiply(currentPower));
        }
        return Value.of(result);
    }

    private static BigInteger asValue(char digit) {
        return BigInteger.valueOf(Character.isDigit(digit) ? digit - '0' : Character.toUpperCase(digit) - 'A' + DECIMAL_BASE);
    }

    private static char asDigit(int value) {
        return (char) (value < DECIMAL_BASE ? '0' + value : 'A' + (value - DECIMAL_BASE));
    }
}
