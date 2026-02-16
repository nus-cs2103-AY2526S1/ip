package shadow.commands;

/**
 * Represents a command that terminates the application.
 * This command signals the application to exit when executed.
 * Typically, invoked when the user specifies an exit command in the application's input.
 */
public class TerminateCommand extends Command {

    @Override
    public String execute() {
        return "Terminating application";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
