package friday.model;

import friday.ui.Ui;
import friday.storage.Storage;
import friday.exception.FridayException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Consists of all methods that modify the task list.
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> all() {
        return tasks;
    }

    // Replaces all tasks in the list with newTasks.
    public void setAll(List<Task> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
    }

    // Returns the task at the given one-based index.
    public Task get(int oneBased) throws FridayException {
        int i = oneBased - 1;
        if (i < 0 || i >= tasks.size()) {
            throw new FridayException("That task number does not exist.");
        }
        return tasks.get(i);
    }

    // Adds a new task to the list.
    public void add(Task t, Ui ui, Storage storage) throws IOException {
        assert t != null;
        tasks.add(t);
        storage.save(tasks);
        ui.added(t, tasks.size());
    }

    // Removes the task at the given one-based index from the list.
    public void remove(int oneBased, Ui ui, Storage storage) throws FridayException, IOException {
        assert get(oneBased) != null;
        Task t = get(oneBased);
        tasks.remove(oneBased - 1);
        storage.save(tasks);
        ui.removed(t, tasks.size());
    }

    // Marks or unmarks the task at the given one-based index.
    public void toggle(int oneBased, boolean mark, Ui ui, Storage storage) throws FridayException, IOException {
        assert get(oneBased) != null;
        Task t = get(oneBased);
        t.done = mark;
        storage.save(tasks);
        ui.toggled(t, mark);
    }

    // Finds all tasks that match the given keyword (case-insensitive).
    public List<Task> find(String keyword) {
        String k = keyword.toLowerCase();
        List<Task> out = new ArrayList<>();
        for (Task t : tasks) {
            if (t.matches(k)) out.add(t);
        }
        return out;
    }

    // Sorts tasks by type: Deadlines, ToDos, Events.
    public void sortByType() {
        tasks.sort((a, b) -> {
            int pa = priority(a.typeIcon());
            int pb = priority(b.typeIcon());
            return Integer.compare(pa, pb);
        });
    }

    // Returns priority based on task type.
    private int priority(String typeIcon) {
        if (typeIcon.startsWith("[D]")) return 1;  // Deadlines first
        if (typeIcon.startsWith("[T]")) return 2;  // ToDos next
        if (typeIcon.startsWith("[E]")) return 3;  // Events last
        return 4; // fallback (unknown)
    }
}

