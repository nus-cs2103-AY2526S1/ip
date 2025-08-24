package lynx.command;

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

}
