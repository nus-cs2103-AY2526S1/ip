import exceptions.*;
import taskTypes.Deadline;
import taskTypes.Event;
import taskTypes.ToDo;
import taskTypes.taskList;

import java.util.Scanner;

public class Jimbot {
    public static void main(String[] args) {
        String userInput;
        Scanner scanner = new Scanner(System.in);
        Response user = new Response();
        taskList userList = new taskList();

        user.hello("Jimbot");

        while (true) {
            userInput = scanner.nextLine().trim();

            try {
                int taskCount = userList.getTaskCount();
                if (userInput.toLowerCase().contains("bye")) {
                    user.goodBye();
                    break;

                } else if (userInput.equalsIgnoreCase("list")) {
                    user.printList(userList.getTaskList());

                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]);

                    if (index < taskCount) {
                        userList.getTask(index).markAsDone();
                        user.markRes(userList, index);
                    } else throw new InvalidIndexException();

                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    if (index < taskCount) {
                        userList.getTask(index).markAsUndone();
                        user.unmarkRes(userList, index);
                    } else throw new InvalidIndexException();
                } else if (userInput.startsWith("deadline")) {

                    if (!userInput.contains("/by")) throw new InvalidDeadlineException();
                    else {
                        String[] deadline = userInput.substring(9)
                                .trim()
                                .split("/by", 2);
                        String description = deadline[0].trim();
                        String by = deadline[1].trim();
                        if (taskCount >= 100) throw new TaskLimitException();
                        else if (by.isEmpty()) throw new InvalidDeadlineException();
                        else {
                            Deadline userDeadline = new Deadline(description, by);
                            userList.addToList(userDeadline);
                            user.addTask(userDeadline, taskCount);
                        }
                    }
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
                        if (taskCount >= 100) throw new TaskLimitException();
                        else if (from.isEmpty() || to.isEmpty()) throw new InvalidEventException();
                        else {
                            Event userEvent = new Event(description, from, to);
                            userList.addToList(userEvent);
                            user.addTask(userEvent, taskCount);
                        }
                    }
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();

                    if (taskCount >= 100) throw new TaskLimitException();
                    else if (description.isEmpty()) throw new InvalidToDoException();
                    else {
                        ToDo userToDo = new ToDo(description);
                        userList.addToList(userToDo);
                        user.addTask(userToDo, taskCount);
                    }
                } else {
                    user.echo(userInput);
                }
            } catch (InvalidDeadlineException | InvalidEventException |
                     InvalidIndexException | InvalidToDoException | TaskLimitException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
