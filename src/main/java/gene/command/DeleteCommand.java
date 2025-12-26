package gene.command;

import gene.TaskList;
import gene.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList tasksList) {
        assert tasksList != null;
        try {
            return tasksList.deleteTask(index);
        } catch (Exception e) {
            return Ui.SPACING + e.getMessage();
        }
    }
}
