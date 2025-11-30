package briar.task;

import java.util.ArrayList;

import briar.exception.TaskNotInListException;

/**
 * Represents list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Creates list of tasks from a taskListString.
     * @param taskListString taskListString read from a file from storage
     */
    public TaskList(String taskListString) {
        assert taskListString != null : "taskListString should not be null";
        tasks = stringToTasks(taskListString);
    }

    private static ArrayList<Task> stringToTasks(String taskListString) {
        String[] splitTaskStrings = taskListString.split("\n");
        return splitStringsToTaskList(splitTaskStrings);
    }

    private static ArrayList<Task> splitStringsToTaskList(String[] splitTaskStrings) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < splitTaskStrings.length; ++i) {
            String[] splitStrings = splitTaskStrings[i].split("\\|");
            switch (splitStrings[0]) {
            case "T":
                Task todoTask = stringToTodo(splitStrings);
                tasks.add(todoTask);
                break;
            case "D":
                Task deadlineTask = stringToDeadline(splitStrings);
                tasks.add(deadlineTask);
                break;
            case "E":
                Task eventTask = stringToEvent(splitStrings);
                tasks.add(eventTask);
                break;
            default:
            }
        }
        return tasks;
    }

    private static Todo stringToTodo(String[] splitStrings) {
        Todo todoTask = new Todo(splitStrings[2]);
        boolean isToDoDone = Integer.parseInt(splitStrings[1]) != 0;
        todoTask.setDone(isToDoDone);
        return todoTask;
    }

    private static Deadline stringToDeadline(String[] splitStrings) {
        Deadline deadlineTask = new Deadline(splitStrings[2], splitStrings[3]);
        boolean isDeadlineDone = Integer.parseInt(splitStrings[1]) != 0;
        deadlineTask.setDone(isDeadlineDone);
        return deadlineTask;
    }

    private static Event stringToEvent(String[] splitStrings) {
        Event eventTask = new Event(splitStrings[2], splitStrings[3], splitStrings[4]);
        boolean isEventDone = Integer.parseInt(splitStrings[1]) != 0;
        eventTask.setDone(isEventDone);
        return eventTask;
    }

    /**
     * Returns the representation of the task to be written into a file as text.
     */
    public String taskToTextString() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.toText()).append(System.lineSeparator());
        }
        return str.toString();
    }

    /**
     * Marks a task as done.
     * @param taskNumber Task number of the task to be marked as done.
     * @return String representation of the task marked.
     * @throws TaskNotInListException If taskNumber is not in the task list.
     */
    public String mark(int taskNumber) throws TaskNotInListException {
        assert taskNumber >= 0 : "taskNumber should be >= 0";
        try {
            Task task = tasks.get(taskNumber);
            task.setDone(true);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskNotInListException();
        }
    }

    /**
     * Marks a task as undone.
     * @param taskNumber Task number of the task to be marked as undone.
     * @return String representation of the task unmarked.
     * @throws TaskNotInListException If taskNumber is not in the task list.
     */
    public String unmark(int taskNumber) throws TaskNotInListException {
        assert taskNumber >= 0 : "taskNumber should be >= 0";
        try {
            Task task = tasks.get(taskNumber);
            task.setDone(false);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskNotInListException();
        }
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added to the task list.
     */
    public void add(Task task) {
        assert task != null : "task should not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNumber Task number of the task to be deleted.
     * @return String representation of the task deleted.
     * @throws TaskNotInListException If taskNumber is not in the task list.
     */
    public String delete(int taskNumber) throws TaskNotInListException {
        assert taskNumber >= 0 : "taskNumber should be >= 0";
        try {
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskNotInListException();
        }
    }

    /**
     * Returns string of all tasks with the given keyword in their descriptions.
     * @param keyword Keyword to look for in task descriptions.
     */
    public String find(String keyword) {
        assert keyword != null : "keyword should not be null";
        String taskListString = "";
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.hasKeyword(keyword)) {
                taskListString += (i + 1) + ". " + task + System.lineSeparator();
            }
        }
        return taskListString;
    }

    /**
     * Returns the number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string representation of the task list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); ++i) {
            str += (i + 1) + ". " + tasks.get(i).toString() + System.lineSeparator();
        }
        return str;
    }
}
