package converter.userinterface;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.IntStream;

import static converter.Conversion.*;
import static converter.ConversionUtilities.Value;

public class UserInterface {
    private final Scanner scanner;

    private static final int BASE_COUNT = 2;
    private static final Integer[] bases = new Integer[BASE_COUNT];

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        String command;
        var currentMenu = Menu.ASK_BASE;
        do {
            command = askCommand(currentMenu);
            if (currentMenu.returnCommand.equals(command)) {
                currentMenu = currentMenu.parent;
            } else {
                currentMenu.action.accept(command);
                currentMenu = currentMenu.getNextMenu();
            }
        } while (currentMenu != null);
    }

    public static void extractBases(String command) {
        final var baseStrings = command.split("\\s+");
        IntStream.range(0, BASE_COUNT)
                .forEach(i -> bases[i] = Integer.valueOf(baseStrings[i]));
    }

    public static void performConversion(String command) {
        final var numberParts = command.split("\\.");
        final var decimalIntPart =
                convertToDecimalFromBase(Value.of(numberParts[0]), BigInteger.valueOf(bases[0]));
        final var resultIntPart =
                convertDecimalToBase(decimalIntPart, BigInteger.valueOf(bases[1]));
        System.out.printf("Conversion result: %s", resultIntPart.<String>get());

        if (numberParts.length > 1) {
            final var decimalFracPart =
                    convertToDecimalFractionFromBase(Value.of(numberParts[1]), BigDecimal.valueOf(bases[0]));
            final var resultFracPart =
                    convertDecimalFractionToBase(decimalFracPart, BigDecimal.valueOf(bases[1]));
            System.out.printf(".%s",
                    resultFracPart.<String>get());
        }

        System.out.println();
    }

    private String askCommand(Menu menu) {
        System.out.print(menu.message.get());
        return scanner.nextLine().toLowerCase();
    }

    public static String getBasePrompt() {
        return "Enter two numbers in format: {source base} {target base} (To quit type /exit) ";
    }

    public static String getNumberPrompt() {
        return String.format("Enter number in base %d to convert to base %d (To go back type /back) ", bases);
    }
}
