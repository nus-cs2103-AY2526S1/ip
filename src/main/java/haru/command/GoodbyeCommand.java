package haru.command;

import java.util.HashMap;

import javafx.application.Platform;

/**
 * Command to exit the application.
 */
public class GoodbyeCommand extends Command {

    /**
     * Constructs a GoodbyeCommand.
     *
     * @param ctx command context for execution
     */
    public GoodbyeCommand(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    /**
     * Executes the command to stop the application.
     */
    @Override
    public void execute() {
        this.getUi().showHaruMessage("See you next time! Bye-bye~!");
        Platform.exit();
    }
}
