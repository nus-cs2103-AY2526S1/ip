package pengu.task;

import java.util.ArrayList;

import pengu.exception.InvalidFieldException;

/**
 * Stores a list of tasks.
 * Supports adding, deleting, marking tasks as done / undone, finding tasks by substring.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a new task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Get task at index, 1-indexed.
     *
     * @param index 1-indexed task index.
     * @return Task at the index.
     * @throws InvalidFieldException If index is out of bounds.
     */
    public Task get(int index) throws InvalidFieldException {
        checkIndex(index);
        return taskList.get(index - 1);
    }

    /**
     * Returns size of task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Mark task at index as done, 1-indexed.
     *
     * @param index 1-indexed task index.
     * @throws InvalidFieldException If index is out of bounds.
     */
    public void markAsDone(int index) throws InvalidFieldException {
        checkIndex(index);
        taskList.get(index - 1).markAsDone();
    }

    /**
     * Mark task at index as undone, 1-indexed.
     *
     * @param index 1-indexed task index.
     * @throws InvalidFieldException If index is out of bounds.
     */
    public void markAsUndone(int index) throws InvalidFieldException {
        checkIndex(index);
        taskList.get(index - 1).markAsUndone();
    }

    /**
     * Deletes task at index, 1-indexed.
     *
     * @param index 1-indexed task index.
     * @throws InvalidFieldException If index is out of bounds.
     */
    public void remove(int index) throws InvalidFieldException {
        checkIndex(index);
        taskList.remove(index - 1);
    }

    private void checkIndex(int index) throws InvalidFieldException {
        if (index <= 0 || index > taskList.size()) {
            String errorMessage = String.format(
                    "Expected: integer value in range [1, %d]\n", taskList.size())
                    + "Given: " + index;
            throw new InvalidFieldException(errorMessage);
        }
    }

    /**
     * Returns a list of tasks whose description contains the string to find.
     *
     * @param toFind The string to find.
     * @return List of tasks whose description contains toFind.
     */
    public TaskList find(String toFind) {
        TaskList foundTasks = new TaskList();

        for (Task task : taskList) {
            if (task.getDescription().contains(toFind)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }

    /**
     * Updates some detail of a task.
     *
     * @param index Index of task.
     * @param fieldLabel The label of the field to update.
     * @param value The value to update to.
     * @throws InvalidFieldException If the index is out of bounds, or fieldLabel is not recognised.
     */
    public void updateTask(int index, String fieldLabel, String value) throws InvalidFieldException {
        Task taskToUpdate = get(index);
        taskToUpdate.updateField(fieldLabel, value);
    }

    /**
     * Returns a string representation of the TaskList object.
     * Each task in the task list is printed in its own line.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            // Every line except the last should end with newline
            if (i > 0) {
                taskListString.append("\n");
            }

            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString.append(taskString);
        }

        return taskListString.toString();
    }

    /**
     * Returns the task list in the save file format.
     *
     * @return Task list in save file format string.
     */
    public String toSaveFileFormat() {
        StringBuilder saveFileString = new StringBuilder();

        for (Task task : taskList) {
            saveFileString.append(task.toSaveFileFormat());
            saveFileString.append("\n");
        }

        return saveFileString.toString();
    }
}
