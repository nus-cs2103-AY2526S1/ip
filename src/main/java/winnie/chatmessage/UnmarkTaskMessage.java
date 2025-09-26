package winnie.chatmessage;

import winnie.task.Task;

/**
 * Message indicating that a task has been unmarked as done.
 */
public class UnmarkTaskMessage implements Sendable {

    private final Task task;

    /**
     * Creates a new UnmarkTaskMessage with the given task.
     *
     * @param task The task to unmark.
     */
    public UnmarkTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessageContent() {
        return "OK, I've marked this task as not done yet:\n     " + task.toString();
    }
}
