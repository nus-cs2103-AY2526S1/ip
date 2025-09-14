package tsundere.ui;

import static tsundere.Config.HORIZONTAL_LINE;

import tsundere.command.AbstractCommand;
import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Interface that handles the printing of strings to the terminal.
 */
public class TerminalUi extends AbstractUi {
    public TerminalUi() {

    }

    @Override
    public void displayMessage(UiMessages type) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(UiMessages.getMessage(type));
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public void displayMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(s);
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public void displayMessage(UiMessages type, Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(UiMessages.getMessage(type, task));
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public void displayMessage(UiMessages type, TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(UiMessages.getMessage(type, tasks));
        System.out.println(tasks);
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public void displayMessage(UiMessages type, AbstractCommand command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(UiMessages.getMessage(type, command));
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public void displayMessage(InvalidFormatCommand.Format format) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(UiMessages.getMessage(UiMessages.HELP, format));
        System.out.println(HORIZONTAL_LINE);
    }
}
