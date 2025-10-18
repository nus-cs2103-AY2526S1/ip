package peppa.command;

import peppa.task.TaskList;
import peppa.command.Storage;

public class Unmark implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String input;

    public Unmark(TaskList tasks, Storage storage, String input) {
        this.tasks = tasks;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'unmark' requires an index. Usage: unmark <task_number>";
            }
            int idx;
            try {
                idx = Integer.parseInt(parts[1].trim()) - 1;
            } catch (NumberFormatException e) {
                return "Error: invalid index for 'unmark'. Please provide a number.";
            }
            if (idx < 0) {
                return "Error: task index must be positive.";
            }
            String result = tasks.unmarkTask(idx);
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return "Error processing 'unmark' command: " + e.getMessage();
        }
    }
}
