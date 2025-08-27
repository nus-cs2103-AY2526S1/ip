package zell;

import zell.storage.Storage;
import zell.task.Task;
import zell.task.TaskList;
import zell.task.ToDo;
import zell.task.Deadline;
import zell.task.Event;
import zell.exception.ZellException;

public class Parser {
    private boolean endProgram;

    public Parser() {
        endProgram = false;
    }

    public boolean getEndProgram() {
        return endProgram;
    }

    public String parseInput(String userInput, TaskList taskList, Storage storage) throws ZellException {
        int firstSpaceIndex = userInput.indexOf(" ");

        String command = firstSpaceIndex != -1 ? userInput.substring(0, firstSpaceIndex) : userInput;

        return executeCommand(userInput, command, taskList, storage);
    }

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

    public String handleBye(int firstSpaceIndex, String command) throws ZellException {
        checkIfCommandHasSpaces(command, firstSpaceIndex);
        endProgram = true;
        return ZellMessage.GOODBYE.message();
    }

    public String handleList(int firstSpaceIndex, String command, TaskList taskList) throws ZellException {
        checkIfCommandHasSpaces(command, firstSpaceIndex);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ZellMessage.LIST.message());
        stringBuilder.append(taskList.listAllTasks());

        return stringBuilder.toString();
    }

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

    public void checkIfCommandHasSpaces(String command, int firstSpaceIndex) throws ZellException {
        if (firstSpaceIndex != -1) {
            String formatMessage = String.format("%s should not have anything after.\nFor example:\n%s",
                    command, command);
            throw new ZellException(formatMessage);
        }
    }

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

    public void checkForInvalidTaskNumber(int index, TaskList taskList) throws ZellException {
        if (!taskList.checkIfTaskExists(index)) {
            String formatMessage = String.format("Task %d does not exist, please indicate a "
                    + "task number from 1 to %d", index, taskList.getNumberOfTask());
            throw new ZellException(formatMessage);
        }
    }

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
