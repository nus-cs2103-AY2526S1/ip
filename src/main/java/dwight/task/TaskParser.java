package dwight.task;

import dwight.storage.CorruptSaveException;

/**
 * Represents an abstract parser for creating {@link Task} objects. Concrete subclasses provide
 * parsing logic for specific task types (e.g., {@link ToDoParser}, {@link DeadlineParser}, {@link
 * EventParser}).
 */
public abstract class TaskParser {

    /**
     * Parses a user-provided description string into a {@link Task}.
     *
     * @param description The raw description entered by the user.
     * @return A {@code Task} created from the description.
     * @throws IncompleteTaskException If the description is missing required fields or is invalid.
     */
    public abstract Task parse(String description) throws IncompleteTaskException;

    /**
     * Parses a saved description string from storage into a {@link Task}.
     *
     * @param description The serialized task data from storage.
     * @return A {@code Task} created from the saved data.
     * @throws CorruptSaveException If the saved data is incomplete or corrupted.
     */
    public abstract Task parseFromFile(String description) throws CorruptSaveException;
}
