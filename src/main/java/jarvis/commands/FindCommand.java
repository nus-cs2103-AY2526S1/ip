package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks by keyword.
 * A find command searches the {@link TaskList} for tasks
 * whose description contains the given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;
    private String message; // what will be shown in the GUI

    /**
     * Constructs a {@code FindCommand}.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        List<Task> matches = new ArrayList<>();
        for (Task t : tasks.asList()) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }

        // Build message with Ui (returns string, doesn’t print)
        message = ui.formatFindResult(matches, keyword);
    }

    @Override
    public String getString() {
        return message != null ? message : "No tasks found containing: " + keyword;
    }
}
