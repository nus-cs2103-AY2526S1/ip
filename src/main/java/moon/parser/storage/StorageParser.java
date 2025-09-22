package moon.parser.storage;

import moon.models.Task;
import moon.parser.exceptions.ParseException;

/**
 * A generic parser for reconstructing {@link Task} objects from storage.
 *
 * @param <T> the type of task produced by this parser
 */
public interface StorageParser<T extends Task> {
    /**
     * Parses a string array representing a task in storage format and
     * reconstructs the corresponding task object.
     *
     * @param inputs the split storage line (e.g., {"T", "0", "read book"})
     * @return the reconstructed task
     * @throws ParseException if the input cannot be parsed into a valid task
     */
    T parse(String[] inputs) throws ParseException;
}
