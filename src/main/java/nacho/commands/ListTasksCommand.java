package nacho.commands;

import nacho.ChatContext;

/**
 * Replies with a representation of all tasks added in TaskList
 */
public class ListTasksCommand implements Command {
    @Override
    public void execute(String[] args, ChatContext context) {

        String listMessage = "Here are your tasks in your list:\n" + context.getTaskList();
        context.reply(listMessage);
    }
}
