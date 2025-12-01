package exceptions;

/**
 * Exception thrown when user did not specify what command.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs an error
     * Prints specific message
     */
    public InvalidInputException() {
        super("Aiyo! Wrong recipe! No sweet is made "
                + "\nFollow the following recipes: "
                + "\n1. To add sweets (Tasks): "
                + "\n     - 'todo <task>'"
                + "\n     - 'deadline <task> /by <dd-mm-yyyy HHmm>'"
                + "\n     - 'events <task> /from <dd-mm-yyyy HHmm> /to <dd-mm-yyyy HHmm>'"
                + "\n2. To edit sweets: "
                + "\n     - 'mark <task number>' "
                + "\n     - 'unmark <task number>'."
                + "\n     - 'delete <task number>' "
                + "\n     - 'edit <task number> /[same as adding task but without 'todo', 'deadline' or 'event']'"
                + "\n3. To view/find sweets:"
                + "\n     - 'list'"
                + "\n     - 'find <keyword>'"
                + "\n4. To close:"
                + "\n   - 'bye'");
    }
}
