package chatbot.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chatbot.exception.BotException;
import chatbot.exception.IncompleteArgumentException;
import chatbot.exception.InvalidArgumentException;
import chatbot.exception.ListIndexOutOfBoundException;

/**
 * TaskList handles temporary storage of tasks the user added.
 * It handles task addition, deletion, mark task complete, unmark task complete and loading tasks from TaskStorage.
 */
public class TaskList {
    // List of tasks managed by the bot
    private final List<Task> myTasks = new ArrayList<>();

    public Task getTask(int taskId) {
        assert taskId > 0 && taskId <= myTasks.size() : "Task ID out of bounds";
        int taskIdx = taskId - 1;
        return myTasks.get(taskIdx);
    }

    public List<Task> getAllTasks() {
        return this.myTasks;
    }

    /**
     * Returns a new task based on the given task description and adds it to the task list.
     *
     * @param taskDescription Description of the task to be added; must not be null or empty.
     * @return A new Task object.
     * @throws BotException If the task creation fails.
     */
    public Task addTask(String taskDescription) throws BotException {
        assert taskDescription != null && !taskDescription.isEmpty() : "Task description must not be null or empty";
        Task newTask = TaskFactory.createTask(taskDescription);
        myTasks.add(newTask);

        return newTask;
    }

    /**
     * Returns a deleted task based on its ID from the task list.
     *
     * @param deleteCommand delete task command.
     * @return The deleted Task object.
     * @throws BotException If the task is empty or the taskId is invalid.
     */
    public Task deleteTask(String deleteCommand) throws BotException {
        String errorMessage;
        String[] input = deleteCommand.split(" ");
        if (input.length > 2) {
            errorMessage = "Invalid Argument: Slow down buddy one at a time\n";
            throw new InvalidArgumentException(errorMessage);
        }

        int taskId = Integer.parseInt(input[1].trim());
        int taskIdx = taskId - 1;
        if (myTasks.isEmpty()) {
            errorMessage = "Cannot Delete Task: Your list is literally empty\n";
            throw new ListIndexOutOfBoundException(errorMessage);
        }

        if (taskId > myTasks.size()) {
            errorMessage = "Invalid Task Id: You don't have that many tasks...\n";
            throw new InvalidArgumentException(errorMessage);
        } else if (taskId < 1) {
            errorMessage = "Are you drunk? Task " + taskId + "?\n"
                    + "Keep it between 1 and " + myTasks.size();
            throw new InvalidArgumentException(errorMessage);
        } else {
            return myTasks.remove(taskIdx);
        }
    }

    /**
     * Marks or unmarks a task as complete based on its index.
     *
     * @param taskIdx 1-based index of the task to be marked/unmarked.
     * @throws BotException If index is invalid of the task does not exist.
     */
    public void handleMarkTaskComplete(String taskIdx) throws BotException {
        String errorMessage;
        try {
            int taskIndex = Integer.parseInt(taskIdx.strip()) - 1;
            Task task = myTasks.get(taskIndex);
            task.markTaskComplete();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            errorMessage = "Invalid Task Id: That task don't exist...\n"
                    + "Keep it between 1 and " + myTasks.size();
            throw new InvalidArgumentException(errorMessage);
        }
    }

    /**
     * Marks or unmarks a task as complete based on its index.
     *
     * @param taskIdx 1-based index of the task to be marked/unmarked.
     * @throws BotException If index is invalid of the task does not exist.
     */
    public void handleUnmarkTaskComplete(String taskIdx) throws BotException {
        String errorMessage;
        try {
            int taskIndex = Integer.parseInt(taskIdx) - 1;
            Task task = myTasks.get(taskIndex);
            task.unmarkTaskComplete();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            errorMessage = "Invalid Task Id: That task don't exist...\n"
                    + "Keep it between 1 and " + myTasks.size();
            throw new InvalidArgumentException(errorMessage);
        }
    }

    /**
     * Returns a formatted string representation of all tasks in the task list.
     *
     * @return A string that lists all tasks with their indices (1-indexed).
     */
    public String showTaskList() {
        return IntStream
                .range(0, myTasks.size())
                .mapToObj(i -> (i + 1) + "." + myTasks.get(i))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns an array of tasks that matches the given search keyword.
     *
     * @param search User input, find work etc.
     * @return An array of tasks with descriptions matching the given keyword.
     * @throws BotException If user input is incomplete.
     */
    public Task[] findTasks(String search) throws BotException {
        String[] input = search.split(" ", 2);
        if (input.length < 2) {
            throw new IncompleteArgumentException("Incomplete Command: Find what...\n");
        }

        String keyword = input[1].trim();
        return myTasks.stream()
                .filter(task -> task.existsInTaskDescription(keyword))
                .toArray(Task[]::new);
    }

    /**
     * Loads task from Storage into a TaskList instance.
     *
     * @param file txt file that stores user tasks.
     * @throws IOException If data is corrupted.
     */
    public void loadTasks(File file) throws IOException {
        if (!file.exists()) {
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTaskFromStorage(line);
                myTasks.add(task);
            }
        } catch (BotException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task parseTaskFromStorage(String line) throws BotException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new BotException("Corrupted task in file: " + line);
        }

        String type = parts[0].strip();
        boolean isComplete = parts[1].strip().equals("[X]");
        String description = parts[2].strip();

        switch (type) {
        case "T":
            Task todoTask = new ToDoTask(description);
            if (isComplete) {
                todoTask.markTaskComplete();
            }
            return todoTask;

        case "D":
            String deadline = parts[3].strip();
            Task deadlineTask = new DeadlineTask(description, deadline);
            if (isComplete) {
                deadlineTask.markTaskComplete();
            }
            return deadlineTask;

        case "E":
            String dateRange = parts[3].strip();
            String from = dateRange.split(" to ")[0];
            String to = dateRange.split(" to ")[1];
            Task eventTask = new EventTask(description, from, to);
            if (isComplete) {
                eventTask.markTaskComplete();
            }
            return eventTask;

        default:
            throw new BotException("Corrupted task in file: " + line);
        }
    }
}
