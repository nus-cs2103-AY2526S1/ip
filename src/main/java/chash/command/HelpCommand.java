package chash.command;

import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

/** Command to display help information and a list of supported commands. */
public class HelpCommand extends Command {
    public HelpCommand() {}

    /**
     * {@inheritDoc}
     * Displays a help message describing all supported commands.
     */
    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        assert ui != null;

        ui.printMsg(
            "Crysis Heir Activity Sentre Hepdesk Commands:\n" +
            "HELP - Print this help message\n" +
            "BYE - Exit this program\n" +
            "LIST - List all tasks currently stored\n" +
            "MARK - Mark a task as done\n" +
            "UNMARK - Mark a task as not done\n" +
            "TODO - Create a TODO task with description\n" +
            "DEADLINE - Create a DEADLINE task with description (needs /by WHEN)\n" +
            "EVENT - Create a EVENT task with description (needs /from START /to END)\n" +
            "DELETE - Delete a task\n" +
            "FIND - Find a task based on its description\n" +
            "Thank you for joining the crysis warz"
        );
    }
}
