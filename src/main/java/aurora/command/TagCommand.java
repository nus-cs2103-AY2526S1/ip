package aurora.command;

import aurora.task.Task;
import aurora.task.TaskList;

/**
 * Command to tag specified task from task list with given string.
 */
public class TagCommand implements Command {
    private final int index;
    private final String tag;

    /**
     * Creates a new {@code TagCommand}.
     *
     * @param index 1-based index of the task to add tag
     * @param tag Tag to be assigned
     */
    public TagCommand(int index, String tag) {
        assert index > 0 : "Index must be non-negative";
        assert tag != null && !tag.isBlank() : "Tag cannot be null or empty";
        this.index = index;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList list) {
        if (index == 0 || index > list.size()) {
            return "There is no task numbered " + index + ".";
        }

        Task task = list.get(index - 1);
        task.addTag(tag);
        return "Great. I've tagged this task as " + tag + ".\n" + task;
    }
}
