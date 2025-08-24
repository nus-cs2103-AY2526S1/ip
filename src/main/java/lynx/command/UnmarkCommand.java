package lynx.command;

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

}

