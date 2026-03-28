package nacho.commands;

import nacho.ChatContext;
import nacho.exceptions.UserInputException;
import nacho.tasks.Task;

/**
 * Deletes Task from TaskList specified by position of task in list
 */
public class DeleteTaskCommand implements Command {

    @Override
    public void execute(String[] args, ChatContext context) {
        if (args.length == 0) {
            throw new UserInputException("Require index number of task to delete");
        }

        int targetIndex = Integer.parseInt(args[0]) - 1;

        if (targetIndex < 0 || targetIndex >= context.getTaskList().getTotalTasks()) {
            throw new UserInputException("Targeted Task Number not in list");
        }

        Task targetTask = context.getTaskList().getTask(targetIndex);
        context.getTaskList().deleteTask(targetIndex);

        String replyMessage = "Noted. I removed this task:\n"
                + targetTask.toString().indent(context.getIndentLevel());

        context.reply(replyMessage);
    }
}
