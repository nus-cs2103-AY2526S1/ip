package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;
import alice.task.Deadline;

public class AddDeadlineCommand extends Command {
    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        String lowerCase = input.toLowerCase();
        String[] arr = input.split("/");
        String description = arr[0];

        if (arr.length < 4 || !arr[1].startsWith("by ")) {
            throw new AliceException("Deadline format should be: deadline <description> /by dd/MM/yyyy HHmm");
        }
        String by = String.format("%s/%s/%s", arr[1].substring(3), arr[2], arr[3].trim());
        Task deadline = new Deadline(description, by);

        tasks.addTask(deadline);
        storage.save(tasks);

        return tasks.printAdd(deadline);

    }
}
