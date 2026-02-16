package chatbot.task;

import chatbot.exception.BotException;
import chatbot.exception.IncompleteArgumentException;
import chatbot.exception.InvalidArgumentException;
import chatbot.exception.InvalidCommandException;

/**
 * Factory class responsible for creating Task object.
 * Either TodoTask, DeadlineTask or EventTask based on user input.
 */
public class TaskFactory {

    /**
     * Creates a Task Object based on the user's task description.
     *
     * @param taskCreationCommand User command for tasking creation.
     * @return A created Task object.
     * @throws BotException If user uses an invalid command or invalid argument.
     */
    public static Task createTask(String taskCreationCommand) throws BotException {
        // taskCreationCommand String parsing
        taskCreationCommand = taskCreationCommand.trim();
        String[] inputParts = taskCreationCommand.split(" ", 2);
        String taskType = inputParts[0].trim();
        String description = (inputParts.length > 1) ? inputParts[1].trim() : "";

        // Switch based on taskType
        return switch (taskType) {
        case "event" -> parseEventTask(taskCreationCommand, description);
        case "deadline" -> parseDeadlineTask(taskCreationCommand, description);
        case "todo" -> parseTodoTask(description);
        default -> throw new InvalidCommandException("Invalid Command: I have zero clue what you are trying to say\n");
        };
    }

    private static EventTask parseEventTask(String taskCreationCommand, String description) throws BotException {
        if (taskCreationCommand.contains("/from") && taskCreationCommand.contains("/to")) {
            // Parsing of input for EventTask
            String[] descriptionArray = description.split("/from", 2);
            String taskName = descriptionArray[0].trim();
            String[] startEndArray = descriptionArray[1].split("/to", 2);
            boolean isMissingDates = startEndArray[0].trim().isEmpty() || startEndArray[1].trim().isEmpty();

            if (startEndArray.length < 2 || isMissingDates) {
                String errorMessage = "Incomplete Command: Do you not know when YOUR event starts and ends...\n";
                throw new InvalidArgumentException(errorMessage);
            } else {
                String from = startEndArray[0].trim();
                String to = startEndArray[1].trim();
                return new EventTask(taskName, from, to);
            }
        } else {
            throw new InvalidCommandException("Invalid Command: What are you even trying to say...\n");
        }
    }

    private static DeadlineTask parseDeadlineTask(String taskCreationCommand, String description) throws BotException {
        if (taskCreationCommand.contains("/by")) {
            // Parsing of input for EventTask
            String[] descriptionArray = description.split("/by", 2);
            boolean isMissingDescription = descriptionArray[0].trim().isEmpty();
            boolean isMissingDate = descriptionArray[1].trim().isEmpty();

            String errorMessage;
            if (isMissingDescription) {
                errorMessage = "Incomplete Command: What task???";
                throw new IncompleteArgumentException(errorMessage);
            } else if (isMissingDate) {
                errorMessage = "Incomplete Command: Deadline task but you don't know the deadline...\n";
                throw new IncompleteArgumentException(errorMessage);
            } else {
                String taskName = descriptionArray[0].trim();
                String deadline = descriptionArray[1].trim();
                return new DeadlineTask(taskName, deadline);
            }
        } else {
            throw new InvalidCommandException("Invalid Command: Can you even type properly...\n");
        }
    }

    private static ToDoTask parseTodoTask(String description) throws BotException {
        if (description.isEmpty()) {
            throw new IncompleteArgumentException("Incomplete Command: To do... what?\n");
        } else {
            return new ToDoTask(description);
        }
    }
}
