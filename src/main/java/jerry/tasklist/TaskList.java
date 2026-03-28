package jerry.tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import jerry.exceptions.JerryException;
import jerry.exceptions.ListIndexOutOfBoundException;
import jerry.storage.Storage;
import jerry.task.Deadline;
import jerry.task.Event;
import jerry.task.Task;
import jerry.task.ToDo;

/**
 * The TaskList manages a list of Task objects, providing
 * functionality to add, remove, mark, unmark, retrieve, and save tasks.
 * It also supports loading tasks from a file and generating user-friendly
 * representations of the task list.
 */
public class TaskList {
    private static final List<Task> taskList = new ArrayList<>();

    public TaskList(File file) throws JerryException {
        loadTasks(file);
    }

    public TaskList() {
    }

    /**
     * Loads tasks from the specified file and populates the internal task list.
     *
     * @param file the file containing saved tasks.
     * @throws JerryException if tasks cannot be loaded due to file errors or format issues.
     */
    private void loadTasks(File file) throws JerryException {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split("\\s*\\|\\s*");
                switch (details[0]) {
                case "T":
                    ToDo todo = new ToDo(details[2].trim());
                    if (details[1].equals("1")) {
                        todo.mark();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = Deadline.fromFile(details[2].trim(), details[3].trim());
                    if (details[1].trim().equals("1")) {
                        deadline.mark();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = Event.getEvent(details);
                    taskList.add(event);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            throw new JerryException("Warning: Could not load some tasks due to file corruption or format issues");
        }
    }

    /**
     * Adds a task to the task list and sort it in chronological order everytime a task is added.
     *
     * @param task the task to add.
     * @return a confirmation message including the added task and updated task count.
     */
    public String addTask(Task task) {
        taskList.add(task);
        taskList.sort(Comparator.comparing(Task::getDescription));
        return "Great! New task added:\n" + task.toString()
                + "\n" + "Now you have " + this.getSize() + " tasks in your list :)";
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex the 1-based index of the task to delete.
     * @return a confirmation message including the deleted task and updated task count.
     * @throws ListIndexOutOfBoundException if the index is out of range.
     */
    public String deleteTask(int taskIndex) throws ListIndexOutOfBoundException {
        if (taskIndex < 0) {
            throw new ListIndexOutOfBoundException("Task number must be positive.");
        } else if (taskIndex > this.getSize()) {
            throw new ListIndexOutOfBoundException("You only have " + this.getSize() + " tasks in the list.");
        }
        Task task = taskList.remove(taskIndex - 1);
        return "Noted! I've marked this task as deleted:\n" + task.toString()
                + "\n" + "Now you have " + this.getSize() + " tasks in the list!";
    }

    public String getList() {
        StringBuilder output = new StringBuilder("Here is your task list: ");
        if (this.getSize() > 0) {
            output.append("\n");
        }
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            output.append(index).append(".").append(taskList.get(i));
            if (i < taskList.size() - 1) {
                output.append("\n");
            }
        }
        if (output.toString().equals("Here is your task list: ")) {
            return "Your task list is currently empty...";
        }
        return output.toString();
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex the 1-based index of the task to mark.
     * @return a confirmation message including the updated task.
     * @throws ListIndexOutOfBoundException if the index is out of range.
     */
    public String mark(int taskIndex) throws ListIndexOutOfBoundException {
        if (taskIndex <= 0) {
            throw new ListIndexOutOfBoundException("Task number must be positive.");
        } else if (taskIndex > this.getSize()) {
            throw new ListIndexOutOfBoundException("You only have " + this.getSize() + " tasks in the list.");
        }
        taskList.get(taskIndex - 1).mark();
        return "Yay! One task down:\n" + taskList.get(taskIndex - 1).toString();
    }

    /**
     * Marks a task as not completed.
     *
     * @param taskIndex the 1-based index of the task to unmark.
     * @return a confirmation message including the updated task.
     * @throws ListIndexOutOfBoundException if the index is out of range.
     */
    public String unmark(int taskIndex) throws ListIndexOutOfBoundException {
        if (taskIndex <= 0) {
            throw new ListIndexOutOfBoundException("Task number must be positive.");
        } else if (taskIndex > this.getSize()) {
            throw new ListIndexOutOfBoundException("You only have " + this.getSize() + " tasks in the list.");
        }
        taskList.get(taskIndex - 1).unmark();
        return "Noted! I've marked this task as undone:\n" + taskList.get(taskIndex - 1).toString();
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Saves all tasks to the given storage.
     *
     * @param storage the Storage object to write tasks to.
     */
    public void saveTasks(Storage storage) {
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            sb.append(t.toFileString()).append("\n");
        }
        try {
            storage.writeToFile(sb.toString());
        } catch (JerryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return getList();
    }
}
