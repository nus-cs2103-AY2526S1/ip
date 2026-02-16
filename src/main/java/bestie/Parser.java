package bestie;

import java.util.ArrayList;
import java.util.List;


/**
 * Interprets user commands and applies the requested changes to the task list.
 */
public class Parser {
    /**
     * Parses the raw user input and executes the corresponding command.
     *
     * @param input    command text from the user
     * @param tasks    list of tasks to mutate
     * @param ui       UI used to show feedback to the user
     * @param storage  persistent store used to save task updates
     * @return {@code true} if the command exits the application, {@code false}
     *         otherwise
     * @throws BestieException if the command is invalid or missing arguments
     */
    public boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws BestieException {
        assert input != null : "Input command must not be null";
        assert tasks != null : "Task list must not be null";
        assert ui != null : "UI must not be null";
        assert storage != null : "Storage must not be null";
        String[] parts = input.split(" ", 2);
        assert parts.length > 0 : "Splitting the command should yield at least one token";
        String command = parts[0];
        switch (command) {
        case "bye":
            ui.showBye();
            return true;
        case "list":
            ui.showList(tasks);
            return false;
        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new BestieException("Please tell me what to find bestie!");
            }
            ui.showFindResults(tasks.find(parts[1].trim()));
            return false;
        case "mark":
            if (parts.length < 2) {
                throw new BestieException("Please specify which task to mark!");
            }
            int markIndex = Integer.parseInt(parts[1]) - 1;
            assert markIndex >= 0 && markIndex < tasks.size() : "Mark index must be within the task list";
            Task markTask = tasks.get(markIndex);
            markTask.markAsDone();
            saveQuiet(storage, tasks);
            ui.showMark(markTask);
            return false;
        case "unmark":
            if (parts.length < 2) {
                throw new BestieException("Please specify which task to unmark!");
            }
            int unmarkIndex = Integer.parseInt(parts[1]) - 1;
            assert unmarkIndex >= 0 && unmarkIndex < tasks.size() : "Unmark index must be within the task list";
            Task unmarkTask = tasks.get(unmarkIndex);
            unmarkTask.markAsUndone();
            saveQuiet(storage, tasks);
            ui.showUnmark(unmarkTask);
            return false;
        case "delete":
            if (parts.length < 2) {
                throw new BestieException("Please specify which task to delete!");
            }
            int delIndex = Integer.parseInt(parts[1]) - 1;
            assert delIndex >= 0 && delIndex < tasks.size() : "Delete index must be within the task list";
            Task removed = tasks.remove(delIndex);
            saveQuiet(storage, tasks);
            ui.showDelete(removed, tasks.size());
            return false;
        case "tag":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new BestieException("Tell me which task to tag and the tags to add bestie!");
            }
            String[] tagTokens = parts[1].trim().split("\\s+");
            if (tagTokens.length < 2) {
                throw new BestieException("Please include at least one tag after the task number!");
            }
            int tagIndex;
            try {
                tagIndex = Integer.parseInt(tagTokens[0]) - 1;
            } catch (NumberFormatException nfe) {
                throw new BestieException("The first argument should be the task number to tag!");
            }
            if (tagIndex < 0 || tagIndex >= tasks.size()) {
                throw new BestieException("Please choose a valid task number to tag!");
            }
            Task taggedTask = tasks.get(tagIndex);
            ArrayList<String> rawTags = new ArrayList<>();
            for (int i = 1; i < tagTokens.length; i++) {
                rawTags.add(tagTokens[i]);
            }
            List<String> addedTags = taggedTask.addTags(rawTags);
            if (!addedTags.isEmpty()) {
                saveQuiet(storage, tasks);
            }
            ui.showTag(taggedTask, addedTags);
            return false;
        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new BestieException("The description of a todo cannot be empty!");
            }
            Task todo = new Todo(parts[1].trim());
            tasks.add(todo);
            saveQuiet(storage, tasks);
            ui.showAdd(todo, tasks.size());
            return false;
        case "deadline":
            if (parts.length < 2 || !parts[1].contains("/by")) {
                throw new BestieException("Deadline command must have a /by date/time!");
            }
            String[] deadlineParts = parts[1].split("/by", 2);
            assert deadlineParts.length == 2 : "Deadline command must contain a description and a /by clause";
            if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new BestieException("Deadline description and date/time cannot be empty!");
            }
            Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            tasks.add(deadline);
            saveQuiet(storage, tasks);
            ui.showAdd(deadline, tasks.size());
            return false;
        case "event":
            if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                throw new BestieException("Event must have /from and /to times!");
            }
            String[] eventParts = parts[1].split("/from", 2);
            assert eventParts.length == 2 : "Event command must contain a description and a /from clause";
            String descr = eventParts[0].trim();
            String[] fromTo = eventParts[1].split("/to", 2);
            assert fromTo.length == 2 : "Event command must contain both /from and /to clauses";
            if (descr.isEmpty() || fromTo[0].trim().isEmpty() || fromTo[1].trim().isEmpty()) {
                throw new BestieException("Event description and times cannot be empty!");
            }
            Task event = new Event(descr, fromTo[0].trim(), fromTo[1].trim());
            tasks.add(event);
            saveQuiet(storage, tasks);
            ui.showAdd(event, tasks.size());
            return false;
        default:
            throw new BestieException("OOPS!!! I'm sorry, but I don't know what you are sayin :-(");
        }
    }

    /**
     * Persists tasks to disk without surfacing errors to the user.
     *
     * @param storage persistence component
     * @param tasks   tasks to store
     */
    private void saveQuiet(Storage storage, TaskList tasks) {
        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
