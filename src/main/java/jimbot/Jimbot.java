package jimbot;

import jimbot.exceptions.*;
import jimbot.ui.UI;
import jimbot.tasktypes.*;
import jimbot.util.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Jimbot {
    private Storage userStorage;
    private TaskList userList;
    private UI user;

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
                    int index = Parser.parseIndex(userInput, "mark", taskCount);
                    Task task = userList.getTask(index);
                    task.markAsDone();
                    user.markRes(userList, index);
                    userStorage.update(userList);

                } else if (userInput.startsWith("unmark")) {
                    int index = Parser.parseIndex(userInput, "unmark", taskCount);
                    Task task = userList.getTask(index);
                    task.markAsUndone();
                    user.unmarkRes(userList, index);
                    userStorage.update(userList);

                } else if (userInput.startsWith("deadline")) {

                    if (!userInput.contains("/by")) throw new InvalidDeadlineException();
                    String[] deadline = userInput.substring(9)
                            .trim()
                            .split("/by", 2);
                    String description = deadline[0].trim();
                    String by = deadline[1].trim();
                    if (by.isEmpty() || description.isEmpty()) throw new InvalidDeadlineException();

                    LocalDateTime dateTime = Parser.parseDateTime(by);

                    Deadline userDeadline = new Deadline(description, dateTime);
                    userList.addToList(userDeadline);
                    user.addTask(userDeadline, taskCount + 1);
                    userStorage.update(userList);

                } else if (userInput.startsWith("event")) {

                    if (!userInput.contains("/from") || !userInput.contains("/to"))
                        throw new InvalidEventException();
                    else {
                        String[] event = userInput.substring(6)
                                .trim()
                                .split("/from", 2);

                        String description = event[0].trim();
                        String[] timings = event[1]
                                .trim()
                                .split("/to", 2);

                        String from = timings[0].trim();
                        String to = timings[1].trim();

                        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) throw new InvalidEventException();

                        LocalDateTime dateTime1 = Parser.parseDateTime(from);
                        LocalDateTime dateTime2 = Parser.parseDateTime(to);

                        Event userEvent = new Event(description, dateTime1, dateTime2);
                        userList.addToList(userEvent);
                        user.addTask(userEvent, taskCount + 1);
                        userStorage.update(userList);

                    }

                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();
                    if (description.isEmpty()) throw new InvalidToDoException();
                    else {
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

                } else if (userInput.contains("/")) {
                    LocalDate date = Parser.parseDate(userInput);
                    user.printListAtDate(userList.getTasksAtDate(date));

                } else {
                    user.echo(userInput);

                }
            } catch (InvalidDateTimeException | InvalidDeadlineException | InvalidEventException |
                     InvalidIndexException | InvalidToDoException | TaskLimitException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Jimbot("data/database.txt").run();
    }
}
