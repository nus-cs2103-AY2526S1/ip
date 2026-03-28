package nacho.commands;

import nacho.ChatContext;
import nacho.ExternalStorageController;
import nacho.exceptions.UserInputException;
import nacho.tasks.Task;

/**
 * Marks target task completed, specified by position in TaskList
 */
public class MarkTaskCommand implements Command {

    @Override
    public void execute(String[] args, ChatContext context) {
        int targetIndex = Integer.parseInt(args[0]) - 1;

        if (targetIndex < 0 || targetIndex >= context.getTaskList().getTotalTasks()) {
            throw new UserInputException("Targeted Task Number not in list");
        }

        Task targetTask = context.getTaskList().getTask(targetIndex);
        targetTask.markCompleted();

        // Update External DB
        String storageRepresentation = context.getTaskList().getStorageRepresentation();
        ExternalStorageController.updateStore(storageRepresentation);

        String replyMessage = "Nice! I've marked this task as done:\n"
                + targetTask.toString().indent(context.getIndentLevel());
        context.reply(replyMessage);

    }
}
