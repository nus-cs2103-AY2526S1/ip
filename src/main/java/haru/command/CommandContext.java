package haru.command;

import haru.model.TaskList;
import haru.ui.Ui;

/**
 * Holds context information for command execution.
 */
public class CommandContext {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a CommandContext with task list and Ui.
     *
     * @param taskList the task list
     * @param ui       the Ui
     */
    public CommandContext(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Returns the task list.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the Ui.
     *
     * @return the Ui
     */
    public Ui getUI() {
        return this.ui;
    }
}
