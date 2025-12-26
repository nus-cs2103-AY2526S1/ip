package Chunky.Command;

import Chunky.Parser.Parser;
import Chunky.Task.Task;
import Chunky.Task.TaskList;
import Chunky.Storage.Storage;

public class DeleteCommand implements Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        try {
            int index = Parser.parseTaskIndex(Parser.getArguments(input));
            if (index >= tasks.size()) {
                return "Invalid task number!";
            }
            Task deletedTask = tasks.get(index);
            tasks.delete(index);
            return "Okay! Removed: " + deletedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
