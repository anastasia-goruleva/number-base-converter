package converter;

public class Conversion {
    private static final int DECIMAL_BASE = 10;

    public static String convertDecimalToBase(int number, int radix) {
        var result = "";
        final var digits = "0123456789ABCDEF";

        do {
            final var quotient = number / radix;
            final var remainder = number % radix;
            number = quotient;
            result = digits.charAt(remainder) + result;
        } while (number != 0);

        return result;
    }

    public static long convertToDecimalFromBase(String number, int radix) {
        var currentPower = 1L;
        var result = 0L;
        for (var i = number.length() - 1; i >= 0; --i, currentPower *= radix) {
            result += asValue(number.charAt(i)) * currentPower;
        }
        return result;
    }

    private static int asValue(char digit) {
        return Character.isDigit(digit) ? digit - '0' : Character.toUpperCase(digit) - 'A' + DECIMAL_BASE;
    }
}
