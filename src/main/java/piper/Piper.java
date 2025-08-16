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
            if (userInput.equals("bye")) { // user is inactive
                ui.farewellUser();
                exit = true;
            } else if (userInput.equals("list")) {
                // list all tasks
                ui.displayTasks(tasks);
            } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                // mark task as done or undone

                String[] substrings = userInput.split(" ", 2); // split user input into action substring nd task index substring
                String actionSubstring = substrings[0];
                String numberSubstring = substrings[1];
                int taskNumber = Integer.parseInt(numberSubstring);
                int index = taskNumber - 1; // task list starts from index 1 but array list starts from index 0

                Task task = tasks.getTask(index);
                if (actionSubstring.equals("mark")) {
                    task.markDone();
                } else if (actionSubstring.equals("unmark")) {
                    task.markUndone();
                }

                ui.showTaskStatus(task);
            } else {
                // add new task

                String[] substrings = userInput.split(" ", 2);
                String taskType = substrings[0];
                String remainingSubstring = substrings[1];
                Task task = null;

                switch (taskType) {
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
            }
        }
        ui.close();
    }
}