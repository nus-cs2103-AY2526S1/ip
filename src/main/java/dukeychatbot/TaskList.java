package dukeychatbot;

import java.util.ArrayList;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;
import dukeychatbot.tasktypes.Deadline;
import dukeychatbot.tasktypes.Event;
import dukeychatbot.tasktypes.Task;
import dukeychatbot.tasktypes.Todo;

/**
 * Constructs the TaskList class which contains the task list and carry out task commands.
 *
 * @author dongjun
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui;

    /**
     * Constructs the TaskList object.
     */
    public TaskList(ArrayList<String> taskList, Ui ui) {
        this.initialiseTasks(taskList);
        this.ui = ui;
    }

    /**
     * Initialises the task list from the storage.
     *
     * @param taskList ArrayList of type String.
     */
    private void initialiseTasks(ArrayList<String> taskList) {
        for (String input : taskList) {
            try {
                // Need to format the input string so that it resembles a command
                StringBuilder formattedCommand = new StringBuilder();

                String[] inputArray = input.split(" ");
                boolean taskIsDone = false;
                if (inputArray[1].length() != 1) {
                    taskIsDone = true;
                }

                // Split according to the two [] and only retain the description portion
                String description = input.split("]")[2].trim();
                String type = String.valueOf(inputArray[0].charAt(1));

                switch (type) {
                case "T" -> {
                    formattedCommand.append("todo ");
                    formattedCommand.append(description);
                }
                case "D" -> {
                    formattedCommand.append("deadline ");
                    String[] splitDescription = description.split("\\(by:");
                    String deadline = splitDescription[1].trim();
                    String formattedDescription = splitDescription[0].trim() + " /by "
                            + deadline.substring(0, deadline.length() - 1);
                    formattedCommand.append(formattedDescription);
                }
                case "E" -> {
                    formattedCommand.append("event ");
                    String[] splitDescription = description.split("\\(from:");
                    String taskDescription = splitDescription[0].trim();
                    String[] timePeriod = splitDescription[1].split("to:");
                    String fromTime = timePeriod[0].trim();
                    String toTime = timePeriod[1].trim();
                    toTime = toTime.substring(0, toTime.length() - 1);
                    String compiledCommand = taskDescription + " /from " + fromTime + " /to " + toTime;
                    formattedCommand.append(compiledCommand);
                }
                default -> {
                    throw new InvalidCommandException();
                }
                }
                this.addNewTask(formattedCommand.toString(), taskIsDone, true);
            } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException
                     | MissingTimeframeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Returns ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds new task to the tasks ArrayList.
     *
     * @param input User input
     * @param isDone Task completion status
     * @param isInitialise Whether this function is called to initialise the task list.
     */
    public String addNewTask(String input, boolean isDone, boolean isInitialise)
            throws InvalidCommandException, EmptyDescriptionException,
            MissingDeadlineException, MissingTimeframeException {
        Task newTask;

        switch (input.split(" ")[0].toLowerCase()) {
        case "todo", "t" -> {
            newTask = addTodoTask(input, isDone);
        }
        case "deadline", "d" -> {
            newTask = addDeadlineTask(input, isDone);
        }
        case "event", "e" -> {
            newTask = addEventTask(input, isDone);
        }
        default -> {
            throw new InvalidCommandException();
        }
        }
        if (!isInitialise) {
            return this.ui.addTaskResponse(newTask.toString(), tasks.size());
        } else {
            return null;
        }
    }

    /**
     * Constructs new Todo task and adds it to the task list.
     *
     * @param input User input
     * @param isDone Task completion status
     *
     * @return Newly constructed task
     */
    public Task addTodoTask(String input, boolean isDone)
            throws EmptyDescriptionException {
        String description = input.substring(input.indexOf(" ") + 1);

        if (!input.trim().contains(" ")) {
            throw new EmptyDescriptionException();
        }
        Task newTask = new Todo(description, isDone);
        this.tasks.add(newTask);

        return newTask;
    }

    /**
     * Constructs new Deadline task and adds it to the task list.
     *
     *
     * @param input User input
     * @param isDone Task completion status
     *
     * @return Newly constructed task
     */
    public Task addDeadlineTask(String input, boolean isDone)
            throws EmptyDescriptionException, MissingDeadlineException {
        String description = input.substring(input.indexOf(" ") + 1);

        if (!input.trim().contains(" ")) {
            throw new EmptyDescriptionException();
        } else if (!input.contains("/by")) {
            throw new MissingDeadlineException();
        }

        Task newTask = new Deadline(description, isDone);
        this.tasks.add(newTask);

        return newTask;
    }

    /**
     * Constructs new Event task and adds it to the task list.
     * @param input User input
     * @param isDone Task completion status
     *
     *  @return Newly constructed task
     */
    public Task addEventTask(String input, boolean isDone)
            throws EmptyDescriptionException, MissingTimeframeException {

        String description = input.substring(input.indexOf(" ") + 1);

        if (!input.trim().contains(" ")) {
            throw new EmptyDescriptionException();
        } else if (!input.contains("/from") || !input.contains("/to")) {
            throw new MissingTimeframeException();
        }

        Task newTask = new Event(description, isDone);
        this.tasks.add(newTask);
        return newTask;
    }

    /**
     * Removes task from tasks ArrayList.
     */
    public String removeTask(int taskNumber) {
        String message = this.ui.removeTaskResponse(tasks.get(taskNumber - 1).toString(), (tasks.size() - 1));
        tasks.remove(taskNumber - 1);

        return message;
    }

    /**
     * Marks task as done.
     */
    public String markDone(int taskNumber) {
        Task currentTask = this.tasks.get(taskNumber - 1);
        currentTask.markDoneStatus();

        return this.ui.markDoneResponse(currentTask.toString());
    }

    /**
     * Marks task as not done.
     */
    public String unmarkDone(int taskNumber) {
        Task currentTask = this.tasks.get(taskNumber - 1);
        currentTask.unmarkDoneStatus();

        return this.ui.unmarkDoneResponse(currentTask.toString());
    }

    /**
     * Returns an ArrayList of tasks which have descriptions with the keyword in it.
     *
     * @param keyword Keyword to find
     * @return ArrayList of tasks with description that has the keyword in it.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>(this.tasks); // Creates a shallow copy

        for (int i = matchingTasks.size() - 1; i >= 0; i--) {
            if (!matchingTasks.get(i).match(keyword)) {
                matchingTasks.remove(i);
            }
        }
        return matchingTasks;
    }
}
