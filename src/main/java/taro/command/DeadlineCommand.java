package taro.command;

import java.time.LocalDate;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Deadline;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code deadline} command which adds a {@link Deadline} task
 * to the current task list.
 * Example:
 *     deadline submit report /by 2025-09-10
 */

public class DeadlineCommand implements Command {
    private String input;

    /**
     * Constructs a {@code DeadlineCommand} with the specified input string.
     * @param input the raw user input that starts with {@code deadline}
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }
    /**
     * Executes the {@code deadline} command.
     * Parses the input string, creates a {@link Deadline} task with the given
     * description and due date, adds it to the {@link TaskList}, saves the list
     * to {@link Storage}, and displays a confirmation message through the {@link Ui}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to save the updated task list
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the command format is invalid or missing the {@code /by} keyword
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String body = input.substring(8).trim();
        int byPos = body.indexOf("/by");
        if (byPos == -1) {
            throw new TaroException("Opps!: Missing /by in deadline command.");
        }
        String desc = body.substring(0, byPos).trim();
        String by = body.substring(byPos + 3).trim();
        LocalDate byTime = LocalDate.parse(by);

        Task t = new Deadline(desc, byTime, false);
        tasks.add(t);
        storage.save(tasks);
        ui.showLine();
        ui.show(" Got it. I've added this task:");
        ui.show("   " + t);
        ui.show(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        return false;
    }
}
