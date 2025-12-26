package Chunky.Command;

import Chunky.Parser.Parser;
import Chunky.Task.Task;
import Chunky.Task.TaskList;
import Chunky.Storage.Storage;
import java.util.ArrayList;

public class FindCommand implements Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        try {
            String keyword = Parser.parseSearchKeyword(input);
            ArrayList<Task> foundTasks = tasks.findTasks(keyword);
            if (foundTasks.isEmpty()) {
                return "No matching tasks found.";
            } else {
                StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < foundTasks.size(); i++) {
                    response.append((i + 1)).append(". ").append(foundTasks.get(i)).append("\n");
                }
                return response.toString().trim();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}