package jimbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

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

    public Jimbot(String filePath) {
        user = new UI();
        userStorage = new Storage(filePath);
        userList = userStorage.load();

        user.hello("Jimbot");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();

            try {
                int taskCount = userList.getTaskCount();
                if (userInput.toLowerCase().matches(".*\\b(bye|goodbye)\\b.*")) {
                    user.goodBye();
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    user.printList(userList.getTaskList());
                } else if (userInput.startsWith("mark")) {
                    // Retrieve task index as int from user string input
                    int index = Parser.parseIndex(userInput, "mark", taskCount);

                    // Retrieve task from list using task index
                    Task task = userList.getTask(index);

                    // Mark task as done
                    task.markAsDone();

                    // Print response
                    user.markRes(userList, index);

                    // Update user data stored in storage to reflect done task
                    userStorage.update(userList);
                } else if (userInput.startsWith("unmark")) {
                    // Retrieve task index as int from user string input
                    int index = Parser.parseIndex(userInput, "unmark", taskCount);

                    // Retrieve task from list using task index
                    Task task = userList.getTask(index);

                    // Mark task as undone
                    task.markAsUndone();

                    // Print response
                    user.unmarkRes(userList, index);

                    // Update user data stored in storage to reflect undone task
                    userStorage.update(userList);
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
                    user.addTask(userDeadline, taskCount + 1);
                    userStorage.update(userList);
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
                        user.addTask(userEvent, taskCount + 1);
                        userStorage.update(userList);
                    }
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();

                    if (description.isEmpty()) {
                        throw new InvalidToDoException();
                    } else {
                        ToDo userToDo = new ToDo(description);

                        userList.addToList(userToDo);
                        user.addTask(userToDo, taskCount + 1);
                        userStorage.update(userList);
                    }
                } else if (userInput.startsWith("delete")) {
                    int index = Parser.parseIndex(userInput, "delete", taskCount);
                    Task task = userList.getTask(index);

                    userList.deleteFromList(userList.getTask(index));
                    user.deleteTask(task, taskCount - 1);
                    userStorage.update(userList);
                } else if (userInput.startsWith("find")) {
                    String description = userInput.toLowerCase()
                            .substring(4)
                            .trim();
                    List<Task> tasks = userList.findTasks(description).getTaskList();

                    user.printList(tasks);
                } else if (userInput.contains("/") || userInput.equalsIgnoreCase("today")) {
                    LocalDate date = LocalDate.now();

                    if (!userInput.equals("today")) {
                        date = Parser.parseDate(userInput);
                    }

                    List<Task> tasks = userList.findTasksAtDate(date).getTaskList();

                    user.printListAtDate(tasks,
                            date.isEqual(LocalDate.now()) || userInput.equals("today"));
                } else if (userInput.equalsIgnoreCase("help")) {
                    user.commandList();
                } else {
                    user.respond(userInput);
                }
            } catch (InvalidDateTimeException | InvalidDeadlineException | InvalidEventException
                     | NoSuchTaskException | InvalidToDoException | TaskLimitException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Jimbot("data/database.txt").run();
    }
}
