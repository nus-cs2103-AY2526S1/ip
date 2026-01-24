package chathyt.task;

/**
 * Utility class for decoding stored task strings into {@link Task} objects.
 * <p>
 * The {@code TaskDecoder} interprets lines read from the storage file,
 * splits them into components, and reconstructs the appropriate
 * {@link Todo}, {@link Deadline}, or {@link Event} objects.
 * </p>
 */

public class TaskDecoder {
    /**
     * Decodes a line of text into a {@link Task} object.
     * @param line The encoded string representing a task.
     * @return A decoded {@link Task} object corresponding to the input line.
     * @throws IllegalArgumentException If the task type is unknown or the format is invalid.
     */
    public static Task decode(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
