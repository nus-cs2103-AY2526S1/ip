package chlo.command;

import chlo.exception.ChloException;
import chlo.storage.Storage;
import chlo.task.*;
import chlo.ui.Ui;

/**
 * Represents an add command that adds a new task to the task list.
 * Supports adding Todo, Deadline, and Event tasks by parsing user input.
 */
public class AddCommand extends Command {
    private String input;
    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.startsWith("todo")) {
                Task task = new Todo(input.substring(5).trim());
                tasks.add(task);
                setString(ui.getAddTask(task, tasks.size()));
                storage.save(tasks);
            } else if (input.startsWith("deadline")) {
                int by = input.indexOf("/by");
                Task task = new Deadline(input.substring(9, by).trim(), input.substring(by + 4).trim());
                tasks.add(task);
                setString(ui.getAddTask(task, tasks.size()));
                storage.save(tasks);
            } else {
                int i = input.indexOf("/from");
                int j = input.indexOf("/to");
                Task task = new Event(input.substring(6, i).trim(), input.substring(i + 6, j).trim(), input.substring(j + 4).trim());
                tasks.add(task);
                setString(ui.getAddTask(task, tasks.size()));
                storage.save(tasks);
            }
        } catch (StringIndexOutOfBoundsException e) {
            setString(ui.getError("Invalid format for command.\nTodo <task name>\nDeadline <task name> /by DD/MM/YYYY HH:mm\nEvent <task name> /from DD/MM/YYYY HH:mm /to DD/MM/YYYY HH:mm"));
        } catch (ChloException e) {
            setString(ui.getError(e.getMessage()));
        }
    }
}