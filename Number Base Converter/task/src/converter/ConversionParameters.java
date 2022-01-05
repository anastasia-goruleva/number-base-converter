package converter;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import static converter.ConversionUtilities.*;

public class ConversionParameters {
    private static final Map<String, Parameters> parameters = Map.of(
            "from", new Parameters(
                    "Enter number in decimal system",
                    "target",
                    "Conversion result",
                    scanner -> Value.of(scanner::nextInt),
                    Conversion::convertDecimalToBase, 's'),
            "to", new Parameters(
                    "Enter source number",
                    "source",
                    "Conversion to decimal result",
                    scanner -> Value.of(scanner::next),
                    Conversion::convertToDecimalFromBase, 'd'));

    private static class Parameters {
        public final String numberLine;
        public final String baseLine;
        public final String resultLine;

        public final Function<Scanner, Value> supplier;
        public final Converter converter;

        public final char formatCharacter;

        public Parameters(String numberLine, String baseLine, String resultLine,
                          Function<Scanner, Value> supplier, Converter converter,
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

    public static Converter getConverter(String command) {
        return parameters.get(command).converter;
    }

    public static boolean isExists(String command) {
        return parameters.containsKey(command);
    }

    public static char getFormatCharacter(String command) {
        return parameters.get(command).formatCharacter;
    }
}
