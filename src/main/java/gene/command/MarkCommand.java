package gene.command;

import gene.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasksList) {
        assert tasksList != null;
        return tasksList.markTask(index);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MarkCommand)) return false;
        MarkCommand other = (MarkCommand) o;
        return this.index == other.index;
    }
}
