package converter;

import java.util.Scanner;

public class Main {
    private static final int DECIMAL_BASE = 10;

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var number = askNumber(scanner);
        final var radix = askBase(scanner);
        System.out.printf("Conversion result: %s\n",
                convertDecimalToBase(number, radix));
    }

    private static String convertDecimalToBase(int number, int radix) {
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
    
    private static long convertToDecimalFromBase(String number, int radix) {
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

    private static int askBase(Scanner scanner) {
        System.out.print("Enter target base: ");
        return scanner.nextInt();
    }

    private static int askNumber(Scanner scanner) {
        System.out.print("Enter number in decimal system: ");
        return scanner.nextInt();
    }
}
