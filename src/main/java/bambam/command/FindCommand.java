package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;

/**
 * Represents the find command which is a type of Command.
 */
public class FindCommand extends Command {
    private String taskListString;
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {

        assert (keyword != null && !keyword.isEmpty()) :
                "Keyword cannot be null or empty";

        taskListString = taskList.findTasks(keyword);
    }

    @Override
    public String getString() {
        return "🔎 Here are the tasks that match your search:\n" +
                taskListString;

    }
}
