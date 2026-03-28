package nacho.commands;

import nacho.ChatContext;
import nacho.ExternalStorageController;
import nacho.exceptions.UserInputException;
import nacho.tasks.Task;

/**
 * Removes Completed Status from Task (if any)
 */
public class UnmarkTaskCommand implements Command {

    @Override
    public void execute(String[] args, ChatContext context) {
        int targetIndex = Integer.parseInt(args[0]) - 1;

        if (targetIndex < 0 || targetIndex >= context.getTaskList().getTotalTasks()) {
            throw new UserInputException("Targeted Task Number not in list");
        }

        Task targetTask = context.getTaskList().getTask(targetIndex);

        targetTask.unmarkCompleted();


        // Update External DB
        String storageRepresentation = context.getTaskList().getStorageRepresentation();
        ExternalStorageController.updateStore(storageRepresentation);

        String replyMessage = "OK, I've marked this task as not done yet:\n"
                + targetTask.toString().indent(context.getIndentLevel());
        context.reply(replyMessage);

    }
}
