package nacho.commands;

import java.util.Arrays;

import nacho.ChatContext;
import nacho.exceptions.UserInputException;
import nacho.tasks.DeadlineTask;


/**
 * Parses User Input and adds new DeadlineTask object to TaskList if valid
 */
public class AddDeadlineCommand implements Command {

    @Override
    public void execute(String[] args, ChatContext context) {

        int byIndex = Arrays.asList(args).indexOf("/by");

        if (byIndex == -1 || byIndex == args.length - 1) {
            throw new UserInputException("Missing Deadline Date.\nSend 'help' for example");
        } else if (byIndex == 0) {
            throw new UserInputException("Missing Event Title\nSend 'help' for example");
        }

        String taskTitle = String.join(" ", Arrays.copyOfRange(args, 0, byIndex));
        String byDate = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length));

        DeadlineTask newDeadline = new DeadlineTask(taskTitle, byDate);
        context.getTaskList().addTask(newDeadline);

        String replyMessage = "Got it. I've added this task:\n"
                + newDeadline.toString().indent(context.getIndentLevel())
                + "\nNow you have " + context.getTaskList().getTotalTasks() + " tasks in the list.";

        context.reply(replyMessage);
    }
}
