package converter;

import java.util.Scanner;
import static converter.Conversion.*;
import static converter.UserInterface.*;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);

        String command;
        do {
            command = askCommand(scanner);
            if (command.charAt(0) == '/') {
                command = command.substring(1);
                switch (command) {
                    case "from": {
                        final var number = askDecimalNumber(scanner);
                        final var radix = askBase(scanner, command);
                        System.out.printf("%s: %s\n", getResultLine(command),
                                convertDecimalToBase(number, radix));
                        break;
                    }

                    case "to": {
                        final var sourceNumber = askSourceNumber(scanner);
                        final var radix = askBase(scanner, command);
                        System.out.printf("%s: %s\n", getResultLine(command),
                                convertToDecimalFromBase(sourceNumber, radix));
                        break;
                    }
                }
                System.out.println();
            } else {
                System.out.println("Command should start with '/'");
            }
        } while (!"exit".equals(command));
    }

    private static int askBase(Scanner scanner, String command) {
        System.out.printf("Enter %s base: ", getBaseLine(command));
        return scanner.nextInt();
    }

    private static int askDecimalNumber(Scanner scanner) {
        System.out.printf("%s: ", getNumberLine("from"));
        return scanner.nextInt();
    }

    private static String askSourceNumber(Scanner scanner) {
        System.out.printf("%s: ", getNumberLine("to"));
        return scanner.next();
    }

    private static String askCommand(Scanner scanner) {
        System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        return scanner.next().toLowerCase();
    }
}
