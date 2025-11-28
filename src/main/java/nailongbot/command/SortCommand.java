package nailongbot.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import nailongbot.exception.InvalidTaskNumberException;
import nailongbot.exception.JkBotException;
import nailongbot.task.Task;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;

/**
 * command that sorts the current events by date
 */
public class SortCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        int size = tasks.size();

        // can abstract out?
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Task currentTask = tasks.getTask(j);
                Task nextTask = tasks.getTask(j + 1);
                LocalDateTime currentTaskDateTime = currentTask.getDate();
                LocalDateTime nextTaskDateTime = nextTask.getDate();
                if (currentTaskDateTime.isAfter(nextTaskDateTime)) {
                    swap(tasks, j, j + 1);
                }
            }
        }
        // list out the tasks in a string
        return listTasks(tasks);
    }

    /**
     * command that sorts swaps the current task and the next
     */
    public void swap(TaskList tasks, int current, int next) throws InvalidTaskNumberException {
        ArrayList<Task> memory = tasks.getMemory();

        // can abstract out?
        if (current < 0 || next < 0 || current >= memory.size() || next >= memory.size()) {
            throw new InvalidTaskNumberException("Invalid task index for swap!");
        }

        Task temp = memory.get(current);
        memory.set(current, memory.get(next));
        memory.set(next, temp);
    }

    /**
     * command that produces the list of all tasks
     */
    public String listTasks(TaskList tasks) throws InvalidTaskNumberException {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("No tasks in your list yet!\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
            }
        }
        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
