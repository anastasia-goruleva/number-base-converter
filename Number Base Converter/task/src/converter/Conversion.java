package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static converter.ConversionUtilities.*;

public class Conversion {
    private static final int DECIMAL_BASE = 10;
    private static final int PRECISION = 5;

    public static Value convertDecimalToBase(Value value, BigInteger radix) {
        var result = new StringBuilder();
        BigInteger number = value.get();

        do {
            final var divisionResult = number.divideAndRemainder(radix);
            number = divisionResult[0];
            result.append(asDigit(divisionResult[1].intValue()));
        } while (!BigInteger.ZERO.equals(number));

        return Value.of(result.reverse().toString());
    }

    public static Value convertToDecimalFromBase(Value value, BigInteger radix) {
        var currentPower = BigInteger.ONE;
        var result = BigInteger.ZERO;
        final String s = value.get();
        for (var i = s.length() - 1; i >= 0; --i, currentPower = currentPower.multiply(radix)) {
            result = result.add(BigInteger.valueOf(asValue(s.charAt(i))).multiply(currentPower));
        }
        return Value.of(result);
    }

    public static Value convertDecimalFractionToBase(Value value, BigDecimal base) {
        var result = new StringBuilder();
        BigDecimal fraction = value.get();

        for (var digitCount = 0; !BigDecimal.ZERO.equals(fraction) && digitCount < PRECISION; ++digitCount) {
            final var parts = fraction.multiply(base).divideAndRemainder(BigDecimal.ONE);
            result.append(asDigit(parts[0].intValue()));
            fraction = parts[1];
        }
        return Value.of(result.toString());
    }

    public static Value convertToDecimalFractionFromBase(Value value, BigDecimal base) {
        final var s = value.<String>get();
        var result = BigDecimal.ZERO;
        var currentPower = base;
        for (var i = 0; i < s.length(); ++i, currentPower = currentPower.multiply(base)) {
            final var digitValue = BigDecimal.valueOf(asValue(s.charAt(i)));
            result = result.add(digitValue.divide(currentPower, 2 * PRECISION, RoundingMode.UP));
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
