package meo.parser;

import meo.MeoException;
import meo.command.Command;
import meo.command.DeadlineCommand;
import meo.command.DeleteCommand;
import meo.command.EventCommand;
import meo.command.ExitCommand;
import meo.command.FindCommand;
import meo.command.ListCommand;
import meo.command.MarkCommand;
import meo.command.SortCommand;
import meo.command.TodoCommand;
import meo.command.UnmarkCommand;

/**
 * Parses commands from user's input.
 */
public class CommandParser {
    /**
     * Parses user's input
     *
     * @param command User input string.
     * @return Command to be executed.
     * @throws MeoException If task content or required task tags is missing.
     */
    public Command parser(String command) throws MeoException {
        switch (command.split(" ")[0]) {
            case "todo":
                if (isTaskContentMissing(command.substring(4).trim())) {
                    throw new MeoException(MeoException.taskMissing);
                }
                return new TodoCommand(command.substring(4).trim());
            case "deadline":
                return prepareDeadline(command);
            case "event":
                return prepareEvent(command);
            case "mark":
                String i = command.substring(4).trim();
                return new MarkCommand(i);
            case "unmark":
                String j = command.substring(6).trim();
                return new UnmarkCommand(j);
            case "list":
                return new ListCommand();
            case "delete":
                String k = command.substring(6).trim();
                return new DeleteCommand(k);
            case "find":
                String keyword = command.substring(4).trim();
                return new FindCommand(keyword);
            case "sort":
                if (command.split(" ")[1].equals("asc")) {
                    return new SortCommand(0);
                } else if ((command.split(" ")[1].equals("desc"))) {
                    return new SortCommand(1);
                }
            case "bye":
                return new ExitCommand();
            default:
                throw new MeoException(MeoException.commandUnknown);
        }
    }

    public Command prepareDeadline(String command) throws MeoException {
        int commandIndex = command.indexOf("/by");
        String[] deadlineTags = new String[1];
        if (commandIndex >= 0) {
            deadlineTags[0] = command.substring(commandIndex + 3).trim();
            if (isTaskContentMissing(command.substring(8, commandIndex - 1).trim())) {
                throw new MeoException(MeoException.taskMissing);
            }
            return new DeadlineCommand(command.substring(8, commandIndex - 1).trim(), deadlineTags);
        } else {
            throw new MeoException(MeoException.deadlineTime);
        }
    }

    public Command prepareEvent(String command) throws MeoException{
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");
        String[] eventTags = new String[2];
        if (fromIndex >= 0 && toIndex >= 0) {
            String taskDesc = command.substring(5, fromIndex - 1).trim();
            if (taskDesc.isEmpty()) {
                throw new MeoException(MeoException.taskMissing);
            }
            eventTags[0] = command.substring(fromIndex + 5, toIndex - 1).trim();
            eventTags[1] = command.substring(toIndex + 3).trim();
            return new EventCommand(taskDesc, eventTags);
        } else {
            throw new MeoException(MeoException.eventTime);
        }
    }

    public boolean isTaskContentMissing(String content) {
        return content.isEmpty();
    }
}
