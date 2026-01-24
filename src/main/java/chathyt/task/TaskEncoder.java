package chathyt.task;

/**
 * Utility class for encoding {@link Task} objects into string representations
 * suitable for persistent storage.
 */
public class TaskEncoder {
    /**
     * Encodes a {@link Task} into a string representation.
     * @param task The {@link Task} object to encode.
     * @return A string representation of the task.
     */
    public static String encode(Task task) {
        if (task instanceof Deadline) {
            return "D | " + (task.isDone ? 1 : 0) + " | "
                    + task.getDescription() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? 1 : 0) + " | "
                    + task.getDescription() + " | " + ((Event) task).getFrom() + ((Event) task).getTo();
        } else {
            return "T | " + (task.isDone ? 1 : 0) + " | "
                    + task.getDescription();
        }
    }
}
