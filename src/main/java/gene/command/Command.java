package gene.command;

import gene.TaskList;

public abstract class Command {
    private final boolean isExit;

    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract String execute(TaskList tasksList);

}
