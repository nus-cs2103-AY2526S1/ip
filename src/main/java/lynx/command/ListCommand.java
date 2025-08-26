package lynx.command;

/**
 * Represents a "list" command.
 * Stores the input and messages for different types of execution.
 */
public class ListCommand extends LynxCommand {

    public ListCommand(String input) {
        super(input);
    }

    public String getMessageForAll() {
        return "Here are the tasks in your list";
    }

    public String getMessageByDate() {
        return "Tasks occurring on ";
    }

    public String getMessageById() {
        return "Task with id ";
    }

    public String getMessageByKeyword() {
        return "Tasks containing ";
    }

    public String getMessageByStatus() {
        return "Tasks with status ";
    }

}
