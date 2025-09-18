package marcus;

import marcus.exception.InvalidIndexError;
import marcus.exception.MissingDescriptionError;
import marcus.task.DeadlineTask;
import marcus.task.EventTask;
import marcus.task.Task;
import marcus.task.ToDoTask;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int taskListSize = 0;

    public TaskList() {
        SaveFileManager.init();
        try {
            taskList = SaveFileManager.readFromFile();
            taskListSize = taskList.size();
        } catch (FileNotFoundException e) {
            taskList = new ArrayList<>();
        }

        assert taskList != null : "TaskList should never be null";
        assert taskListSize >= 0 : "TaskList size must be non-negative";
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskListSize() {
        assert taskListSize == taskList.size() : "taskListSize must match taskList.size()";
        return taskListSize;
    }

    public void updateTask() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskListSize; i++) {
            tasks.append(taskList.get(i).getSaveFileString()).append("\n");
        }
        SaveFileManager.writeToFile(tasks.toString());
    }

    /**
     * Marks a task as completed.
     *
     * @throws InvalidIndexError if index provided is less than 0 or greater than size of task list.
     */
    public String mark(int taskIndex) throws InvalidIndexError {
        if (taskIndex <= taskListSize & taskIndex > 0) {
            String message = taskList.get(taskIndex - 1).markComplete();
            this.updateTask();
            return message;
        } else {
            throw(new InvalidIndexError());
        }
    }

    /**
     * Marks a task as incomplete.
     *
     * @throws InvalidIndexError if index provided is less than 0 or greater than size of task list.
     */
    public String unmark(int taskIndex) throws InvalidIndexError {
        if (taskIndex <= taskListSize & taskIndex > 0) {
            String message = taskList.get(taskIndex - 1).unmarkComplete();
            this.updateTask();
            return message;
        } else {
            throw(new InvalidIndexError());
        }
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param taskDescription A description of the task.
     * @throws MissingDescriptionError if taskDescription is missing.
     */
    public void add(String taskDescription) throws MissingDescriptionError {
        if (taskDescription.isEmpty()) {
            throw new MissingDescriptionError("Correct format: todo <taskDescription>");
        } else {
            taskList.add(new ToDoTask(taskDescription));
            taskListSize++;
            this.updateTask();
        }
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param taskDescription A description of the task.
     * @param taskDeadline The deadline of the task, stored as a LocalDate object.
     */
    public void add(String taskDescription, LocalDate taskDeadline) {
        taskList.add(new DeadlineTask(taskDescription, taskDeadline));
        taskListSize++;
        this.updateTask();
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param taskDescription A description of the task.
     * @param start The start time of the task.
     * @param end The end time of the task.
     */
    public void add(String taskDescription, String start, String end) {
        taskList.add(new EventTask(taskDescription, start, end));
        taskListSize++;
        this.updateTask();
    }

    public Task delete(int index) throws InvalidIndexError {
        if (index <= taskListSize & index > 0) {
            Task deletedTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            taskListSize--;

            this.updateTask();

            return deletedTask;
        } else {
            throw(new InvalidIndexError());
        }
    }
 }
