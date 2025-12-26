package gene.command;

import gene.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasksList) {
        assert tasksList != null;
        return tasksList.unmarkTask(index);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UnmarkCommand)) return false;
        UnmarkCommand other = (UnmarkCommand) o;
        return this.index == other.index;
    }
}
