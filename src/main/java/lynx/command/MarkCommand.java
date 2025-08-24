package lynx.command;

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

}

