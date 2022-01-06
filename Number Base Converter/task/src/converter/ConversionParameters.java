package converter;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

import static converter.ConversionUtilities.Converter;
import static converter.ConversionUtilities.Value;

public enum ConversionParameters {
    FROM("Enter number in decimal system",
            "target",
            "Conversion result",
            scanner -> Value.of(scanner::nextBigInteger),
            Conversion::convertDecimalToBase, 's'),
    TO("Enter source number",
            "source",
            "Conversion to decimal result",
            scanner -> Value.of(scanner::next),
            Conversion::convertToDecimalFromBase, 'd');

    private final String numberLine;
    private final String baseLine;
    private final String resultLine;

    private final Function<Scanner, Value> supplier;
    private final Converter converter;

    private final char formatCharacter;

    ConversionParameters(String numberLine, String baseLine, String resultLine,
                         Function<Scanner, Value> supplier, Converter converter,
                         char formatCharacter) {
        this.numberLine = numberLine;
        this.baseLine = baseLine;
        this.resultLine = resultLine;
        this.supplier = supplier;
        this.converter = converter;
        this.formatCharacter = formatCharacter;
    }

    public static Set<String> existedCommands = Set.of(FROM.toString(), TO.toString());

    public String getBaseLine(String command) {
        return ConversionParameters.valueOf(command.toUpperCase()).baseLine;
    }

    public String getNumberLine(String command) {
        return ConversionParameters.valueOf(command.toUpperCase()).numberLine;
    }

    public String getResultLine(String command) {
        return ConversionParameters.valueOf(command.toUpperCase()).resultLine;
    }

    public Function<Scanner, Value> getValueSupplier(String command) {
        return ConversionParameters.valueOf(command.toUpperCase()).supplier;
    }

    public Converter getConverter(String command) {
        return ConversionParameters.valueOf(command.toUpperCase()).converter;
    }

    public boolean isExists(String command) {
        return existedCommands.contains(command);
    }

    public char getFormatCharacter(String command) {
        return ConversionParameters.valueOf(command.toUpperCase()).formatCharacter;
    }
}
