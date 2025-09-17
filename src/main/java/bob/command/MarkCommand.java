package bob.command;

import java.util.ArrayList;
import java.util.Arrays;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String SINGLE_TASK_PROMPT = "I've marked this task as done:\n\t";
    private static final String MULTIPLE_TASKS_PROMPT = "I've marked these tasks as done:\n\t";
    private static final String INVALID_TASK_ERROR = "Invalid task number(s) provided: ";
    
    private final Integer[] taskNumbers;

    /**
     * Constructs MarkCommand.
     *
     * @param taskNumbers the list of task numbers to be marked as done
     *                    (1-indexed).
     */
    public MarkCommand(Integer[] taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Marks tasks as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (super.isValidTasks(tasks, taskNumbers)) {
            this.markAllTaskDone(tasks, ui);
        } else {
            ui.showError(INVALID_TASK_ERROR + Arrays.toString(taskNumbers));
        }
    }

    private void markAllTaskDone(TaskList tasks, Ui ui) {
        ArrayList<Task> tasksMarked = new ArrayList<>();
        for (int taskNumber : taskNumbers) {
            tasksMarked.add(tasks.markDone(taskNumber - 1));
        }
        String prompt = taskNumbers.length == 1 ? SINGLE_TASK_PROMPT : MULTIPLE_TASKS_PROMPT;
        ui.printSection(prompt + TaskList.stringifyTasks(tasksMarked));
    }
}
