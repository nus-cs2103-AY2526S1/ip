package dwight.command;

import dwight.personality.PersonalityResponses;
import dwight.task.Task;
import dwight.task.TaskList;

/** Represents a command that deletes a specified task from the task list. */
public class DeleteCommand extends Command {

    /**
     * Executes the command by removing the task at the given index from the provided task list.
     *
     * @param list The task list containing the task to be deleted.
     * @param description The 1-based index of the task to delete.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to DeleteCommand.execute() cannot be null.";

        if (description == null || description.trim().isEmpty()) {
            String error = "You have to specify an index to delete";
            String message = PersonalityResponses.GENERAL_ERROR.getRandomResponse(error);
            return new CommandResponse(message, ResponseType.ERROR);
        }

        int index;
        try {
            index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            String error = "'" + description + "' is not a valid index...";
            String message = PersonalityResponses.GENERAL_ERROR.getRandomResponse(error);
            return new CommandResponse(message, ResponseType.ERROR);
        }

        if (index < 0 || index >= list.size()) {
            String error = "Index out of bounds.";
            String message = PersonalityResponses.GENERAL_ERROR.getRandomResponse(error);
            return new CommandResponse(message, ResponseType.ERROR);
        }

        Task task = list.delete(index);

        assert task != null : "TaskList.delete() returned a null task.";

        String message = PersonalityResponses.DELETE_SUCCESS.getRandomResponse(task);
        return new CommandResponse(message, ResponseType.SUCCESS);
    }
}
