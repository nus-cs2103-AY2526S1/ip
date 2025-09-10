package jimbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jimbot.command.Commands;
import jimbot.exception.InvalidDateTimeException;
import jimbot.exception.InvalidDeadlineException;
import jimbot.exception.InvalidEventException;
import jimbot.exception.InvalidToDoException;
import jimbot.exception.NoSuchTaskException;
import jimbot.exception.TaskLimitException;
import jimbot.storage.Storage;
import jimbot.tasktype.Deadline;
import jimbot.tasktype.Event;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.tasktype.ToDo;
import jimbot.ui.UI;
import jimbot.util.Parser;

/**
 * Main class for Jimbot application.
 * Initializes UI, storage and tasklist, and includes method for retrieving a response to user inputs.
 *
 * @author limjimin-nus
 */
public class Jimbot {
    private final Storage userStorage;
    private final TaskList userList;
    private final UI user;
    private String commandType;

    /**
     * Initializes a new Jimbot instance.
     * Loads user data from the specified file.
     *
     * @param filePath Path to the file used for storing and loading user data.
     */
    public Jimbot(String filePath) {
        user = new UI();
        userStorage = new Storage(filePath);
        userList = userStorage.load();
    }

    public String getResponse(String userInput) {
        try {
            commandType = userInput.split(" ")[0];
            Commands cmd = Commands.fromString(commandType);
            int taskCount = userList.getTaskCount();

            switch (cmd) {
            case BYE:
                return user.goodBye();
            case LIST:
                return user.printList(userList.getTaskList());
            case MARK: {
                // Retrieve task index as int from user string input
                int index = Parser.parseIndex(userInput, "mark", taskCount);

                // Retrieve task from list using task index
                Task task = userList.getTask(index);

                // Mark task as done
                task.markAsDone();

                // Update user data stored in storage to reflect done task
                userStorage.update(userList);

                // Print response
                return user.markRes(userList, index);
            }
            case UNMARK: {
                // Retrieve task index as int from user string input
                int index = Parser.parseIndex(userInput, "unmark", taskCount);

                // Retrieve task from list using task index
                Task task = userList.getTask(index);

                // Mark task as undone
                task.markAsUndone();

                // Update user data stored in storage to reflect undone task
                userStorage.update(userList);

                // Print response
                return user.unmarkRes(userList, index);
            }
            case DEADLINE: {
                if (!userInput.contains("/by")) {
                    throw new InvalidDeadlineException();
                }

                String[] deadline = userInput.substring(9)
                        .trim()
                        .split("/by", 2);
                String description = deadline[0].trim();
                String by = deadline[1].trim();

                if (by.isEmpty() || description.isEmpty()) {
                    throw new InvalidDeadlineException();
                }

                LocalDateTime dateTime = Parser.parseDateTime(by);
                Deadline userDeadline = new Deadline(description, dateTime);

                userList.addToList(userDeadline);
                userStorage.update(userList);
                return user.addTask(userDeadline, taskCount + 1);
            }
            case EVENT: {
                if (!userInput.contains("/from") || !userInput.contains("/to")) {
                    throw new InvalidEventException();
                } else {
                    String[] event = userInput.substring(6)
                            .trim()
                            .split("/from", 2);
                    String description = event[0].trim();
                    String[] timings = event[1]
                            .trim()
                            .split("/to", 2);
                    String from = timings[0].trim();
                    String to = timings[1].trim();

                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new InvalidEventException();
                    }

                    LocalDateTime dateTime1 = Parser.parseDateTime(from);
                    LocalDateTime dateTime2 = Parser.parseDateTime(to);
                    Event userEvent = new Event(description, dateTime1, dateTime2);

                    userList.addToList(userEvent);
                    userStorage.update(userList);
                    return user.addTask(userEvent, taskCount + 1);
                }
            }
            case TODO: {
                String description = userInput.substring(4).trim();

                if (description.isEmpty()) {
                    throw new InvalidToDoException();
                } else {
                    ToDo userToDo = new ToDo(description);

                    userList.addToList(userToDo);
                    userStorage.update(userList);
                    return user.addTask(userToDo, taskCount + 1);
                }
            }
            case DELETE:
                int index = Parser.parseIndex(userInput, "delete", taskCount);
                Task task = userList.getTask(index);

                userList.deleteFromList(userList.getTask(index));
                userStorage.update(userList);
                return user.deleteTask(task, taskCount - 1);
            case FIND: {
                String description = userInput.toLowerCase()
                        .substring(4)
                        .trim();
                List<Task> tasks = userList.findTasks(description).getTaskList();

                return user.printList(tasks);
            }
            case DATE, TODAY:
                LocalDate date = LocalDate.now();

                if (!userInput.equals("today")) {
                    date = Parser.parseDate(userInput);
                }

                List<Task> tasks = userList.findTasksAtDate(date).getTaskList();

                return user.printListAtDate(tasks,
                        date.isEqual(LocalDate.now()) || userInput.equals("today"));
            case HELP:
                return user.commandList();
            default:
                return user.respond(userInput);
            }
        } catch (InvalidDateTimeException | InvalidDeadlineException | InvalidEventException
                 | NoSuchTaskException | InvalidToDoException | TaskLimitException e) {
            return e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
