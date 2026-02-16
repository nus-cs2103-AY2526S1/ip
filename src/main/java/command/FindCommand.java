package command;

import java.util.ArrayList;
import java.util.List;

import task.Task;
import ui.Lmbd;

/**
 * Represents a command to search for tasks in the Lmbd application's task list.
 * It finds all tasks whose names match a given regular expression pattern.
 */
public class FindCommand extends Command {
    public FindCommand() {
        super("find", 1, "Finds all tasks with a name matching the regex pattern");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        List<Task> tasks = lmbd.tasks.find(String.join(" ", args));
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return String.join("\n", lines);
    }
}
