package apleasebot.commands;

import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;

/**
 * Encapsulates the logic for when a user says help
 */
public class HelpCommand implements Command {
    @Override
    public String execute(TaskList tasks) {
        return APleaseBot.LINE
                + "List of commands\n"
                + "help - list commands\n"
                + "bye - close program\n"
                + "list - list tasks\n"
                + "mark <task-number>\n"
                + "unmark <task-number>\n"
                + "todo <task-name>\n"
                + "deadline <task-name> \\by <deadline>\n"
                + "event <event-name> \\from <time> \\to <time>\n"
                + "delete <task-number>\n"
                + "find <keyphrase>\n"
                + APleaseBot.LINE;
    }
}

