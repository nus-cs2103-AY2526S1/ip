package piper;

import piper.ui.Ui;
import piper.task.TaskList;
import piper.task.Task;
import piper.task.Todo;
import piper.task.Deadline;
import piper.task.Event;
import piper.PiperException;

public class Piper {
    public static void main(String[] args) {
        final String CHATBOT_NAME = "Piper";
        Ui ui = new Ui(CHATBOT_NAME);
        TaskList tasks = new TaskList();
        boolean exit = false;

        ui.greetUser();

        while (!exit) { // user is active
            String userInput = ui.read();
            if (userInput == null) {
                break;
            }
            userInput = userInput.trim();
            if (userInput.isEmpty()) {
                ui.showError("CHIRP CHIRP! Don't think you said anything there. Try tweeting a command!");
                continue;
            }
            try {
                String[] substrings = userInput.split("\\s", 2);
                String cmd = substrings[0];

                if (substrings.length < 2) {
                    if (cmd.equals("bye")) { // user is inactive
                        ui.farewellUser();
                        exit = true;
                    } else if (cmd.equals("list")) {
                        // list all tasks
                        ui.displayTasks(tasks);
                    } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                        // missing task description
                        throw new PiperException("TWEET TWEET! What are we supposed to do? Please specify the description!");
                    } else if (cmd.equals("mark") || cmd.equals("unmark") || cmd.equals("delete")) {
                        // missing task index
                        throw new PiperException("CHIRRUP! Which task should I peck at? Please give me the task index!");
                    } else {
                        // unrecognisable command
                        throw new PiperException("CHIRP CHIRP! I don't recognise the tune called '" + cmd + "'. Try another command?");
                    }
                } else {
                    String remainingSubstring = substrings[1];

                    if (cmd.equals("mark") || cmd.equals("unmark")) {
                        // mark task as done or undone

                        try {
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
                        } catch (Exception e) {
                            // task index is outside of array range
                            throw new PiperException("PEEP! That task flew out of the nest. Please check using 'list' to see which tasks are home!");
                        }
                    } else if (cmd.equals("delete")) {
                        // delete task

                        try {
                            int taskNumber = Integer.parseInt(remainingSubstring);
                            int index = taskNumber - 1;
                            Task task = tasks.getTask(index);

                            tasks.deleteTask(index);
                            ui.showDeletedTask(task);
                            ui.getTasksSize(tasks);
                        } catch (Exception e) {
                            throw new PiperException("PEEP! Bad egg. Please check using 'list' to see which tasks are home!");
                        }
                    } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                        // add new task

                        Task task = null;

                        switch (cmd) {
                            case "todo":
                                task = new Todo(remainingSubstring);
                                break;
                            case "deadline": {
                                try {
                                    String[] descriptionAndBy = remainingSubstring.split("/by ", 2);
                                    String description = descriptionAndBy[0].trim();
                                    if (description.isEmpty()) {
                                        throw new IllegalArgumentException();
                                    }
                                    String by = descriptionAndBy[1].trim();
                                    if (by.isEmpty()) {
                                        throw new IllegalArgumentException();
                                    }
                                    task = new Deadline(description, by);
                                } catch (Exception e) {
                                    throw new PiperException("OOPS! That deadline's off key. Please format the task as 'deadline (description) /by (deadline)'!");
                                }
                                break;
                            }
                            case "event": {
                                try {
                                    String[] descriptionAndFrom = remainingSubstring.split("/from ", 2);
                                    String description = descriptionAndFrom[0].trim();
                                    if (description.isEmpty()) {
                                        throw new IllegalArgumentException();
                                    }
                                    String[] fromAndTo = descriptionAndFrom[1].split("/to ", 2);
                                    String from = fromAndTo[0].trim();
                                    if (from.isEmpty()) {
                                        throw new IllegalArgumentException();
                                    }
                                    String to = fromAndTo[1].trim();
                                    if (to.isEmpty()) {
                                        throw new IllegalArgumentException();
                                    }
                                    task = new Event(description, from, to);
                                } catch (Exception e) {
                                    throw new PiperException("EEP! Your event's missing a few notes. Please format the event as 'event (description) /from (start time) /to (end time)'!");
                                }
                                break;
                            }
                        }

                        tasks.addTask(task);
                        ui.showAddedTask(task);
                        ui.getTasksSize(tasks);
                    } else {
                        // unrecognisable string
                        throw new PiperException("CHEEP CHEEP! I can't quite sing along with '" + userInput + "'. Wanna try another command?");
                    }
                }
            } catch (PiperException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }
}