package zell;

import javafx.application.Platform;
import zell.exception.ZellException;
import zell.storage.Storage;
import zell.task.Deadline;
import zell.task.Event;
import zell.task.Task;
import zell.task.TaskList;
import zell.task.ToDo;

/**
 * Deals with the parsing of the user's input and executes
 * the relevant commands.
 * It also returns the message to print based on the user's
 * input and command executed.
 * It also keeps track of whether the chatbot should terminate.
 */
public class Parser {
    /**
     * Processes user input and extracts the command to be executed.
     * It also calls the method to execute the command.
     *
     * @param userInput The user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @param storage The {@link zell.storage.Storage} object which deals with local storage.
     * @return The message to be printed based on the input and command executed.
     * @throws ZellException If an invalid command is provided (wrong format or command does not exist).
     * @see #executeCommand(String, String, TaskList, Storage)
     */
    public String parseInput(String userInput, TaskList taskList, Storage storage) throws ZellException {
        assert userInput != null : "User input is null";
        assert taskList != null : "Task List is null";
        assert storage != null : "Storage is null";

        int firstSpaceIndex = userInput.indexOf(" ");

        String command = firstSpaceIndex != -1 ? userInput.substring(0, firstSpaceIndex) : userInput;

        return executeCommand(userInput, command, taskList, storage);
    }

    /**
     * Executes the user command using the command extracted and the user's input.
     * It executes the command using a switch case and calling the relevant method to deal
     * with the command.
     *
     * @param userInput The user's input.
     * @param command The command to be executed.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @param storage The {@link zell.storage.Storage} object which deals with local storage.
     * @return The message to be printed based on the input and command executed.
     * @throws ZellException If an invalid command is provided (wrong format or command does not exist).
     */
    public String executeCommand(String userInput, String command, TaskList taskList,
            Storage storage) throws ZellException {
        int firstSpaceIndex = userInput.indexOf(" ");

        String output;

        switch (command) {
        case "bye":
            // Fallthrough
        case "b":
            output = handleBye(firstSpaceIndex, command);
            break;
        case "list":
            // Fallthrough
        case "l":
            output = handleList(firstSpaceIndex, command, taskList);
            break;
        case "mark":
            // Fallthrough
        case "m":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "un":
            output = handleMarkOrUnMark(command, userInput, firstSpaceIndex, taskList, storage);
            break;
        case "todo":
            // Fallthrough
        case "t":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "d":
            // Fallthrough
        case "event":
            // Fallthrough
        case "e":
            output = handleTaskCommands(userInput, command, firstSpaceIndex, taskList, storage);
            break;
        case "delete":
            // Fallthrough
        case "del":
            output = handleDelete(userInput, command, firstSpaceIndex, taskList, storage);
            break;
        case "find":
            // Fallthrough
        case "f":
            output = handleFind(userInput, command, firstSpaceIndex, taskList);
            break;
        default:
            throw new ZellException(command + ZellMessage.UNKNOWN_COMMAND.getMessage());
        }

        return output;
    }

    /**
     * Deals with the Task command (ToDo, Deadline, Event).
     * Here the relevant task is created using createTask,
     * added to the TaskList, and stored in local storage.
     *
     * @param userInput The user's input.
     * @param command The command to be executed.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @param storage The {@link zell.storage.Storage} object which deals with local storage.
     * @return The task messages to be printed.
     * @throws ZellException If the task commands format is invalid.
     * @see #createTask(String, String, int)
     */
    public String handleTaskCommands(String userInput, String command, int firstSpaceIndex,
            TaskList taskList, Storage storage) throws ZellException {

        // Create the task
        Task task = createTask(userInput, command, firstSpaceIndex);

        // Store task in task list and local storage
        taskList.addTask(task);
        storage.storeTask(task);

        // Create output that will be displayed to user
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.TASK_ADDED.getMessage());
        stringBuilder.append(task);
        stringBuilder.append(taskList);

        return stringBuilder.toString();
    }

    /**
     * Deals with the delete command. Here a task is deleted if it is valid.
     * <p>
     * Exceptions are checked using checkNoSpacesInCommand
     * </p>
     * @param userInput The user's input.
     * @param command The command to be executed.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @param storage The {@link zell.storage.Storage} object which deals with local storage.
     * @return The delete messages to be printed.
     * @throws ZellException If the delete command format is invalid or the task to be deleted does not exist.
     * @see #checkNoSpacesInCommand(String, int)
     */
    public String handleDelete(String userInput, String command, int firstSpaceIndex,
            TaskList taskList, Storage storage) throws ZellException {
        checkNoSpacesInCommand(command, firstSpaceIndex);

        // Get index of task to delete
        int index = getIndexFromUserInput(command, userInput, firstSpaceIndex, taskList);

        // Create output that will be displayed to user
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.TASK_REMOVED.getMessage());
        stringBuilder.append(taskList.getTask(index));

        // Delete the task
        taskList.removeTask(index);
        storage.updateTasks(taskList.getAllTasksInString());

        stringBuilder.append(taskList);

        return stringBuilder.toString();
    }

    /**
     * Deals with creating a task. Here a task is created by checking the command if it is
     * (ToDo, Deadline, Event). We then pass in the appropriate parameters to the constructor of each
     * command's class.
     * <p></p>
     * Exceptions are checked using checkNoSpacesInCommand, checkForDeadlineExceptions, checkForEventExceptions.
     * </p>
     * @param userInput The user's input
     * @param command The command to be executed
     * @param firstSpaceIndex The index of the first space in the user's input
     * @return The task that is created
     * @throws ZellException If the task command format is invalid such as missing parameters or invalid dates
     * @see #checkNoSpacesInCommand(String, int)
     * @see #checkForEventExceptions(String, String, String, int, int)
     */
    public Task createTask(String userInput, String command, int firstSpaceIndex)
            throws ZellException {
        checkNoSpacesInCommand(command, firstSpaceIndex);

        // Extract second half of user input after the command
        String userInputSecondHalf = userInput.substring(firstSpaceIndex + 1);

        Task task = null;

        if (command.equals("todo") || command.equals("t")) {
            task = new ToDo(userInputSecondHalf);
        } else if (command.equals("deadline") || command.equals("d")) {
            task = createDeadline(userInput, command, userInputSecondHalf);
        } else if (command.equals("event") || command.equals("e")) { // Event
            task = createEvent(userInput, command, userInputSecondHalf);
        } else {
            assert true : "Should not be able to reach here for creating a task";
        }

        return task;
    }

    /**
     * Deals with creating the Deadline
     *
     * @param userInput The user's input
     * @param command The command to be executed
     * @param userInputSecondHalf The second half of the user input after we split it from the command
     * @return The Deadline that is created
     * @throws ZellException If the user input to create a new Deadline is invalid
     * @see #checkForDeadlineExceptions(String, String, String, int)
     */
    public Deadline createDeadline(String userInput, String command, String userInputSecondHalf)
            throws ZellException {

        String splitBy = " /by ";
        int firstByIndex = userInputSecondHalf.indexOf(splitBy);

        checkForDeadlineExceptions(command, userInput, splitBy, firstByIndex);

        String name = userInputSecondHalf.substring(0, firstByIndex);
        String dueBy = userInputSecondHalf.substring(firstByIndex + splitBy.length());

        return new Deadline(name, dueBy);
    }

    /**
     * Deals with creating the Event
     *
     * @param userInput The user's input
     * @param command The command to be executed
     * @param userInputSecondHalf The second half of the user input after we split it from the command
     * @return The Event that is created
     * @throws ZellException If the user input to create a new Event is invalid
     * @see #checkForEventExceptions(String, String, String, int, int)
     */
    public Event createEvent(String userInput, String command, String userInputSecondHalf)
            throws ZellException {

        String splitFrom = " /from ";
        String splitTo = " /to ";

        int firstFromIndex = userInputSecondHalf.indexOf(splitFrom);
        int firstToIndex = userInputSecondHalf.indexOf(splitTo);

        checkForEventExceptions(command, userInput, splitFrom, firstFromIndex, firstToIndex);

        String name = userInputSecondHalf.substring(0, firstFromIndex);
        String start = userInputSecondHalf.substring(firstFromIndex + splitFrom.length(), firstToIndex);
        String end = userInputSecondHalf.substring(firstToIndex + splitTo.length());

        return new Event(name, start, end);
    }

    /**
     * Deals with the bye command.
     * <p>
     * Exceptions are checked using checkIfCommandHasSpaces
     * </p>
     * @param firstSpaceIndex The index of the first space in the user's input
     * @param command The command to be executed
     * @return The goodbye message
     * @throws ZellException If bye command format is invalid
     * @see #checkIfCommandHasSpaces(String, int)
     */
    public String handleBye(int firstSpaceIndex, String command) throws ZellException {
        checkIfCommandHasSpaces(command, firstSpaceIndex);
        Platform.exit();
        return ZellMessage.GOODBYE.getMessage();
    }

    /**
     * Deals with the list command. Here we get all the tasks in the string format.
     * <p>
     * Exceptions are checked using checkIfCommandHasSpaces
     * </p>
     * @param firstSpaceIndex The index of the first space in the user's input
     * @param command The command to be executed
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @return The messages for the list command
     * @throws ZellException If the list command format is invalid such as missing parameters.
     * @see #checkIfCommandHasSpaces(String, int)
     */
    public String handleList(int firstSpaceIndex, String command, TaskList taskList) throws ZellException {
        checkIfCommandHasSpaces(command, firstSpaceIndex);

        String output = "";
        output += ZellMessage.LIST.getMessage();
        output += taskList.listAllTasks();

        return output;
    }

    /**
     * Deals with the mark/unmark command. We use an if else to determine which command to execute.
     * Here we get the task if it is valid and mark/unmark it.
     *
     * @param command The command to be executed.
     * @param userInput The user's input.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @param storage The {@link zell.storage.Storage} object for local storage.
     * @return The task messages to be printed.
     * @throws ZellException If the mark/unmark commands format is invalid.
     */
    public String handleMarkOrUnMark(String command, String userInput,
            int firstSpaceIndex, TaskList taskList, Storage storage) throws ZellException {

        // Get index of task to mark/unmark
        int index = getIndexFromUserInput(command, userInput, firstSpaceIndex, taskList);

        // Get the task to mark/unmark
        Task currentTask = taskList.getTask(index);

        StringBuilder stringBuilder = new StringBuilder();

        // Perform mark or unmark on the task
        if (command.equals("mark") || command.equals("m")) {
            taskList.markTaskAsDone(index);
            storage.updateTasks(taskList.getAllTasksInString());
            stringBuilder.append(ZellMessage.TASK_MARKED.getMessage());
        } else if (command.equals("unmark") || command.equals("un")) {
            taskList.markTaskAsNotDone(index);
            storage.updateTasks(taskList.getAllTasksInString());
            stringBuilder.append(ZellMessage.TASK_UNMARKED.getMessage());
        } else {
            assert true : "Should not reach here for mark or unmark";
        }

        stringBuilder.append(currentTask);

        return stringBuilder.toString();
    }

    /**
     * Deals with the find command.
     *
     * @param command The command to be executed.
     * @param userInput The user's input.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @return The task messages to be printed.
     * @throws ZellException If the find command format is invalid.
     */
    public String handleFind(String userInput, String command, int firstSpaceIndex,
            TaskList taskList) throws ZellException {

        checkNoSpacesInCommand(command, firstSpaceIndex);

        // Extract word to find from user input
        String word = userInput.substring(firstSpaceIndex + 1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.TASK_FOUND.getMessage());
        stringBuilder.append(taskList.listAllTasksContainingWord(word));

        return stringBuilder.toString();
    }

    /**
     * Extracts the index of the task from the user's input.
     * <p>
     * Exceptions are checked using checkForInvalidTaskNumber
     * </p>
     * @param command The command to be executed.
     * @param userInput The user's input.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @return The index of the task from the user's input.
     * @throws ZellException If a non-number was provided for the index
     * @see #checkForInvalidTaskNumber
     */
    public int getIndexFromUserInput(String command, String userInput, int firstSpaceIndex,
            TaskList taskList) throws ZellException {
        checkNoSpacesInCommand(command, firstSpaceIndex);

        String indexInStringForm = userInput.substring(firstSpaceIndex + 1);

        int index;

        // Throw exception if a non-number was provided
        try {
            index = Integer.parseInt(indexInStringForm);
        } catch (NumberFormatException e) {
            String formatMessage = String.format("%s is not a number, you should indicate a number from "
                    + "the list to %s." + "\nFor example:\n%s 2", indexInStringForm, command, command);
            throw new ZellException(formatMessage);
        }

        checkForInvalidTaskNumber(index, taskList);

        return index;
    }

    /**
     * Checks if a command contains a space.
     * Since for certain commands like bye and list it should not have anything after it.
     *
     * @param command The command to be executed.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @throws ZellException If the command is invalid by containing spaces.
     */
    public void checkIfCommandHasSpaces(String command, int firstSpaceIndex) throws ZellException {
        if (firstSpaceIndex != -1) {
            String formatMessage = String.format("%s should not have anything after.\nFor example:\n%s",
                    command, command);
            throw new ZellException(formatMessage);
        }
    }

    /**
     * Checks if a command does not contain a space.
     * Since for certain commands like ToDo, Deadline, Event it should have some parameters after it.
     *
     * @param command The command to be executed.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @throws ZellException If the command is invalid by not containing spaces.
     */
    public void checkNoSpacesInCommand(String command, int firstSpaceIndex) throws ZellException {
        if (firstSpaceIndex == -1) {
            String formatMessage;
            switch (command) {
            case "todo":
                // Fallthrough
            case "t":
                formatMessage = String.format("%s should include a thing to do.\nFor example:\n%s read books",
                        command, command);
                break;
            case "deadline":
                // Fallthrough
            case "d":
                formatMessage = String.format("%s should include a thing to do.\nFor example:\n%s books "
                        + "/by 2025-09-01", command, command);
                break;
            case "event":
                // Fallthrough
            case "e":
                formatMessage = String.format("%s should include a thing to do.\nFor example:\n%s "
                        + "books /from 2025-09-01 18:30 /to 2025-09-02 18:30", command, command);
                break;
            case "find":
                // Fallthrough
            case "f":
                formatMessage = String.format("%s should include a thing to search for.\nFor example:\n%s "
                        + "book", command, command);
                break;
            default:
                formatMessage = String.format("%s should have a number to indicate which one to %s."
                        + "\nFor example:\n%s 2", command, command, command);
                break;
            }

            throw new ZellException(formatMessage);
        }
    }

    /**
     * Checks if the task number provided is valid
     *
     * @param index The task number.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @throws ZellException If an invalid task number is provided.
     */
    public void checkForInvalidTaskNumber(int index, TaskList taskList) throws ZellException {
        if (!taskList.doesTaskExist(index)) {
            String formatMessage = String.format("Task %d does not exist, please indicate a "
                    + "task number from 1 to %d", index, taskList.getNumberOfTask());
            throw new ZellException(formatMessage);
        }
    }

    /**
     * Checks if the deadline command format is invalid.
     * Such cases occur when:
     * 1) The name of the deadline is missing.
     * 2) The deadline of the task is missing.
     *
     * @param command The command to be executed.
     * @param userInput The user's input.
     * @param splitBy The string we use to indicate the datetime we provide.
     * @param firstByIndex The first index of " /by ".
     * @throws ZellException If the deadline command format is invalid
     */
    public void checkForDeadlineExceptions(String command, String userInput, String splitBy,
            int firstByIndex) throws ZellException {

        // Handle missing deadline name
        if (userInput.contains(splitBy) && firstByIndex == -1) {
            String formatMessage = String.format("%s should include a name.\nFor example:\n" + "%s project "
                    + "meeting /by 2025-09-01 18:30", command, command);
            throw new ZellException(formatMessage);
        }

        // Handle missing /by
        if (firstByIndex == -1) {
            String formatMessage = String.format("%s should include a dateline by using /by.\nFor "
                    + "example:\n" + "%s read books /by 2025-09-02", command, command);
            throw new ZellException(formatMessage);
        }
    }

    /**
     * Checks if the event command format is invalid.
     * Such cases occur when:
     * 1) The name of the event is missing.
     * 2) The start of the event is missing.
     * 3) The end of the event is missing
     * 4) The end of the event come before the start of the event.
     *
     * @param command The command to be executed.
     * @param userInput The user's input.
     * @param splitFrom The string we use to indicate the starting datetime we provide.
     * @param firstFromIndex The first index of " /from ".
     * @param firstToIndex The first index of " /to ".
     * @throws ZellException If the event command format is invalid
     */
    public void checkForEventExceptions(String command, String userInput, String splitFrom,
            int firstFromIndex, int firstToIndex) throws ZellException {
        // Handle if no name for event was provided. event /from today /to tmr
        if (userInput.contains(splitFrom) && firstFromIndex == -1) {
            String formatMessage = String.format("%s should include a name.\nFor example:\n" + "%s project "
                    + "meeting /from 2025-09-01 18:30 /to 2025-09-02 18:30", command, command);
            throw new ZellException(formatMessage);
        }

        if (firstFromIndex == -1) {
            String formatMessage = String.format("%s should include a start by using /from.\nFor "
                    + "example:\n" + "%s project meeting /from 2025-09-01 18:30 /to 2025-09-02 18:30",
                    command, command);
            throw new ZellException(formatMessage);
        }

        if (firstToIndex == -1) {
            String formatMessage = String.format("%s should include a end by using /to.\nFor "
                    + "example:\n" + "%s project meeting /from 2025-09-01 18:30 /to 2025-09-02 18:30",
                    command, command);
            throw new ZellException(formatMessage);
        }

        // Handle exception if /to comes before /from
        if (firstFromIndex > firstToIndex) {
            String formatMessage = String.format("For %s /from should come before /to.\nFor "
                    + "example:\n" + "%s project meeting /from 2025-09-01 18:30 /to 2025-09-02 18:30",
                    command, command);
            throw new ZellException(formatMessage);
        }

        if (firstFromIndex + splitFrom.length() > firstToIndex) {
            String formatMessage = String.format("For %s please include a start date/time using "
                    + "/from.\nFor example:\n" + "%s project meeting /from 2025-09-01 18:30 /to 2025-09-02 18:30",
                    command, command);
            throw new ZellException(formatMessage);
        }
    }
}
