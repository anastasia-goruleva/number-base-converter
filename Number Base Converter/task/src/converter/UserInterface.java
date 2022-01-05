package converter;

import java.math.BigInteger;
import java.util.Scanner;

import static converter.ConversionUtilities.Value;

public class UserInterface {
    private ConversionParameters parameters;
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.parameters = new ConversionParameters();
    }

    public void run() {
        String command;
        do {
            command = askCommand();
            if (command.charAt(0) == '/') {
                command = command.substring(1);
                if (parameters.isExists(command)) {
                    final var value = askNumber(command);
                    final var base = askBase(command);
                    System.out.printf(String.format("%%s: %%%c\n", parameters.getFormatCharacter(command)),
                            parameters.getResultLine(command),
                            parameters.getConverter(command).convert(value, base).get());
                    System.out.println();
                }
            } else {
                System.out.println("Command should start with '/'");
            }
        } while (!"exit".equals(command));
    }

    private BigInteger askBase(String command) {
        System.out.printf("Enter %s base: ", parameters.getBaseLine(command));
        return scanner.nextBigInteger();
    }

    private Value askNumber(String command) {
        System.out.printf("%s: ", parameters.getNumberLine(command));
        return parameters.getValueSupplier(command).apply(scanner);
    }

    private String askCommand() {
        System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        return scanner.next().toLowerCase();
    }
}
