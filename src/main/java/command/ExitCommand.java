package command;

import ui.ui;

/**
 * Command to terminate running process
 */
public class ExitCommand implements Command {
    private ui ui;

    public ExitCommand(ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public String toString() {
        return "Bye.Hope to see you again soon!";
    }
}
