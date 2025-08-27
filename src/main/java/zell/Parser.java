package zell;

import zell.storage.Storage;
import zell.task.Task;
import zell.task.TaskList;
import zell.task.ToDo;
import zell.task.Deadline;
import zell.task.Event;
import zell.exception.ZellException;

/**
 * Deals with the parsing of the user's input and executes
 * the relevant commands.
 * It also returns the message to print based on the user's
 * input and command executed.
 * It also keeps track of whether the chatbot should terminate.
 */
public class Parser {
    /** Indicates if the chatbot should terminate */
    private boolean endProgram;

    public Parser() {
        endProgram = false;
    }

    public boolean getEndProgram() {
        return endProgram;
    }

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
            output = handleBye(firstSpaceIndex, command);
            break;
        case "list":
            output = handleList(firstSpaceIndex, command, taskList);
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            output = handleMarkOrUnMark(command, userInput, firstSpaceIndex, taskList);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            output = handleTaskCommands(userInput, command, firstSpaceIndex, taskList, storage);
            break;
        case "delete":
            output = handleDelete(userInput, command, firstSpaceIndex, taskList, storage);
            break;
        case "find":
            output = handleFind(userInput, command, firstSpaceIndex, taskList);
            break;
        default:
            throw new ZellException(command + ZellMessage.UNKNOWN_COMMAND.message());
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
     * @see #createTask(String, String, int).
     */
    public String handleTaskCommands(String userInput, String command, int firstSpaceIndex,
            TaskList taskList, Storage storage) throws ZellException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.TASK_ADDED.message());

        Task task = createTask(userInput, command, firstSpaceIndex);

        taskList.addTask(task);

        storage.storeTask(task);

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.TASK_REMOVED.message());

        checkNoSpacesInCommand(command, firstSpaceIndex);

        int index = parseIndex(command, userInput, firstSpaceIndex, taskList);

        stringBuilder.append(taskList.getTask(index));

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
     * @see #checkForDeadlineExceptions(String, String, String, int)
     * @see #checkForEventExceptions(String, String, String, int, int)
     */
    public Task createTask(String userInput, String command, int firstSpaceIndex)
            throws ZellException {
        checkNoSpacesInCommand(command, firstSpaceIndex);

        String userInputSecondHalf = userInput.substring(firstSpaceIndex + 1);
        Task task;

        if (command.equals("todo")) {
            task = new ToDo(userInputSecondHalf);
        } else if (command.equals("deadline")) {
            String splitBy = " /by ";
            int firstByIndex = userInputSecondHalf.indexOf(splitBy); // Handle exception if missing; -1

            checkForDeadlineExceptions(command, userInput, splitBy, firstByIndex);

            String name = userInputSecondHalf.substring(0, firstByIndex);
            String dueBy = userInputSecondHalf.substring(firstByIndex + splitBy.length());

            task = new Deadline(name, dueBy);
        } else { // Event
            String splitFrom = " /from ";
            String splitTo = " /to ";

            int firstFromIndex = userInputSecondHalf.indexOf(splitFrom);
            int firstToIndex = userInputSecondHalf.indexOf(splitTo);

            checkForEventExceptions(command, userInput, splitFrom, firstFromIndex, firstToIndex);

            String name = userInputSecondHalf.substring(0, firstFromIndex);
            String start = userInputSecondHalf.substring(firstFromIndex + splitFrom.length(), firstToIndex);
            String end = userInputSecondHalf.substring(firstToIndex + splitTo.length());

            task = new Event(name, start, end);
        }

        return task;
    }

    /**
     * Deals with the bye command.
     * Here the instance variable {@link #endProgram} is set to true to indicate we should terminate the program
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
        endProgram = true;
        return ZellMessage.GOODBYE.message();
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

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.LIST.message());
        stringBuilder.append(taskList.listAllTasks());

        return stringBuilder.toString();
    }

    /**
     * Deals with the mark/unmark command. We use an if else to determine which command to execute.
     * Here we get the task if it is valid and mark/unmark it.
     *
     * @param command The command to be executed.
     * @param userInput The user's input.
     * @param firstSpaceIndex The index of the first space in the user's input.
     * @param taskList The {@link zell.task.TaskList} object which stores tasks.
     * @return The task messages to be printed.
     * @throws ZellException If the mark/unmark commands format is invalid.
     */
    public String handleMarkOrUnMark(String command, String userInput,
            int firstSpaceIndex, TaskList taskList) throws ZellException {
        int index = parseIndex(command, userInput, firstSpaceIndex, taskList);

        Task currentTask = taskList.getTask(index);
        StringBuilder stringBuilder = new StringBuilder();

        if (command.equals("mark")) {
            taskList.markTaskAsDone(index);
            stringBuilder.append(ZellMessage.TASK_MARKED.message());
        } else {
            taskList.markTaskAsNotDone(index);
            stringBuilder.append(ZellMessage.TASK_UNMARKED.message());
        }

        stringBuilder.append(currentTask);

        return stringBuilder.toString();
    }

    public String handleFind(String userInput, String command, int firstSpaceIndex,
            TaskList taskList) throws ZellException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.TASK_FOUND.message());

        checkNoSpacesInCommand(command, firstSpaceIndex);

        String word = userInput.substring(firstSpaceIndex + 1);

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
    public int parseIndex(String command, String userInput, int firstSpaceIndex,
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
                formatMessage = String.format("%s should include a thing to do.\nFor example:\n%s read books",
                        command, command);
                break;
            case "deadline":
                formatMessage = String.format("%s should include a thing to do.\nFor example:\n%s books "
                        + "/by  Sunday", command, command);
                break;
            case "event":
                formatMessage = String.format("%s should include a thing to do.\nFor example:\n%s "
                        + "books /from Mon 2pm /to 4pm", command, command);
                break;
            case "find":
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
        if (!taskList.checkIfTaskExists(index)) {
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
                    + "meeting /by Mon 2pm", command, command);
            throw new ZellException(formatMessage);
        }

        if (firstByIndex == -1) {
            String formatMessage = String.format("%s should include a dateline by using /by.\nFor "
                    + "example:\n" + "%s read books /by Sunday", command, command);
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
                    + "meeting /from Mon 2pm /to 4pm", command, command);
            throw new ZellException(formatMessage);
        }

        if (firstFromIndex == -1) {
            String formatMessage = String.format("%s should include a start by using /from.\nFor "
                    + "example:\n" + "%s project meeting /from Mon 2pm /to 4pm", command, command);
            throw new ZellException(formatMessage);
        }

        if (firstToIndex == -1) {
            String formatMessage = String.format("%s should include a end by using /to.\nFor "
                    + "example:\n" + "%s project meeting /from Mon 2pm /to 4pm", command, command);
            throw new ZellException(formatMessage);
        }

        // Handle exception if /to comes before /from
        if (firstFromIndex > firstToIndex) {
            String formatMessage = String.format("For %s /from should come before /to.\nFor "
                    + "example:\n" + "%s project meeting /from Mon 2pm /to 4pm", command, command);
            throw new ZellException(formatMessage);
        }

        if (firstFromIndex + splitFrom.length() > firstToIndex) {
            String formatMessage = String.format("For %s please include a start date/time using "
                    + "/from.\nFor example:\n" + "%s project meeting /from Mon 2pm /to 4pm", command, command);
            throw new ZellException(formatMessage);
        }
    }
}
