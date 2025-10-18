package peppa.command;

import peppa.task.TaskList;

public class Mark implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String input;

    public Mark(TaskList tasks, Storage storage, String input) {
        this.tasks = tasks;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'mark' requires an index. Usage: mark <task_number>";
            }
            int idx;
            try {
                idx = Integer.parseInt(parts[1].trim()) - 1;
            } catch (NumberFormatException e) {
                return "Error: invalid index for 'mark'. Please provide a number.";
            }
            if (idx < 0) {
                return "Error: task index must be positive.";
            }
            String result = tasks.markTask(idx);
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return "Error processing 'mark' command: " + e.getMessage();
        }
    }
}
