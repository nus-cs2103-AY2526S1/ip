package fish.command;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import fish.storage.Storage;
import fish.task.Deadline;
import fish.task.TaskList;
import fish.ui.Ui;

/**
 * Displays all deadline tasks sorted by their due date.
 */
public class SortDeadlineCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Deadline> deadlines = tasks.getTasks().stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .sorted(Comparator.comparing(Deadline::getDueDate))
                .collect(Collectors.toList());

        if (deadlines.isEmpty()) {
            ui.printIn("There are no deadline tasks to sort.");
            return;
        }

        ui.printIn("Here are your deadlines sorted by due date:");
        for (int i = 0; i < deadlines.size(); i++) {
            Deadline deadline = deadlines.get(i);
            ui.printIn((i + 1) + ". " + deadline);
        }
    }
}
