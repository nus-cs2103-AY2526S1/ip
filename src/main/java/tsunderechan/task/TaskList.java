package tsunderechan.task;

import java.util.ArrayList;
import java.util.List;

import tsunderechan.ui.Ui;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Instantiates an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Instantiates a TaskList object with the specified tasks.
     *
     * @param tasks Tasks to be loaded into the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
    }

    /**
     * Returns the size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task at the specified index.
     *
     * @param index Index of Task to be returned.
     *
     * @throws IllegalArgumentException If index is negative or larger than size of list.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            ui.showInvalidIndexError();
        }
        return tasks.get(index);
    }

    /**
     * Adds a Todo task to the TaskList.
     *
     * @param description Description of Todo task to be added.
     */
    public String addTodoTask(String description) {
        detectDuplicateTask("T", description);
        tasks.add(new Todo(description));
        int size = tasks.size();
        return ui.showAddTask(tasks.get(size - 1), size);
    }

    /**
     * Adds a deadline task to the TaskList.
     *
     * @param description Description of deadline task to be added.
     * @param by The time the Deadline Task should be completed by.
     */
    public String addDeadlineTask(String description, String by) {
        detectDuplicateTask("D", description, by);
        tasks.add(new Deadline(description, by));
        int size = tasks.size();
        return ui.showAddTask(tasks.get(size - 1), size);
    }

    /**
     * Adds an event task to the TaskList.
     *
     * @param description Description of deadline task to be added.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     */
    public String addEventTask(String description, String from, String to) {
        detectDuplicateTask("E", description, from, to);
        tasks.add(new Event(description, from, to));
        int size = tasks.size();
        return ui.showAddTask(tasks.get(size - 1), size);
    }

    /**
     * Marks task at the specified index as completed.
     *
     * @param index Index of the task being marked as completed.
     *
     * @throws IllegalArgumentException If index is negative or larger than current size of list.
     */
    public String mark(int index) {
        if (index < 1 || index > tasks.size()) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.get(index - 1);
        if (task.isDone) {
            ui.showAlreadyMarkedError();
        }
        task.mark();
        return ui.showMarkTask(task);
    }

    /**
     * Marks task at the specified index as uncompleted.
     *
     * @param index Index of the task being marked as uncompleted.
     *
     * @throws IllegalArgumentException If index is negative or larger than current size of list.
     */
    public String unmark(int index) {
        if (index < 1 || index > tasks.size()) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.get(index - 1);
        if (!task.isDone) {
            ui.showAlreadyUnmarkedError();
        }
        task.unmark();
        return ui.showUnmarkTask(task);
    }

    /**
     * Deletes task at the specified index.
     *
     * @param index Index of the task being deleted.
     *
     * @throws IllegalArgumentException If index is negative or larger than current size of list.
     */
    public String delete(int index) {
        if (index < 1 || index > tasks.size()) {
            ui.showInvalidTaskError();
            return "";
        }
        Task task = tasks.remove(index - 1);
        return ui.showDeleteTask(task, tasks.size());
    }

    /**
     * Returns tasks whose descriptions match the keyword.
     *
     * @param keyword Keyword to match with description of tasks.
     */
    public List<Task> find(String keyword) {
        List<Task> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Task task : tasks) {
            // compare whole description first
            if (task.getDescription().toLowerCase().startsWith(lowerKeyword)) {
                results.add(task);
                continue;
            }
            String[] words = task.getDescription().toLowerCase().split("\\s+");
            // compare each word
            for (String word : words) {
                if (word.startsWith(lowerKeyword)) {
                    results.add(task);
                    break;
                }
            }
        }
        return results;
    }

    /**
     * Detects duplicate task when adding a new task, and returns an exception if found.
     * A task is a duplicate if all descriptors are the same.
     *
     * @param typeOfNewTask the icon of the new task to be added
     * @param descriptors Depends on the task.
     *                    For todo, just description.
     *                    For deadline, description then by.
     *                    For event, description then from then to.
     *
     * @throws IllegalArgumentException if duplicate found.
     */
    private void detectDuplicateTask(String typeOfNewTask, String ... descriptors) {
        for (Task task : tasks) {
            String description = task.getDescription();
            String type = task.getIcon();

            boolean isSameDescription = description.equals(descriptors[0]);
            boolean isSameType = type.equals(typeOfNewTask);

            if (isSameType && isSameDescription) {
                switch (type) {
                case "T":
                    detectTodoDuplicates(task);
                    break;
                case "D":
                    detectDeadlineDuplicates((Deadline) task, descriptors[1]);
                    break;
                case "E":
                    detectEventDuplicates((Event) task, descriptors[1], descriptors[2]);
                    break;
                default:
                    ui.showInvalidIconError();
                }
            }
        }
    }

    private void detectTodoDuplicates(Task task) {
        ui.showDuplicateTaskError(task.toString(), tasks.indexOf(task) + 1);
    }

    private void detectDeadlineDuplicates(Deadline task, String by) {
        boolean isSameBy = task.by.equals(by);
        if (isSameBy) {
            ui.showDuplicateTaskError(task.toString(), tasks.indexOf(task) + 1);
        }
    }

    private void detectEventDuplicates(Event task, String from, String to) {
        boolean isSameFrom = task.from.equals(from);
        boolean isSameTo = task.to.equals(to);
        if (isSameFrom && isSameTo) {
            ui.showDuplicateTaskError(task.toString(), tasks.indexOf(task) + 1);
        }
    }
}
