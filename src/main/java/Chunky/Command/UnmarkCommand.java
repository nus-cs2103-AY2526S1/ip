package Chunky.Command;

import Chunky.Parser.Parser;
import Chunky.Task.TaskList;
import Chunky.Storage.Storage;

public class UnmarkCommand implements Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        try {
            int index = Parser.parseTaskIndex(Parser.getArguments(input));
            if (index >= tasks.size()) {
                return "Invalid task number!";
            }
            tasks.get(index).unmarkDone();
            return "Okay! Unmarked the task:\n  " + tasks.get(index);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}