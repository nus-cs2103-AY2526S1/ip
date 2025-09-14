package tsundere;

import tsundere.command.AbstractCommand;
import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * A stub for the Ui interface. Used for testing.
 */
public class UiStub extends AbstractUi {
    private String output;

    @Override
    public void displayMessage(UiMessages type) {
        output = UiMessages.getMessage(type);
    }

    @Override
    public void displayMessage(InvalidFormatCommand.Format format) {
        output = UiMessages.getMessage(UiMessages.HELP, format);
    }

    @Override
    public void displayMessage(UiMessages type, Task task) {
        output = UiMessages.getMessage(type, task);
    }

    @Override
    public void displayMessage(UiMessages type, TaskList tasks) {
        output = UiMessages.getMessage(type, tasks);
    }

    @Override
    public void displayMessage(UiMessages type, AbstractCommand command) {
        output = UiMessages.getMessage(type, command);
    }

    @Override
    public void displayMessage(String s) {
        output = s;
    }

    public String getOutput() {
        return output;
    }
}
