package winnie.chatmessage;

import winnie.tasklist.TaskList;

/**
 * Message indicating the current list of tasks.
 */
public class TaskListMessage implements Sendable {
    private final TaskList tasks;

    public TaskListMessage(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String getMessageContent() {
        if (tasks.getTaskCount() == 0) {
            return "No tasks found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            sb.append("\n     ")
                    .append((i + 1))
                    .append(".")
                    .append(tasks.getTask(i).toString());
        }
        return sb.toString();
    }
}
