package command;

import java.util.List;

import tasklist.TaskList;
import ui.UI;

public class TagCommand extends Command{
    private final int INDEX;
    private final List<String> TAGS;

    public TagCommand(int index, List<String> tags) {
        super(CommandType.TAG);
        this.INDEX = index;
        this.TAGS = tags;
    }

    /**
     * {@inheritDoc}
     * Tags the task at the specified index with a user-defined tag
     * and displays a confirmation message with the marked task.
     *
     * @param taskList the task list containing the task to mark
     */
    @Override
    public String execute(TaskList taskList) {
        assert INDEX < taskList.size() && INDEX > 0 : "index out of range in Command";
        taskList.tagTask(TAGS, INDEX);
        return UI.showMessage("Nice! I've tagged this task with the given tags:")
                + UI.showMessage(taskList.getAllTasks().get(INDEX).toString());
    }
}

