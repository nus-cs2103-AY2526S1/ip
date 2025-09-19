package jimmy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jimmy.exception.JimmyException;
import jimmy.task.Deadline;
import jimmy.task.Event;
import jimmy.task.Task;
import jimmy.task.TaskList;
import jimmy.task.ToDo;

/**
 * Represents a Parser object.
 */
public class Parser {
    private Ui ui;
    private Storage taskStorage;
    private TaskList taskList;

    // Initialise all necessary regular expression patterns
    private final String markPattern = "mark \\d+\\s*";
    private final String unmarkPattern = "unmark \\d+\\s*";
    private final String listPattern = "list\\s*";
    private final String byePattern = "bye\\s*";
    private final String toDoPattern = "todo\\s+(.+)\\s*";
    private final String deadlinePattern = "deadline\\s+(.+)\\s+(/by\\s+(.+))\\s*";
    private final String eventPattern = "event\\s+(.+)\\s+(/from (.+))\\s+(/to (.+))\\s*";
    private final String deletePattern = "delete (\\d+)\\s*";
    private final String findPattern = "find (.+)";
    private final String tagPattern = "tag (\\d+) (.+)\\s*";
    private final String untagPattern = "untag (\\d+)\\s*";

    /**
     * Constructs a Parser object.
     *
     * @param ui          Ui object
     * @param taskStorage Storage object
     * @param taskList    TaskList of stored tasks.
     */
    public Parser(Ui ui, Storage taskStorage, TaskList taskList) {
        this.ui = ui;
        this.taskStorage = taskStorage;
        this.taskList = taskList;
    }

    /**
     * Parses a given command into the respective command type.
     *
     * @param command String command provided by the user.
     * @return Respective CommandPattern of the user's command.
     * @throws JimmyException If the command is not of any recognisable pattern.
     */
    public CommandPattern parsePattern(String command) throws JimmyException {
        if (command.matches(byePattern)) {
            return CommandPattern.BYE;
        } else if (command.matches(listPattern)) {
            return CommandPattern.LIST;
        } else if (command.matches(markPattern)) {
            return CommandPattern.MARK;
        } else if (command.matches(unmarkPattern)) {
            return CommandPattern.UNMARK;
        } else if (command.matches(toDoPattern)) {
            return CommandPattern.TODO;
        } else if (command.matches(deadlinePattern)) {
            return CommandPattern.DEADLINE;
        } else if (command.matches(eventPattern)) {
            return CommandPattern.EVENT;
        } else if (command.matches(deletePattern)) {
            return CommandPattern.DELETE;
        } else if (command.matches(findPattern)) {
            return CommandPattern.FIND;
        } else if (command.matches(tagPattern)) {
            return CommandPattern.TAG;
        } else if (command.matches(untagPattern)) {
            return CommandPattern.UNTAG;
        } else {
            throw throwCommandError(command);
        }
    }

    /**
     * Returns the appropriate error based on what command was given.
     * @param command String command provided by the user.
     * @return Appropriate JimmyException based on the string command given by the user.
     */
    public JimmyException throwCommandError(String command) {
        if (command.toLowerCase().contains("mark")) {
            return new JimmyException("Oops! Please mark a valid task!");
        } else if (command.toLowerCase().contains("todo")) {
            return new JimmyException("Oops! Please give a valid todo task!");
        } else if (command.toLowerCase().contains("deadline")) {
            return new JimmyException("Oops! Please give a valid deadline task with this date format: YYYY-MM-DD HHmm");
        } else if (command.toLowerCase().contains("event")) {
            return new JimmyException("Oops! Please give a valid event task with this date format: YYYY-MM-DD HHmm");
        } else if (command.toLowerCase().contains("tag")) {
            return new JimmyException("Oops! Please give a valid tag!");
        } else if (command.toLowerCase().contains("untag")) {
            return new JimmyException("Oops! Please untag a valid task!");
        } else {
            return new JimmyException("Oops! I have no clue what that means!");
        }
    }

    /**
     * Handles the specific input sent by the user and responds accordingly.
     *
     * @param command The string input that is typed by the user.
     * @return String to be shown in the GUI by chatbot
     */
    public String parseCommand(String command) {
        try {
            CommandPattern pattern = parsePattern(command);

            switch (pattern) {
            case BYE:
                return this.ui.displayExit();
            case LIST:
                return this.ui.displayTaskList(this.taskList);
            case MARK:
                return handleMark(command);
            case UNMARK:
                return handleUnmark(command);
            case TODO:
                return handleToDo(command);
            case DEADLINE:
                return handleDeadline(command);
            case EVENT:
                return handleEvent(command);
            case DELETE:
                return handleDelete(command);
            case FIND:
                return handleFind(command);
            case TAG:
                return handleTag(command);
            case UNTAG:
                return handleUntag(command);
            default:
                throw new JimmyException("Oops! Ran into an error!");
            }
        } catch (JimmyException e) {
            return this.ui.displayError(e);
        } finally {
            // Save tasks on any input automatically
            taskStorage.saveData(this.taskList);
        }
    }

    /**
     * Handles the logic being parsing the mark command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     */
    public String handleMark(String command) throws JimmyException {
        // Mark: mark task as done
        try {
            String[] splitCommand = command.split(" ");
            int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
            Task taskToMark = this.taskList.markDone(parsedInt);
            return this.ui.displayMarkDone(taskToMark);
        } catch (Exception e) {
            throw new JimmyException("Please mark a valid task!");
        }
    }

    /**
     * Handles the logic being parsing the unmark command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     */
    public String handleUnmark(String command) throws JimmyException {
        // Unmark: mark task as not done
        try {
            String[] splitCommand = command.split(" ");
            int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
            Task taskToUnmark = this.taskList.markNotDone(parsedInt);
            return this.ui.displayMarkNotDone(taskToUnmark);
        } catch (Exception e) {
            throw new JimmyException("Please unmark a valid task!");
        }
    }

    /**
     * Handles the logic being parsing the todo command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleToDo(String command) throws JimmyException {
        Matcher m = Pattern.compile(toDoPattern).matcher(command);
        try {
            m.find();
            String commandDescription = m.group(1);
            ToDo newToDo = new ToDo(commandDescription, false, Task.EMPTY_TAG);
            this.taskList.addTask(newToDo);
            return this.ui.displayAddedTask(newToDo, this.taskList);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please add a valid todo task!");
        }
    }

    /**
     * Handles the logic being parsing the deadline command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleDeadline(String command) throws JimmyException {
        Matcher m = Pattern.compile(deadlinePattern).matcher(command);
        try {
            m.find();
            String commandDescription = m.group(1);
            String deadline = m.group(3).trim();
            Deadline newDeadline = new Deadline(commandDescription, false, Task.EMPTY_TAG, deadline);
            this.taskList.addTask(newDeadline);
            return this.ui.displayAddedTask(newDeadline, this.taskList);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please give a valid deadline task with this date format: YYYY-MM-DD HHmm");
        }
    }

    /**
     * Handles the logic being parsing the event command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleEvent(String command) throws JimmyException {
        Matcher m = Pattern.compile(eventPattern).matcher(command);
        try {
            m.find();
            String commandDescription = m.group(1);
            String start = m.group(3).trim();
            String end = m.group(5).trim();
            Event newEvent = new Event(commandDescription, false, Task.EMPTY_TAG, start, end);
            this.taskList.addTask(newEvent);
            return this.ui.displayAddedTask(newEvent, this.taskList);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please give a valid event task with this date format: YYYY-MM-DD HHmm");
        }
    }

    /**
     * Handles the logic being parsing the delete command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleDelete(String command) throws JimmyException {
        Matcher m = Pattern.compile(deletePattern).matcher(command);
        try {
            m.find();
            int taskNumberToDelete = Integer.parseInt(m.group(1)) - 1;
            if (taskNumberToDelete > this.taskList.size()) {
                throw new JimmyException("Oops! Please delete a valid task!");
            }
            Task taskToDelete = this.taskList.removeTask(taskNumberToDelete);
            return this.ui.displayRemovedTask(taskToDelete, this.taskList);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please delete a valid task!");
        }
    }

    /**
     * Handles the logic being parsing the find command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleFind(String command) throws JimmyException {
        Matcher m = Pattern.compile(findPattern).matcher(command);
        try {
            m.find();
            String keyword = m.group(1);
            return this.ui.displayFoundTasks(this.taskList, keyword);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please find a valid task!");
        }
    }

    /**
     * Handles the logic being parsing the tag command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleTag(String command) throws JimmyException {
        Matcher m = Pattern.compile(tagPattern).matcher(command);
        try {
            m.find();
            int taskNumber = Integer.parseInt(m.group(1));
            String tag = m.group(2);
            Task taskToTag = this.taskList.getTask(taskNumber);
            taskToTag.setTag(tag); // Set the tag of the task
            return this.ui.displayTag(taskToTag, tag);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please tag a valid task!");
        }
    }

    /**
     * Handles the logic being parsing the untag command.
     *
     * @param command String input that is typed by the user.
     * @return String to be shown on the GUI
     * @throws JimmyException Appropriate JimmyException based on the string command given by the user.
     */
    public String handleUntag(String command) throws JimmyException {
        Matcher m = Pattern.compile(untagPattern).matcher(command);
        try {
            m.find();
            int taskNumber = Integer.parseInt(m.group(1));
            Task taskToUntag = this.taskList.getTask(taskNumber);
            taskToUntag.untag(); // Untag the task
            return this.ui.displayUntag(taskToUntag);
        } catch (Exception e) {
            throw new JimmyException("Oops! Please untag a valid task!");
        }
    }
}
