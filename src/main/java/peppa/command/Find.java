package peppa.command;

import peppa.task.TaskList;

public class Find implements Command {
    private final TaskList tasks;
    private final String input;

    public Find(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'find' requires a search keyword. Usage: find <keyword>";
            }
            String toFind = parts[1].trim();
            return tasks.findTask(toFind);
        } catch (Exception e) {
            return "Error processing 'find' command: " + e.getMessage();
        }
    }
}
