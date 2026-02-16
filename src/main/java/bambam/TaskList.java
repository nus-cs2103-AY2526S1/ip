package bambam;

import java.util.ArrayList;

import bambam.task.Task;

/**
 * Handles the array list of Task objects.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Gets number of tasks in the task list.
     * @return The size of the current task list.
     */
    public int getTaskSize() {
        return taskList.size();
    }

    /**
     * Gets Task object of a specific index.
     * @param index
     * @return The Task object.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public Task getTask(int index) throws BambamException {
        assert index >= 0 : "Task index cannot be negative: " + index;

        if (index >= getTaskSize()) {
            throw new BambamException("Oopsies, this is a invalid task Number");
        } else {
            return taskList.get(index);
        }
    }

    /**
     * Adds Task object to the task list.
     * @param newTask The new task to be added to the task list.
     */
    public void addTaskToList(Task newTask) {
        assert newTask != null : "Null task cannot be added to task list";

        taskList.add(newTask);
    }

    /**
     * Deletes Task object from the task list.
     * @param index The task index in the task list.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public void deleteTaskFromList(int index) throws BambamException {
        assert index >= 0 : "Task index cannot be negative: " + index;

        if (index >= getTaskSize()) {
            throw new BambamException("Oopsies, this is a invalid task Number");
        } else {
            taskList.remove(index);
        }
    }

    /**
     * Marks Task object as done using index.
     * @param index The task index in the task list.
     * @return The task being mark as done.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public Task markTaskAsDone(int index) throws BambamException {
        assert index >= 0 : "Task index cannot be negative: " + index;

        if (index >= getTaskSize()) {
            throw new BambamException("Oopsies, this is a invalid task Number");
        } else {
            Task task = getTask(index);
            task.markAsDone();

           return task;
        }
    }

    /**
     * Marks Task objects as undone using index.
     * @param index The task index in the task list.
     * @return The task being mark as undone.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public Task markTaskAsUndone(int index) throws BambamException {
        assert index >= 0 : "Task index cannot be negative: " + index;

        if (index >= getTaskSize()) {
            throw new BambamException("Oopsies, this is a invalid task Number");
        } else {
            Task task = getTask(index);
            task.markAsUndone();

            return task;
        }
    }

    /**
     * Handles the finding of Task objects with a specific keyword.
     * @param keyword The input word from users when finding specific task.
     * @return The task list with tasks of the specific keyword.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public String findTasks(String keyword) throws BambamException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getTaskSize(); i++) {
            String taskDescription = getTask(i).getTaskDescription();

            if (taskDescription.contains(keyword)) {
                sb.append(i + 1)
                        .append(". ")
                        .append(getTask(i).printTaskString())
                        .append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Handles the getting of task strings and putting them in a list.
     * @return The String of task list.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public String getTaskListString() throws BambamException {
        StringBuilder sb = new StringBuilder(); // ChatGPT enhanced code, it recommended use of StringBuilder

        for (int i = 0; i < getTaskSize(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(getTask(i).printTaskString())
                    .append("\n");
        }

        return sb.toString();
    }
}
