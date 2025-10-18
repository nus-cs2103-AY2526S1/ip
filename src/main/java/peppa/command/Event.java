package peppa.command;

import peppa.task.TaskList;

public class Event implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String input;

    public Event(TaskList tasks, Storage storage, String input) {
        this.tasks = tasks;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'event' requires a description and '/at'. Usage: event <description> /at <time>";
            }
            String result = tasks.addTask(input);
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return "Error processing 'event' command: " + e.getMessage();
        }
    }
}
