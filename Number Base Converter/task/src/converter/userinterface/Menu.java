package converter.userinterface;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum Menu {
    ASK_BASE(UserInterface::getBasePrompt,"/exit", null, UserInterface::extractBases),
    ASK_NUMBER(UserInterface::getNumberPrompt, "/back", ASK_BASE, UserInterface::performConversion);

    public final Supplier<String> message;
    public final String returnCommand;
    public final Menu parent;
    public final Consumer<String> action;

    private final static Map<Menu, Menu> nextMenu =
            Map.of(ASK_BASE, ASK_NUMBER, ASK_NUMBER, ASK_NUMBER);

    Menu(Supplier<String> message, String returnCommand, Menu parent, Consumer<String> action) {
        this.message = message;
        this.returnCommand = returnCommand;
        this.parent = parent;
        this.action = action;
    }

    public Menu getNextMenu() {
        return nextMenu.get(this);
    }
}
