package dwight.command;

import dwight.personality.PersonalityResponses;
import dwight.task.Task;
import dwight.task.TaskList;

/** Represents a command that marks a specified task in the task list as completed. */
public class MarkCommand extends Command {

    /**
     * Executes the command by marking the task at the given index in the provided task list as
     * completed.
     *
     * @param list The task list containing the task to be marked.
     * @param description The 1-based index of the task to mark as done.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to MarkCommand.execute() cannot be null.";

        if (description == null || description.trim().isEmpty()) {
            String error = "You have to specify an index to mark";
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

        Task task = list.mark(index);

        assert task != null : "TaskList.mark() returned a null task.";
        assert task.isMarked() : "Task was not marked as done after MarkCommand execution.";

        String message = PersonalityResponses.MARK_SUCCESS.getRandomResponse(task);
        return new CommandResponse(message, ResponseType.SUCCESS);
    }
}
