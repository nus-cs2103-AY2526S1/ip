package winnie.chatmessage;

import winnie.tasklist.TaskList;

/**
 * Message to display found tasks matching a search keyword.
 */
public class FoundTasksMessage implements Sendable {
    private TaskList foundTasks;

    public FoundTasksMessage(TaskList foundTasks) {
        this.foundTasks = foundTasks;
    }

    @Override
    public String getMessageContent() {
        if (foundTasks.getTaskCount() == 0) {
            return "No matching tasks found.";
        }

        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < foundTasks.getTaskCount(); i++) {
            message.append(String.format("    %d.%s\n", i + 1, foundTasks.getTask(i).toString()));
        }

        return message.toString().trim();
    }
}
