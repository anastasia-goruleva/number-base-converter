package converter;

import java.util.Scanner;

import static converter.UserInterface.*;
import static converter.ConversionInterface.*;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        String command;
        do {
            command = askCommand(scanner);
            if (command.charAt(0) == '/') {
                command = command.substring(1);
                if (isExists(command)) {
                    final var value = askNumber(scanner, command);
                    final var base = askBase(scanner, command);
                    System.out.printf(String.format("%%s: %%%c\n", getFormatCharacter(command)),
                            getResultLine(command),
                            getConverter(command).convert(value, base).get());
                    System.out.println();
                }
            } else {
                System.out.println("Command should start with '/'");
            }
        } while (!"exit".equals(command));
    }

    private static int askBase(Scanner scanner, String command) {
        System.out.printf("Enter %s base: ", getBaseLine(command));
        return scanner.nextInt();
    }

    private static Value askNumber(Scanner scanner, String command) {
        System.out.printf("%s: ", getNumberLine(command));
        return getValueSupplier(command).apply(scanner);
    }

    private static String askCommand(Scanner scanner) {
        System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        return scanner.next().toLowerCase();
    }
}
