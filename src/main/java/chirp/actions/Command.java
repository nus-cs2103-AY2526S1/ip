package chirp.actions;
import chirp.exceptions.InvalidCommandException;

/**
 * Represents enum of possible user commands
 */
public enum Command {
    BYE("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"),
    DELETE("delete"), DEADLINE("deadline"), EVENT("event"), TODO("todo"),
    FIND("find");

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Converts string keyword to command enum
     *
     * @param input the keyword
     * @return the corresponding command enum
     * @throws InvalidCommandException If input is not a valid keyword
     */
    public static Command fromString(String input) throws InvalidCommandException {
        for (Command cmd : Command.values()) {
            if (cmd.keyword.equals(input)) {
                return cmd;
            }
        }
        throw new InvalidCommandException();
    }

    /**
     * Returns keyword
     *
     * @return Underlying string of the Command Enum
     */
    public String getKeyword() {
        return keyword;
    }
}
