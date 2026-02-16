package chatbot.command;

import chatbot.ui.UI;

/**
 * ByeCommand implements the CommandExecutor interface and handles the bye command.
 */
public class ByeCommand implements CommandExecutor {
    private final UI ui;

    /**
     * Constructor, initializes a ByeCommand
     *
     * @param ui Ui of the application; must not be null.
     */
    public ByeCommand(UI ui) {
        assert ui != null : "UI must not be null";
        this.ui = ui;
    }

    @Override
    public String execute(String arg) {
        return this.ui.byeResponse();
    }
}
