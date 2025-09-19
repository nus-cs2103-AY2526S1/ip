package sheares.command;

import sheares.Storage;
import sheares.TaskList;
import sheares.Ui;

/**
 * Class to represent a command to close/exit the chatbot
 */
public class ExitCommand extends Command {

    private boolean isExit;

    /**
     * Creates command that causes program to exit/close
     */
    public ExitCommand() {
        super();
    }
    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    @Override
    public String executeWithString(TaskList list, Ui ui, Storage storage) {
        return "    Bye. Hope to see you again soon!\n" + "    App will be closing in 3 seconds";
    }
}
