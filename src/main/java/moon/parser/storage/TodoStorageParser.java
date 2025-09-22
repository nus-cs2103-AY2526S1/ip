package moon.parser.storage;

import moon.models.Todo;
import moon.parser.exceptions.ParseException;

/**
 * Parser for reconstructing {@link Todo} tasks from storage.
 * <p>
 * Expected storage format:
 * {@code T | <done-flag> | <name>}
 */
public class TodoStorageParser implements StorageParser<Todo> {

    /**
     * Parses the given storage fields into a {@link Todo} task.
     *
     * @param inputs the split storage line (must contain 3 fields)
     * @return the reconstructed {@link Todo}
     */
    @Override
    public Todo parse(String[] inputs) throws ParseException {
        String name = inputs[2];
        boolean done = inputs[1].equals("1");

        Todo todo = new Todo(name);
        todo.setDone(done);
        return todo;
    }
}
