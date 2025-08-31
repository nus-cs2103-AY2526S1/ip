package lynxgui.parser;

import lynxgui.storage.LynxTaskList;

import objectclasses.command.LynxCommand;
import objectclasses.exception.LynxException;
import objectclasses.formatter.LynxDateManager;
import objectclasses.task.Task;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static lynxgui.parser.LynxGeneral.checkName;

/**
 * Contains methods to execute commands that search for tasks in the task list.
 */
public class LynxSearcher {

    /**
     * Filters a task stream as specified by a <code>LynxCommand</code> object.
     * Stores the result within the <code>LynxCommand</code> object at the end of the search.
     *
     * @param command <code>LynxCommand</code> object containing a string of search modifiers.
     * @param stream Task stream that is being operated on.
     * @throws LynxException If search modifiers are invalid.
     */
    public static void findTasks(LynxCommand command, Stream<Task> stream) throws LynxException {
        String input = command.getNextCommand();

        if (input.isBlank()) {
            command.setSearchResult(stream.toList());
            return;
        }

        switch (input) {
            case "/all" -> findTasks(command, stream);
            case "/id" -> findTasksById(command, stream);
            case "/key" -> findTasksByKeyword(command, stream);
            case "/on" -> findTasksByDate(command, stream);
            case "/status" -> findTasksByStatus(command, stream);
            case "/type" -> findTasksByType(command, stream);
            default -> throw new LynxException("Non matching command detected. " +
                    "Please try again or type \"help\" to access the user guide.");
        }

    }

    /**
     * Filters a task stream to only contain the task with a given id.
     *
     * @param command <code>LynxCommand</code> object containing the id.
     * @param stream Task stream that is being operated on.
     * @throws LynxException If id is invalid.
     */
    private static void findTasksById(LynxCommand command, Stream<Task> stream) throws LynxException {
        try {
            String id = command.getNextCommand();
            command.setId(id);
            findTasks(command, LynxTaskList.filterTasksById(stream, Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            throw new LynxException("Sorry, that isn't a valid ID.");
        }
    }

    /**
     * Filters a task stream to only contain tasks containing a given keyword.
     *
     * @param command <code>LynxCommand</code> object containing the keyword.
     * @param stream Task stream that is being operated on.
     * @throws LynxException If name is of invalid format.
     */
    private static void findTasksByKeyword(LynxCommand command, Stream<Task> stream) throws LynxException {
        String keyword = command.getNextCommand();
        checkName(keyword);
        command.setKeyword(keyword);
        findTasks(command, LynxTaskList.filterTasksByKeyword(stream, keyword));
    }

    /**
     * Filters a task stream to only contain tasks occurring on a given date.
     *
     * @param command <code>LynxCommand</code> object containing the date.
     * @param stream Task stream that is being operated on.
     * @throws LynxException If date is of invalid format.
     */
    private static void findTasksByDate(LynxCommand command, Stream<Task> stream) throws LynxException {
        String date = command.getNextCommand();
        LocalDateTime dateTime = LynxDateManager.parseDateTime(date);
        command.setDate(LynxDateManager.textDateTime(dateTime));
        findTasks(command, LynxTaskList.filterTasksByDate(stream, dateTime));
    }

    /**
     * Filters a task stream to only contain tasks of a given status.
     *
     * @param command <code>LynxCommand</code> object containing the status.
     * @param stream Task stream that is being operated on.
     * @throws LynxException If status is invalid.
     */
    private static void findTasksByStatus(LynxCommand command, Stream<Task> stream) throws LynxException {
        String symbol = command.getNextCommand();
        Task.Status status = Task.Status.matchSymbol(symbol);
        command.setStatus(symbol);
        findTasks(command, LynxTaskList.filterTasksByStatus(stream, status));
    }

    /**
     * Filters a task stream to only contain tasks of a given type.
     *
     * @param command <code>LynxCommand</code> object containing the type.
     * @param stream Task stream that is being operated on.
     * @throws LynxException If type is invalid.
     */
    private static void findTasksByType(LynxCommand command, Stream<Task> stream) throws LynxException {
        String symbol = command.getNextCommand();
        Task.TaskType type = Task.TaskType.matchSymbol(symbol);
        command.setType(symbol);
        findTasks(command, LynxTaskList.filterTasksByType(stream, type));
    }

}
