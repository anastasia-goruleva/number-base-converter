package converter;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import static converter.ConversionInterface.*;

public class UserInterface {
    private static final Map<String, ConversionParameters> parameters = Map.of(
            "from", new ConversionParameters(
                    "Enter number in decimal system",
                    "target",
                    "Conversion result",
                    scanner -> Value.of(scanner::nextInt),
                    Conversion::convertDecimalToBase, 's'),
            "to", new ConversionParameters(
                    "Enter source number",
                    "source",
                    "Conversion to decimal result",
                    scanner -> Value.of(scanner::next),
                    Conversion::convertToDecimalFromBase, 'd'));

    private static class ConversionParameters {
        public final String numberLine;
        public final String baseLine;
        public final String resultLine;

        public final Function<Scanner, Value> supplier;
        public final ConversionFunction converter;

        public final char formatCharacter;

        public ConversionParameters(String numberLine, String baseLine, String resultLine,
                                    Function<Scanner, Value> supplier, ConversionFunction converter,
                                    char formatCharacter) {
            this.numberLine = numberLine;
            this.baseLine = baseLine;
            this.resultLine = resultLine;
            this.supplier = supplier;
            this.converter = converter;
            this.formatCharacter = formatCharacter;
        }
    }

    public static String getBaseLine(String command) {
        return parameters.get(command).baseLine;
    }

    public static String getNumberLine(String command) {
        return parameters.get(command).numberLine;
    }

    public static String getResultLine(String command) {
        return parameters.get(command).resultLine;
    }

    public static Function<Scanner, Value> getValueSupplier(String command) {
        return parameters.get(command).supplier;
    }

    public static ConversionFunction getConverter(String command) {
        return parameters.get(command).converter;
    }

    public static boolean isExists(String command) {
        return parameters.containsKey(command);
    }

    public static char getFormatCharacter(String command) {
        return parameters.get(command).formatCharacter;
    }
}
