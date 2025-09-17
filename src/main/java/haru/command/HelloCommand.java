package haru.command;

import java.util.HashMap;

/**
 * Command to greet the user.
 */
public class HelloCommand extends Command {

    /**
     * Constructs a HelloCommand.
     *
     * @param ctx command context for execution
     */
    public HelloCommand(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    /**
     * Executes the command to show a greeting message.
     */
    @Override
    public void execute() {
        this.getUi().showHaruMessage("Hi~! I'm Haru. What can I do for you today?");
    }
}
