package command;

import task.Task;

import java.util.List;

public class InvalidCommand implements Command{

    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(List<Task> list) {
        return message;
    }
}
