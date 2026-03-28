package nacho.commands;

import nacho.ChatContext;


/**
 * Sorts the task in the database
 */
public class SortCommand implements Command {
    @Override
    public void execute(String[] args, ChatContext context) {

        context.getTaskList().sortTaskByTitle();

        // Reply Matching tasks
        context.reply("Sorted!");
    }
}
