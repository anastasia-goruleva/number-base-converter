package converter.userinterface;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.IntStream;

import static converter.Conversion.convertDecimalToBase;
import static converter.Conversion.convertToDecimalFromBase;
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
        final var sourceRadix = BigInteger.valueOf(bases[0]);
        final var destinationRadix = BigInteger.valueOf(bases[1]);
        final var decimalForm = convertToDecimalFromBase(Value.of(command), sourceRadix);
        final var result = convertDecimalToBase(decimalForm, destinationRadix);
        System.out.printf("Conversion result: %s\n\n", result.<String>get());
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
