package Chunky.Command;

import Chunky.Task.TaskList;
import Chunky.Storage.Storage;

public class ListCommand implements Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return "No tasks in your list yet!";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString().trim();
    }
}
