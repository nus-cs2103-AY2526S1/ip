package tsundere.ui;

import tsundere.command.AbstractCommand;
import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Abstract Ui class that defines methods for printing output of Tsundere to the user.
 */
public abstract class AbstractUi {
    public abstract void displayMessage(UiMessages type);
    public abstract void displayMessage(InvalidFormatCommand.Format format);
    public abstract void displayMessage(UiMessages type, Task task);
    public abstract void displayMessage(UiMessages type, TaskList tasks);
    public abstract void displayMessage(UiMessages type, AbstractCommand command);
    public abstract void displayMessage(String s);
}
