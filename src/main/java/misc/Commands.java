package misc;

/**
 * Parent class of commands carried out by TaskBot.TaskBot
 */
public enum Commands {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    BYE,
    UPDATE,
    ONDATE;

    /**
     * Reads command input from user
     * @param input command given by user
     * @return
     * @throws TaskBotException
     */

    public static Commands fromString(String input) throws TaskBotException {
        try {
            return Commands.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskBotException("OOPS!! I don't know what you want me to do :-(" + input);
        }
    }
}
