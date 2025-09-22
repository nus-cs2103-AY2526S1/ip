package moon.parser.storage;

import moon.models.Task;
import moon.parser.exceptions.ParseException;

/**
 * Dispatches parsing of storage strings to the appropriate task-specific parser.
 * <p>
 * The first element of the split input string determines the task type:
 * <ul>
 *   <li>"T" → {@link TodoStorageParser}</li>
 *   <li>"D" → {@link DeadlineStorageParser}</li>
 *   <li>"E" → {@link EventStorageParser}</li>
 * </ul>
 */
public class AddFromStorageParser {

    /**
     * Parses a raw storage line into the corresponding {@link Task}.
     *
     * @param input the storage line (e.g., {@code "D | 1 | return book | June 6th"})
     * @return the reconstructed task
     * @throws ParseException if the task type is unrecognised or the line is malformed
     */
    public static Task parse(String input) throws ParseException {
        String[] inputList = input.split(" \\| ");
        StorageParser<? extends Task> p = switch (inputList[0]) {
        case "T" -> new TodoStorageParser();
        case "D" -> new DeadlineStorageParser();
        case "E" -> new EventStorageParser();
        default -> throw new ParseException("Unrecognised command in storage.");
        };
        return p.parse(inputList);
    }
}
