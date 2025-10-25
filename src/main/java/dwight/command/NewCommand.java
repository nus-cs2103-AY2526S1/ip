package dwight.command;

import dwight.personality.PersonalityResponses;
import dwight.task.DuplicateTaskException;
import dwight.task.IncompleteTaskException;
import dwight.task.Task;
import dwight.task.TaskList;
import dwight.task.TaskParser;
import dwight.task.TaskParserFactory;
import dwight.task.UnknownTaskTypeException;

/**
 * Represents a command that creates and adds a new task to the specified task list based on the
 * provided description.
 */
public class NewCommand extends Command {

    /**
     * Executes the command by parsing the task description, creating a corresponding task, and
     * adding it to the given task list.
     *
     * @param list The task list where the new task will be stored.
     * @param description The raw description containing the task type and details.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to NewCommand.execute() cannot be null.";

        if (description == null || description.trim().isEmpty()) {
            String error = "Task description cannot be empty.";
            String message = PersonalityResponses.GENERAL_ERROR.getRandomResponse(error);
            return new CommandResponse(message, ResponseType.ERROR);
        }

        String[] parts = description.split(" ", 2);

        String taskType = parts[0].trim();
        String taskDescription = parts.length > 1 ? parts[1].trim() : "";

        try {
            TaskParser parser = TaskParserFactory.createFileParser(taskType);
            Task task = parser.parse(taskDescription);

            assert task != null : "TaskParser returned a null task object.";

            list.add(task);

            String message = PersonalityResponses.ADD_SUCCESS.getRandomResponse(task);
            return new CommandResponse(message, ResponseType.SUCCESS);
        } catch (UnknownTaskTypeException | IncompleteTaskException | DuplicateTaskException e) {
            String errorMessage =
                    PersonalityResponses.GENERAL_ERROR.getRandomResponse(e.getMessage());
            return new CommandResponse(errorMessage, ResponseType.ERROR);
        }
    }
}
