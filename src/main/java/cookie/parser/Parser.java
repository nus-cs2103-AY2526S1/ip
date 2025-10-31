package cookie.parser;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.Deadline;
import cookie.task.Event;
import cookie.task.Task;
import cookie.task.TaskList;
import cookie.task.Todo;
import cookie.ui.Ui;

/**
 * Checks the users input matches the required format and
 * executes the relevant functions.
 */
public class Parser {

    /**
     * Checks the users input matches the required format and
     * prints the corresponding output, updates the task list,
     * and saves it.
     * Output shown in GUI.
     *
     * @param listOfTasks List of tasks currently stored.
     * @param ui          The ui instance used to print relevant messages to user.
     * @param storage     The storage instance used to save the current list in a text file
     * @param fullInput   The complete user input string.
     * @return String output to indicate whether operation was a success or not.
     * @throws CookieException If input does not follow required format.
     */
    public static String parseForGui(
            TaskList listOfTasks, Ui ui, Storage storage, String fullInput) throws CookieException {

        String[] splitInput = fullInput.split(" ", 2);
        String firstWord = splitInput[0];

        switch (firstWord) {
        case "bye":
            return ui.showByeGui();
        case "list":
            return ui.showListGui(listOfTasks);
        case "mark":
            return handleMark(listOfTasks, ui, storage, fullInput, splitInput);
        case "unmark":
            return handleUnmark(listOfTasks, ui, storage, fullInput, splitInput);
        case "delete":
            return handleDelete(listOfTasks, ui, storage, fullInput, splitInput);
        case "todo":
            return handleTodo(listOfTasks, ui, storage, fullInput, splitInput);
        case "deadline":
            return handleDeadline(listOfTasks, ui, storage, fullInput, splitInput);
        case "event":
            return handleEvent(listOfTasks, ui, storage, fullInput, splitInput);
        case "find":
            return handleFind(listOfTasks, ui, storage, fullInput, splitInput);
        case "update":
            return handleUpdate(listOfTasks, ui, storage, fullInput, splitInput);
        default:
            throw new CookieException("Sorry! I'm not too sure what you mean");
        }
    }

    private static String handleMark(TaskList listOfTasks, Ui ui, Storage storage,
                                     String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("mark")) {
            throw new CookieException("Please specify which task you would like to mark.");
        }
        int indexToBeMarked = Integer.parseInt(splitInput[1]) - 1;
        listOfTasks.get(indexToBeMarked).markAsDone();
        storage.save(listOfTasks);
        return ui.showMarkGui(listOfTasks.get(indexToBeMarked));
    }

    private static String handleUnmark(TaskList listOfTasks, Ui ui, Storage storage,
                                       String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("unmark")) {
            throw new CookieException("Please specify which task you would like to unmark.");
        }
        int indexToBeUnmarked = Integer.parseInt(splitInput[1]) - 1;
        listOfTasks.get(indexToBeUnmarked).markAsUndone();
        storage.save(listOfTasks);
        return ui.showUnmarkGui(listOfTasks.get(indexToBeUnmarked));
    }

    private static String handleDelete(TaskList listOfTasks, Ui ui, Storage storage,
                                       String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("delete")) {
            throw new CookieException("Please specify which task you would like to delete.");
        }
        int indexToBeDeleted = Integer.parseInt(splitInput[1]) - 1;
        Task taskToBeDeleted = listOfTasks.get(indexToBeDeleted);
        listOfTasks.remove(indexToBeDeleted);
        storage.save(listOfTasks);
        return ui.showDeleteGui(taskToBeDeleted, listOfTasks.size());
    }

    private static String handleTodo(TaskList listOfTasks, Ui ui, Storage storage,
                                     String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("todo")) {
            throw new CookieException("Please provide a description for your todo task.");
        }
        assert splitInput.length == 2 : "Todo task should have a type and description";
        Task newTodo = new Todo(splitInput[1]);
        listOfTasks.add(newTodo);
        storage.save(listOfTasks);
        return ui.showTodoGui(newTodo, listOfTasks.size());
    }

    private static String handleDeadline(TaskList listOfTasks, Ui ui, Storage storage,
                                         String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("deadline") || !splitInput[1].contains("/by")) {
            throw new CookieException("Please provide deadline in the format: {Description} /by {Day}");
        }
        String[] secondPhraseSplit = splitInput[1].split("/by");
        assert secondPhraseSplit.length == 2 : "Deadline task should have a description and end date/time";
        String description = secondPhraseSplit[0];
        String deadline = secondPhraseSplit[1];
        Task newDeadline = new Deadline(description, deadline);
        listOfTasks.add(newDeadline);
        storage.save(listOfTasks);
        return ui.showDeadlineGui(newDeadline, listOfTasks.size());
    }

    private static String handleEvent(TaskList listOfTasks, Ui ui, Storage storage,
                                      String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("event") || !splitInput[1].contains("/from") || !splitInput[1].contains("/to")) {
            throw new CookieException(
                    "Please provide event in the format: {Description} /from {date/time} /to {date/time}");
        }
        String[] secondPhraseSplit = splitInput[1].split("/from");
        String[] thirdPhraseSplit = secondPhraseSplit[1].split("/to");
        String description = secondPhraseSplit[0];
        assert thirdPhraseSplit.length == 2 : "Event task should have a start and end date/time";
        String from = thirdPhraseSplit[0];
        String to = thirdPhraseSplit[1];
        Task newEvent = new Event(description, from, to);
        listOfTasks.add(newEvent);
        storage.save(listOfTasks);
        return ui.showEventGui(newEvent, listOfTasks.size());
    }

    private static String handleFind(TaskList listOfTasks, Ui ui, Storage storage,
                                     String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("find")) {
            throw new CookieException("Please specify task to find.");
        }
        String taskToFind = splitInput[1];
        System.out.println(taskToFind);
        TaskList listOfMatchingTasks = listOfTasks.find(taskToFind);
        return ui.showFindingsGui(listOfMatchingTasks);
    }

    private static String handleUpdate(TaskList listOfTasks, Ui ui, Storage storage,
                                       String fullInput, String[] splitInput) throws CookieException {
        if (fullInput.equals("update")) {
            throw new CookieException("Please specify task to update in the following format: "
                    + "update {task number} {updated information}");
        }

        String[] providedInformation = splitInput[1].split(" ", 2);
        if (providedInformation.length < 2) {
            throw new CookieException("Please specify task to update in the following format: "
                    + "update {task number} {updated information}");
        }

        int indexToUpdate = Integer.parseInt(providedInformation[0]) - 1;
        String newInformation = providedInformation[1];

        if (indexToUpdate < 0 || indexToUpdate > listOfTasks.size()) {
            throw new CookieException("Task Number " + (indexToUpdate + 1) + " out of bounds.");
        }

        Task taskToUpdate = listOfTasks.get(indexToUpdate);
        taskToUpdate.update(newInformation);
        storage.save(listOfTasks);
        return ui.showUpdateGui(taskToUpdate, indexToUpdate + 1);
    }
}
