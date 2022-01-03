package converter;

import java.util.Map;

public class UserInterface {
    private static final Map<String, PromptSet> prompts = Map.of(
            "from", new PromptSet("Enter number in decimal system", "target", "Conversion result"),
            "to", new PromptSet("Enter source number", "source", "Conversion to decimal result"));

    private static class PromptSet {
        public final String numberLine;
        public final String baseLine;
        public final String resultLine;

        public PromptSet(String numberLine, String baseLine, String resultLine) {
            this.numberLine = numberLine;
            this.baseLine = baseLine;
            this.resultLine = resultLine;
        }
    }

    public static String getBaseLine(String command) {
        return prompts.get(command).baseLine;
    }

    public static String getNumberLine(String command) {
        return prompts.get(command).numberLine;
    }

    public static String getResultLine(String command) {
        return prompts.get(command).resultLine;
    }
}
