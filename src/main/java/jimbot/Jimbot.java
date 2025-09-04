package jimbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jimbot.exceptions.InvalidDateTimeException;
import jimbot.exceptions.InvalidDeadlineException;
import jimbot.exceptions.InvalidEventException;
import jimbot.exceptions.InvalidToDoException;
import jimbot.exceptions.NoSuchTaskException;
import jimbot.exceptions.TaskLimitException;
import jimbot.storage.Storage;
import jimbot.tasktypes.Deadline;
import jimbot.tasktypes.Event;
import jimbot.tasktypes.Task;
import jimbot.tasktypes.TaskList;
import jimbot.tasktypes.ToDo;
import jimbot.ui.UI;
import jimbot.util.Parser;

public class Jimbot {
    private final Storage userStorage;
    private final TaskList userList;
    private final UI user;
    private String commandType;

    public Jimbot(String filePath) {
        user = new UI();
        userStorage = new Storage(filePath);
        userList = userStorage.load();

        user.hello("Jimbot");
    }

    public String getResponse(String userInput) {
        commandType = userInput.split(" ")[0];
        try {
            int taskCount = userList.getTaskCount();
            if (userInput.toLowerCase().matches(".*\\b(bye|goodbye)\\b.*")) {
                return user.goodBye();
            } else if (userInput.equalsIgnoreCase("list")) {
                return user.printList(userList.getTaskList());
            } else if (userInput.startsWith("mark")) {
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
            } else if (userInput.startsWith("unmark")) {
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
            } else if (userInput.startsWith("deadline")) {
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
            } else if (userInput.startsWith("event")) {
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
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(4).trim();

                if (description.isEmpty()) {
                    throw new InvalidToDoException();
                } else {
                    ToDo userToDo = new ToDo(description);

                    userList.addToList(userToDo);
                    userStorage.update(userList);
                    return user.addTask(userToDo, taskCount + 1);
                }
            } else if (userInput.startsWith("delete")) {
                int index = Parser.parseIndex(userInput, "delete", taskCount);
                Task task = userList.getTask(index);

                userList.deleteFromList(userList.getTask(index));
                userStorage.update(userList);
                return user.deleteTask(task, taskCount - 1);
            } else if (userInput.startsWith("find")) {
                String description = userInput.toLowerCase()
                        .substring(4)
                        .trim();
                List<Task> tasks = userList.findTasks(description).getTaskList();

                return user.printList(tasks);
            } else if (userInput.contains("/") || userInput.equalsIgnoreCase("today")) {
                LocalDate date = LocalDate.now();

                if (!userInput.equals("today")) {
                    date = Parser.parseDate(userInput);
                }

                List<Task> tasks = userList.findTasksAtDate(date).getTaskList();

                return user.printListAtDate(tasks,
                        date.isEqual(LocalDate.now()) || userInput.equals("today"));
            } else if (userInput.equalsIgnoreCase("help")) {
                return user.commandList();
            } else {
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
