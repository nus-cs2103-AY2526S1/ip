package pagrobot.errors;

import pagrobot.tasks.Deadline;
import pagrobot.tasks.Event;
import pagrobot.tasks.ToDo;

/**
 * Indicates that the user has entered an invalid task argument.
 */
public class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(String taskType) {
        super(buildMessage(taskType));
    }

    /**
     * Decideds the error message.
     */
    private static String buildMessage(String taskType) {
        return switch (taskType.toLowerCase()) {
        case "deadline" -> "Invalid arguments. Usage:\n" + Deadline.inputArgument();
        case "event" -> "Invalid arguments. Usage:\n" + Event.inputArgument();
        case "todo" -> "Invalid arguments. Usage:\n" + ToDo.inputArgument();
        default -> "Unknown task type: " + taskType;
        };
    }
}
