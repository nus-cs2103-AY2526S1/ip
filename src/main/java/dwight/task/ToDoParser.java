package dwight.task;

import dwight.storage.CorruptSaveException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents a parser that creates {@link ToDo} tasks from user input or saved file data. */
public class ToDoParser extends TaskParser {

    /**
     * Parses a user-provided description into a {@link ToDo} task.
     *
     * @param description The description of the task.
     * @return A new {@code ToDo} task containing the given description.
     * @throws IncompleteTaskException If the description is empty or invalid.
     */
    @Override
    public Task parse(String description) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+)$");
        Matcher matcher = pattern.matcher(description);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The description for a 'todo' task cannot be empty.");
        }

        return new ToDo(matcher.group(1));
    }

    /**
     * Parses a saved description string from storage into a {@link ToDo} task.
     *
     * @param description The saved task description.
     * @return A new {@code ToDo} task created from the saved description.
     * @throws CorruptSaveException If the saved description is corrupted.
     */
    @Override
    public Task parseFromFile(String description) throws CorruptSaveException {
        assert description != null && !description.trim().isEmpty()
                : "Corrupted save file: ToDo description is empty.";

        if (description.trim().isEmpty()) {
            throw new CorruptSaveException("Saved ToDo description is empty.");
        }

        return new ToDo(description);
    }
}
