package Dan.Command;

import Dan.Task.TaskList;

public class ErrorCommand extends Command {
    String errorMessage;

    public ErrorCommand(String errorMessage) {
       this.errorMessage = errorMessage;
    }

    public CommandType getType() {
        return CommandType.ERROR;
    }

    public String execute(TaskList tasks) {
       return errorMessage;
    }
}
