package dwight.command;

import dwight.personality.PersonalityResponses;
import dwight.task.TaskList;

/** Represents a command that displays all tasks currently stored in the task list. */
public class ListCommand extends Command {

    /**
     * Executes the command by printing the contents of the given task list to the console.
     *
     * @param list The task list whose tasks will be displayed.
     * @param description Not used by this command.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to ListCommand.execute() cannot be null.";
        String listStr = list.toString();
        if (listStr.isEmpty()) {
            String message =
                    PersonalityResponses.GENERAL_ERROR.getRandomResponse("You have no tasks.");
            return new CommandResponse(message, ResponseType.SUCCESS);
        } else {
            String message = PersonalityResponses.LIST_RESPONSE.getRandomResponse(listStr);
            return new CommandResponse(message, ResponseType.SUCCESS);
        }
    }
}
