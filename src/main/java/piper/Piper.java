package piper;

import piper.ui.Ui;
import piper.task.TaskList;
import piper.task.Task;
import piper.task.Todo;
import piper.task.Deadline;
import piper.task.Event;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Piper {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "piper.txt";
    private static final Path SAVE_PATH = Paths.get(DATA_DIR, DATA_FILE);

    public static void main(String[] args) {
        final String CHATBOT_NAME = "Piper";
        Ui ui = new Ui(CHATBOT_NAME);
        TaskList tasks = new TaskList();
        boolean exit = false;

        try {
            Path dir = SAVE_PATH.getParent();
            if (dir != null && !Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            if (!Files.exists(SAVE_PATH)) {
                Files.createFile(SAVE_PATH);
            }

            List<String> lines = Files.readAllLines(SAVE_PATH, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String[] substrings = line.split(" \\| ", 5);
                String taskType = substrings[0];
                String doneField = substrings[1];
                String description = substrings[2];
                boolean isDone = "1".equals(doneField);

                Task task = null;
                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, substrings[3]);
                    break;
                case "E":
                    task = new Event(description, substrings[3], substrings[4]);
                    break;
                }

                if (isDone) {
                    task.markDone();
                }
                tasks.addTask(task);
            }
        } catch (IOException e) {
            ui.showError("SQUAWK! Can't seem to read the save file at " + SAVE_PATH + ": " + e.getMessage());
        }

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

                            saveAll(tasks);

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

                            saveAll(tasks);

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

                        saveAll(tasks);

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

    private static String serialize(Task task) {
        return task.toSerializedLine();
    }

    private static void saveAll(TaskList tasks) throws PiperException {
        try {
            List<String> out = new ArrayList<>();
            for (int i = 0; i < tasks.getSize(); i++) {
                out.add(serialize(tasks.getTask(i)));
            }
            Files.write(SAVE_PATH, out, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PiperException("SQUAWK! Can't seem to write tasks to " + SAVE_PATH + ": " + e.getMessage());
        }
    }

}