package woop.logic;

/**
 * Class to handle all user input and return needed information.
 */
public class Parser {
    private static final String SPACE = " ";
    /**
     * Retrieves the command from the given input.
     *
     * @param input The user input.
     * @return The command part of the input.
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Retrieves the Task descriptor from the user input depending on the task type.
     * Additionally, creates a Task object that represents the descriptor.
     * If descriptor is invalid, throws IllegalDescriptorException.
     *
     * @param input The user input.
     * @param type The Task type that this descriptor is for.
     * @return A new Task object that represents the descriptor.
     * @throws IllegalDescriptorException If descriptor syntax is invalid.
     * @throws UnknownCommandException If type given is invalid.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public static Task parseDescriptor(String input, TaskType type)
            throws IllegalDescriptorException, UnknownCommandException {
        int index = input.indexOf(SPACE);
        if (index < 0) {
            throw new IllegalDescriptorException();
        }
        String descriptor = getDescriptor(input, index);
        if (descriptor.isBlank()) {
            throw new IllegalDescriptorException();
        }
        // CHECKSTYLE OFF: Indentation
        return switch (type) {
            case TODO -> new TodoTask(descriptor);
            case EVENT -> getEventTask(descriptor);
            case DEADLINE -> getDeadlineTask(descriptor);
            default -> throw new UnknownCommandException();
        };
        // CHECKSTYLE ON: Indentation
    }

    private static String getDescriptor(String input, int index) {
        return input.substring(index + 1).trim();
    }

    private static DeadlineTask getDeadlineTask(String descriptor) throws IllegalDescriptorException {
        String[] tmp = descriptor.split(" /by ");
        String deadlineSubject = tmp[0];
        String dueDate = tmp[1];
        return new DeadlineTask(deadlineSubject, dueDate);
    }

    private static EventTask getEventTask(String descriptor) {
        String[] tmp = descriptor.split(" /from ", 2);
        String eventSubject = tmp[0];
        String[] tmp2 = tmp[1].split(" /to ", 2);
        String startTime = tmp2[0];
        String endTime = tmp2[1];
        return new EventTask(eventSubject, startTime, endTime);
    }

    /**
     * Retrieves the keyword from the user input.
     * The keyword is used to search for tasks that contain the keyword.
     *
     * @param input The user input.
     * @return The keyword to search for tasks.
     */
    public static String parseKeyword(String input) {
        String[] tmp = input.split(" ", 2);
        return tmp[1];
    }

    /**
     * Retrieves the tag index from the user input.
     *
     * @param input The user input.
     * @return The tag index for tagging the Task.
     */
    public static int parseTagIndex(String input) {
        int offset = 1;
        String[] tmp = input.split(" ", 3);
        return Integer.parseInt(tmp[1]) - offset; //go back to 0-indexing
    }

    /**
     * Retrieves the tag from the user input.
     *
     * @param input The user input.
     * @return The tag for tagging the Task.
     */
    public static String parseTag(String input) {
        String[] tmp = input.split(" ", 3);
        return tmp[2];
    }

    /**
     * Retrieves the index of the task from the user input.
     * The index of the task is used for commands that require to edit at specific indexes.
     *
     * @param input The user input.
     * @return The index of the Task in int.
     * @throws IllegalDescriptorException If descriptor syntax is not valid.
     */
    public static int parseIndex(String input) throws IllegalDescriptorException {
        if (!input.contains(" ")) {
            throw new IllegalDescriptorException();
        }
        int offset = 1;
        String tmp = input.split(" ")[1];
        return Integer.parseInt(tmp) - offset; // go back to 0-indexing
    }
}
