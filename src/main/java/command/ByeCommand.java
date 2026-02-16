package command;

import ui.Lmbd;

/**
 * Represents a command to exit the application. When executed, it signals the
 * Lmbd application to terminate.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye", 0, "Exits the program");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        lmbd.exit();
        return "Bye. Hope to see you again soon!";
    }
}
