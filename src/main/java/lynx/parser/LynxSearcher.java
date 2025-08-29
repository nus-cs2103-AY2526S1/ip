package lynx.parser;

import lynx.command.LynxCommand2;
import lynx.exception.LynxException;
import lynx.formatter.LynxDateManager;
import lynx.storage.LynxTaskList;
import lynx.task.Task;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static lynx.parser.LynxGeneral.checkName;

public class LynxSearcher {

    /**
     * Searches tasks from the task list as specified by a <code>Command</code> object.
     * <p>
     * Searches based on keyword by default.
     *
     * @param command <code>Command</code> object containing a valid command.
     * @return List of tasks fulfilling the search.
     * @throws LynxException If command is of invalid format.
     */
    public static void findTasks(LynxCommand2 command, Stream<Task> stream) throws LynxException {
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
            case "/sts" -> findTasksByStatus(command, stream);
            case "/type" -> findTasksByType(command, stream);
            default -> throw new LynxException("Non matching command detected.");
        }

    }

    private static void findTasksById(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        try {
            String id = command.getNextCommand();
            command.setId(id);
            findTasks(command, LynxTaskList.filterTasksById(stream, Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            throw new LynxException("Sorry, that isn't a valid ID.");
        }
    }

    private static void findTasksByKeyword(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String keyword = command.getNextCommand();
        checkName(keyword);
        command.setKeyword(keyword);
        findTasks(command, LynxTaskList.filterTasksByKeyword(stream, keyword));
    }

    private static void findTasksByDate(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String date = command.getNextCommand();
        LocalDateTime dateTime = LynxDateManager.parseDateTime(date);
        command.setDate(LynxDateManager.textDateTime(dateTime));
        findTasks(command, LynxTaskList.filterTasksByDate(stream, dateTime));
    }

    private static void findTasksByStatus(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String symbol = command.getNextCommand();
        Task.Status status = Task.Status.matchSymbol(symbol);
        command.setStatus(symbol);
        findTasks(command, LynxTaskList.filterTasksByStatus(stream, status));
    }

    private static void findTasksByType(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String symbol = command.getNextCommand();
        Task.TaskType type = Task.TaskType.matchSymbol(symbol);
        command.setType(symbol);
        findTasks(command, LynxTaskList.filterTasksByType(stream, type));
    }

}
