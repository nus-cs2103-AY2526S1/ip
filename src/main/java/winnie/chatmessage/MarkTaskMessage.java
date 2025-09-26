package winnie.chatmessage;

import winnie.task.Task;

/**
 * Message indicating that a task has been marked as done.
 */
public class MarkTaskMessage implements Sendable {
    private final Task task;

    public MarkTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessageContent() {
        return "Nice! I've marked this task as done:\n     " + task.toString();
    }
}
