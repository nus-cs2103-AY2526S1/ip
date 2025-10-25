package dwight.task;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a factory for creating {@link TaskParser} instances based on task type keywords used by
 * the user or stored in files.
 */
public class TaskParserFactory {

    /** Mapping of task type keywords to their corresponding parsers. */
    private static final Map<String, TaskParser> PARSERS = new HashMap<>();

    static {
        PARSERS.put("T", new ToDoParser());
        PARSERS.put("todo", new ToDoParser());
        PARSERS.put("D", new DeadlineParser());
        PARSERS.put("deadline", new DeadlineParser());
        PARSERS.put("E", new EventParser());
        PARSERS.put("event", new EventParser());
    }

    /**
     * Returns a {@link TaskParser} associated with the given task type keyword.
     *
     * @param type The task type keyword (e.g., {@code "todo"}, {@code "deadline"}, {@code
     *     "event"}).
     * @return The corresponding {@code TaskParser} instance.
     * @throws UnknownTaskTypeException If the task type is not recognized.
     */
    public static TaskParser createFileParser(String type) throws UnknownTaskTypeException {
        TaskParser parser = PARSERS.get(type);
        if (parser == null) {
            throw new UnknownTaskTypeException(type);
        }
        return parser;
    }
}
