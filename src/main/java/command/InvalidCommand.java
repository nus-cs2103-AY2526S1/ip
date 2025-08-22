package command;

import task.Task;

import java.util.List;

public class InvalidCommand implements Command{
    @Override
    public String execute(List<Task> list) {
        return "Invalid command.";
    }
}
