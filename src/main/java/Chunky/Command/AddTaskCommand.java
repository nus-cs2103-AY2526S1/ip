package Chunky.Command;

import Chunky.Parser.Parser;
import Chunky.Task.Task;
import Chunky.Task.TaskList;
import Chunky.Storage.Storage;

public class AddTaskCommand implements Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        try {
            Task newTask = Parser.parseTask(input);
            tasks.add(newTask);
            return "Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}