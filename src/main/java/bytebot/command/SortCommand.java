package bytebot.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Deadline;
import bytebot.task.Event;
import bytebot.task.Task;
import bytebot.task.Todo;
import bytebot.ui.Ui;

/**
 * Sorts tasks by a criteria.
 * Supports sorting deadlines and events.
 */
public class SortCommand extends Command {
    private static final Comparator<Event> EVENT_BY_END = Comparator.comparing(Event::getTo);
    private static final Comparator<Deadline> DEADLINE_BY_DUE = Comparator.comparing(Deadline::getBy);
    private static final Comparator<Task> TODO_BY_TEXT = Comparator.comparing(Task::toString);
    /**
     * Supported sort types for the sort command.
     */
    public enum SortType {
        DEADLINE,
        ALL
    }

    private final SortType sortType;

    public SortCommand(SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Executes the sort command.
     * Retrieves tasks from storage, applies the sorting, and displays
     * the sorted output on the UI. Shows deadlines sorted by due date.
     *
     * @param ui       UI used to render output
     * @param storage  Storage providing access to tasks
     * @return Rendered output string from the UI
     * @throws ByteException If the sort type is unsupported or any error occurs
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        List<Task> tasks = storage.getAllTasks();

        switch (sortType) {
        case DEADLINE: {
            List<Deadline> deadlinesOnly = new ArrayList<>();
            for (Task t : tasks) {
                if (t instanceof Deadline) {
                    deadlinesOnly.add((Deadline) t);
                }
            }
            deadlinesOnly.sort(DEADLINE_BY_DUE);
            if (deadlinesOnly.isEmpty()) {
                return "No deadlines found.";
            }
            List<Task> result = new ArrayList<>(deadlinesOnly.size());
            result.addAll(deadlinesOnly);
            return ui.showTasks(result);
        }
        case ALL: {
            List<Event> events = new ArrayList<>();
            List<Deadline> deadlines = new ArrayList<>();
            List<Todo> todos = new ArrayList<>();

            for (Task t : tasks) {
                if (t instanceof Event) {
                    events.add((Event) t);
                } else if (t instanceof Deadline) {
                    deadlines.add((Deadline) t);
                } else if (t instanceof Todo) {
                    todos.add((Todo) t);
                }
            }

            events.sort(EVENT_BY_END);
            deadlines.sort(DEADLINE_BY_DUE);
            todos.sort((a, b) -> TODO_BY_TEXT.compare(a, b));

            List<Task> combined = new ArrayList<>(events.size() + deadlines.size() + todos.size());
            combined.addAll(events);
            combined.addAll(deadlines);
            combined.addAll(todos);

            if (combined.isEmpty()) {
                return "No tasks found.";
            }
            return ui.showTasks(combined);
        }
        default:
            throw new ByteException("Invalid sort type");
        }
    }
}


