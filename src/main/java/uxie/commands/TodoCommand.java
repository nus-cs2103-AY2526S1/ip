package uxie.commands;

import uxie.exceptions.UxieIOException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;
import uxie.tasks.ToDo;

/**
 * Command for creating Todo tasks.
 *
 * @author junyan-k
 */
public class TodoCommand extends Command {

    /** Task to be added. */
    private ToDo task;

    /**
     * Generates TodoCommand with parameters for ToDo.
     * @see uxie.tasks.ToDo#ToDo(String)
     */
    public TodoCommand(String desc) {
        task = new ToDo(desc);
    }

    /**
     * Generates TodoCommand with parameters for ToDo.
     * @see uxie.tasks.ToDo#ToDo(boolean, String)
     */
    public TodoCommand(boolean isCompleted, String desc) {
        task = new ToDo(isCompleted, desc);
    }

    /**
     * {@inheritDoc}
     * Adds the Todo to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.storeTask(task);
        } catch (UxieIOException e) {
            ui.appendException(e);
        }
        ui.uxieAppendln(String.format("Alright. Task added:\n  %s\n"
                + "You have %s total tasks. Best of luck.", task, tasks.getSize()));
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if provided object is equal to this TodoCommand.
     * Two TodoCommands are equal if they contain equal Todos.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof TodoCommand) {
            return ((TodoCommand) o).task.equals(this.task);
        }
        return false;
    }

}
