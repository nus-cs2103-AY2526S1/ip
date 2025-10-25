package dwight.command;

import dwight.personality.PersonalityResponses;
import dwight.task.TaskList;

/**
 * Represents a command that searches for tasks in the task list whose descriptions contain a
 * specified keyword or phrase.
 */
public class FindCommand extends Command {

    /**
     * Executes the command by filtering the task list for tasks that match the given description
     * keyword or phrase, and prints the results to the console.
     *
     * @param list The task list to search within.
     * @param description The keyword or phrase to match against task descriptions.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to FindCommand.execute() cannot be null.";

        if (description == null || description.trim().isEmpty()) {
            String error = "Search keyword cannot be empty.";
            String message = PersonalityResponses.GENERAL_ERROR.getRandomResponse(error);
            return new CommandResponse(message, ResponseType.ERROR);
        }

        TaskList filtered = list.filtered(description);

        assert filtered != null : "TaskList.filtered returned a null TaskList object.";

        String message = PersonalityResponses.FIND_SUCCESS.getRandomResponse(filtered);
        return new CommandResponse(message, ResponseType.SUCCESS);
    }
}
