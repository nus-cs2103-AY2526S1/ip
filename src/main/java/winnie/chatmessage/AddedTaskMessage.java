package winnie.chatmessage;

import winnie.task.Task;

/**
 * Message indicating that a task has been added.
 */
public class AddedTaskMessage implements Sendable {

    private final Task task;

    public AddedTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessageContent() {
        return "added: " + task.getDescription();
    }
}
