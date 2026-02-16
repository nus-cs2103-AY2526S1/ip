package alice.command;

import alice.Storage;
import alice.Task;
import alice.TaskList;
import alice.Ui;
import alice.exceptions.AliceException;
import alice.task.Event;

public class AddEventCommand extends Command {
    private final String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AliceException {
        String[] arr = input.split("/");
        String description = arr[0];

        if (arr.length < 6 || !arr[1].startsWith("from ") || !arr[4].startsWith("to ")) {
            throw new AliceException("Event format should be:" +
                    " event <description> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
        }
        String start = String.format("%s/%s/%s", arr[1].substring(5), arr[2], arr[3].trim());
        String end = String.format("%s/%s/%s", arr[4].substring(3), arr[5], arr[6].trim());
        //String at = start + "-" + end;
        Task event = new Event(description, start, end);

        tasks.addTask(event);
        storage.save(tasks);

        return tasks.printAdd(event);

    }
}
