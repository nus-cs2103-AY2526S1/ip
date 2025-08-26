package lynx.command;

/**
 * Represents a "delete" command.
 * Stores the input and messages for different types of execution.
 */
public class DeleteCommand extends LynxCommand {

    public DeleteCommand(String input) {
        super(input);
    }

    public String getMessageForAll() {
        return "Removed all tasks";
    }

    public String getMessageByDate() {
        return "Removed tasks occurring on ";
    }

    public String getMessageById() {
        return "Removed task with id ";
    }

    public String getMessageByKeyword() {
        return "Removed tasks containing ";
    }

    public String getMessageByStatus() {
        return "Removed tasks with status ";
    }

}

