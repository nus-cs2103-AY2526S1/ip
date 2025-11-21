/**
 * Enum representing different types of tasks.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Converts a character to the corresponding TaskType.
     *
     * @param c The character representing the task type
     * @return The corresponding TaskType
     * @throws InvalidTaskTypeException if the character is not valid
     */
    public static TaskType fromChar(char c) throws InvalidTaskTypeException {
        switch (Character.toLowerCase(c)) {
        case 't':
            return TODO;
        case 'd':
            return DEADLINE;
        case 'e':
            return EVENT;
        default:
            throw new InvalidTaskTypeException(
                    "Unknown task type please re-enter based on the specified format");
        }
    }
}
