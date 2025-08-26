package lynx.command;

/**
 * Represents a "mark" command.
 * Stores the input and messages for different types of execution.
 */
public class MarkCommand extends LynxCommand {

    public MarkCommand(String input) {
        super(input);
    }

    public String getMessageForAll() {
        return "Marked all tasks";
    }

    public String getMessageByDate() {
        return "Marked tasks occurring on ";
    }

    public String getMessageById() {
        return "Marked task with id ";
    }

    public String getMessageByKeyword() {
        return "Marked tasks containing ";
    }

    public String getMessageByStatus() {
        return "Marked tasks with status ";
    }

}

