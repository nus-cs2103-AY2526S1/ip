package momo.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by returning all tasks in the task list as a string.
     * Tasks are numbered starting from 1.
     *
     * @param tasks the task list whose tasks are displayed.
     * @param ui the user interface used to generate messages (not used in this command).
     * @param storage the storage handler (not used in this command).
     * @return all tasks in the task list as a string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        String listMessage = IntStream.range(0, tasks.size())
                .mapToObj(x -> String.format("%d.%s", x + 1, tasks.getTask(x).toString()))
                .collect(Collectors.joining("\n"));
        return "Here are the tasks in your list:\n" + listMessage;
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as listing tasks does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
