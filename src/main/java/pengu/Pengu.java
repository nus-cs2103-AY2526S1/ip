package pengu;

import java.time.LocalDateTime;

import pengu.exception.InvalidCommandException;
import pengu.exception.PenguException;
import pengu.task.Deadline;
import pengu.task.Event;
import pengu.task.Task;
import pengu.task.TaskList;
import pengu.task.Todo;

/**
 * A friendly and helpful penguin chatbot to help you remember tasks.
 */
public class Pengu {
    private TaskList taskList;
    private final Ui ui;
    private Save save;

    private boolean isRunning = true;
    private boolean isSaveFileOk = true;
    private String startupSaveFileError;

    /**
     * Constructor for Pengu.
     */
    public Pengu() {
        ui = new Ui();

        try {
            save = new Save();
            taskList = save.load();
        } catch (PenguException e) {
            isSaveFileOk = false;
            isRunning = false;
            startupSaveFileError = e.getMessage();
        }
    }

    /**
     * Returns the startup message for the bot.
     * If failed to load save file, returns an error message.
     */
    public String getStartupMessage() {
        if (!isSaveFileOk) {
            return startupSaveFileError;
        } else {
            return ui.getGreetingMessage();
        }
    }

    /**
     * Returns a response based on the user input.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        String command = parser.getCommand();

        try {
            switch (command) {
            case "bye" -> {
                isRunning = false;
                save.save(taskList);
                return ui.getExitMessage();
            }
            case "list" -> {
                return ui.getTaskListMessage(taskList);
            }
            case "mark" -> {
                return processMark(parser);
            }
            case "unmark" -> {
                return processUnmark(parser);
            }
            case "todo" -> {
                return processTodo(parser);
            }
            case "deadline" -> {
                return processDeadline(parser);
            }
            case "event" -> {
                return processEvent(parser);
            }
            case "delete" -> {
                return processDelete(parser);
            }
            case "find" -> {
                return processFind(parser);
            }
            case "update" -> {
                return processUpdate(parser);
            }
            default -> throw new InvalidCommandException();
            }
        } catch (PenguException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns whether the Pengu bot is still running.
     */
    public boolean isRunning() {
        return isRunning;
    }

    private String processMark(Parser parser) throws PenguException {
        final String markFormat = "mark <index>";

        int taskIndex = parser.getIntField("", markFormat);
        taskList.markAsDone(taskIndex);

        Task taskToMark = taskList.get(taskIndex);
        return ui.getMarkTaskMessage(taskToMark);
    }

    private String processUnmark(Parser parser) throws PenguException {
        final String unmarkFormat = "unmark <index>";

        int taskIndex = parser.getIntField("", unmarkFormat);
        taskList.markAsUndone(taskIndex);

        Task taskToUnmark = taskList.get(taskIndex);
        return ui.getUnmarkTaskMessage(taskToUnmark);
    }

    private String processDelete(Parser parser) throws PenguException {
        final String deleteFormat = "delete <index>";
        int taskIndex = parser.getIntField("", deleteFormat);

        String message = ui.getDeleteTaskMessage(taskList.get(taskIndex), taskList);
        taskList.remove(taskIndex);
        return message;
    }

    private String processFind(Parser parser) throws PenguException {
        final String findFormat = "find <string_to_find>";
        String toFind = parser.getField("", findFormat);

        TaskList foundTasks = taskList.find(toFind);
        return ui.getFoundTasksMessage(foundTasks);
    }

    private String processTodo(Parser parser) throws PenguException {
        final String todoFormat = "todo <description>";

        String taskDesc = parser.getField("", todoFormat);

        Todo todo = new Todo(taskDesc, false);
        taskList.add(todo);
        return ui.getAddTaskMessage(todo, taskList);
    }

    private String processDeadline(Parser parser) throws PenguException {
        final String deadlineFormat = "deadline <description> /by <by>";

        String description = parser.getField(" /by ", deadlineFormat);
        String byString = parser.getField("", deadlineFormat);

        LocalDateTime by = DateTimeParser.fromDateTimeString(byString);

        Deadline deadline = new Deadline(description, false, by);
        taskList.add(deadline);
        return ui.getAddTaskMessage(deadline, taskList);
    }

    private String processEvent(Parser parser) throws PenguException {
        final String eventFormat = "event <description> /from <from> /to <to>";

        String description = parser.getField(" /from ", eventFormat);
        LocalDateTime from = parser.getDateTimeField(" /to ", eventFormat);
        LocalDateTime to = parser.getDateTimeField("", eventFormat);

        Event event = new Event(description, false, from, to);
        taskList.add(event);
        return ui.getAddTaskMessage(event, taskList);
    }

    private String processUpdate(Parser parser) throws PenguException {
        final String updateFormat = "update <index> </field> <field_value>";

        int taskIndex = parser.getIntField(" ", updateFormat);
        String fieldLabel = parser.getField(" ", updateFormat);
        String fieldValue = parser.getField("", updateFormat);

        taskList.updateTask(taskIndex, fieldLabel, fieldValue);
        Task task = taskList.get(taskIndex);
        return ui.getUpdateTaskMessage(task);
    }
}
