package alice.command;

import alice.Storage;
import alice.TaskList;
import alice.Ui;

public class FindCommand extends Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTask(input);
    }

}
