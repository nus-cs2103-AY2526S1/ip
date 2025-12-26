package gene.command;

import gene.TaskList;

public class PrintCommand extends Command {
    private final String msg;

    public PrintCommand(String msg) {
        super(false);
        this.msg = msg;
    }

    @Override
    public String execute(TaskList tasksList) {
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PrintCommand)) return false;
        PrintCommand other = (PrintCommand) o;
        return this.msg.equals(other.msg);
    }
}
