package nacho.commands;

import java.util.ArrayList;

import nacho.ChatContext;
import nacho.exceptions.UserInputException;
import nacho.tasks.Task;


/**
 * Finds a task by searching for a keyword in task description
 */
public class FindCommand implements Command {
    @Override
    public void execute(String[] args, ChatContext context) {

        String keyword = null;

        try {
            keyword = args[0];
        } catch (Exception e) {
            throw new UserInputException("Improper Format. Require one Keyword to search by!");
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Find and add matching tasks to list
        for (int i = 0; i < context.getTaskList().getTotalTasks(); i++) {
            Task currentTask = context.getTaskList().getTask(i);
            if (currentTask.getTitle().contains(keyword)) {
                matchingTasks.add(currentTask);
            }
        }

        // Reply Matching tasks
        context.reply(formReplyMessage(matchingTasks));
    }

    private String formReplyMessage(ArrayList<Task> matchingTasks) {
        // Print out matching tasks
        String replyMessage = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            String currentTaskLine = String.format("%d. %s\n", i, matchingTasks.get(i));
            replyMessage = replyMessage + currentTaskLine;
        }
        return replyMessage;
    }
}
