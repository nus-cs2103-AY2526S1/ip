package piper;

import piper.ui.Ui;
import piper.task.TaskList;
import piper.task.Task;
import piper.task.Todo;
import piper.task.Deadline;
import piper.task.Event;

public class Piper {
    public static void main(String[] args) {
        final String CHATBOT_NAME = "Piper";
        Ui ui = new Ui(CHATBOT_NAME);
        TaskList tasks = new TaskList();
        boolean exit = false;

        ui.greetUser();

        while (!exit) { // user is active
            String userInput = ui.read();
            String[] substrings = userInput.split(" ", 2);
            String cmd = substrings[0];

            if (substrings.length < 2) {
                if (userInput.equals("bye")) { // user is inactive
                    ui.farewellUser();
                    exit = true;
                } else if (userInput.equals("list")) {
                    // list all tasks
                    ui.displayTasks(tasks);
                }
            } else {
                String remainingSubstring = substrings[1];

                if (cmd.equals("mark") || cmd.equals("unmark")) {
                    // mark task as done or undone

                    int taskNumber = Integer.parseInt(remainingSubstring);
                    int index = taskNumber - 1; // task list starts from index 1 but array list starts from index 0

                    Task task = tasks.getTask(index);

                    switch (cmd) {
                        case "mark":
                            task.markDone();
                            break;
                        case "unmark":
                            task.markUndone();
                            break;
                    }

                    ui.showTaskStatus(task);
                } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                    // add new task
                    Task task = null;

                    switch (cmd) {
                        case "todo":
                            task = new Todo(remainingSubstring);
                            break;
                        case "deadline": {
                            String[] descriptionAndBy = remainingSubstring.split("/by ", 2);
                            String description = descriptionAndBy[0];
                            String by = descriptionAndBy[1];
                            task = new Deadline(description, by);
                            break;
                        }
                        case "event": {
                            String[] descriptionAndFrom = remainingSubstring.split("/from ", 2);
                            String description = descriptionAndFrom[0];
                            String[] fromAndTo = descriptionAndFrom[1].split("/to ", 2);
                            String from = fromAndTo[0];
                            String to = fromAndTo[1];
                            task = new Event(description, from, to);
                            break;
                        }
                    }

                    tasks.addTask(task);
                    ui.showAddedTask(task);
                    ui.getTasksSize(tasks);
                } else {
                    // error handling
                }
            }
        }
        ui.close();
    }
}