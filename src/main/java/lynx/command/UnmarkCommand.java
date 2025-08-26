package lynx.command;

/**
 * Represents an "unmark" command.
 * Stores the input and messages for different types of execution.
 */
public class UnmarkCommand extends LynxCommand {

    public UnmarkCommand(String input) {
        super(input);
    }

    public String getMessageForAll() {
        return "Unmarked all tasks";
    }

    public String getMessageByDate() {
        return "Unmarked tasks occurring on ";
    }

    public String getMessageById() {
        return "Unmarked task with id ";
    }

    public String getMessageByKeyword() {
        return "Unmarked tasks containing ";
    }

    public String getMessageByStatus() {
        return "Unmarked tasks with status ";
    }

}

