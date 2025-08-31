package poopiemeow;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import poopiemeow.exception.EmptyDescriptionException;
import poopiemeow.parser.Parser;
import poopiemeow.storage.Storage;
import poopiemeow.task.Task;
import poopiemeow.ui.Ui;

public class PoopieMeow {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner scanner;

    public PoopieMeow(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        scanner = new Scanner(System.in);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand(scanner);
            
            try {
                if (input.equals("bye")) {
                    ui.showGoodbye();
                    break;
                } else if (input.equals("list")) {
                    ui.showTaskList(tasks.getTasks());
                } else if (input.startsWith("mark ")) {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            ui.showError("Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to " + tasks.size() + ").");
                        } else {
                            Task task = tasks.getTask(index);
                            task.markAsDone();
                            storage.save(tasks.getTasks());
                            ui.showTaskMarked(task);
                        }
                    } catch (NumberFormatException e) {
                        ui.showError("Please enter a valid task number after 'mark'.");
                    }
                } else if (input.startsWith("unmark ")) {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            ui.showError("Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to " + tasks.size() + ").");
                        } else {
                            Task task = tasks.getTask(index);
                            task.markAsUndone();
                            storage.save(tasks.getTasks());
                            ui.showTaskUnmarked(task);
                        }
                    } catch (NumberFormatException e) {
                        ui.showError("Please enter a valid task number after 'unmark'.");
                    }
                } else if (input.startsWith("delete ")) {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            ui.showError("Task number " + (index + 1) + " does not exist. Please enter a valid task number (1 to " + tasks.size() + ").");
                        } else {
                            Task removedTask = tasks.deleteTask(index);
                            storage.save(tasks.getTasks());
                            ui.showTaskDeleted(removedTask, tasks.size());
                        }
                    } catch (NumberFormatException e) {
                        ui.showError("Please enter a valid task number after 'delete'.");
                    }
                } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    Task newTask = Parser.parseTask(input);
                    tasks.addTask(newTask);
                    storage.save(tasks.getTasks());
                    ui.showTaskAdded(newTask, tasks.size());
                } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    throw new EmptyDescriptionException("The description cannot be empty!");
                } else if (input.startsWith("show ")) {
                    String dateStr = input.substring(5);
                    try {
                        LocalDateTime date = LocalDateTime.parse(dateStr + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        ui.showTasksOnDate(tasks.getTasks(), date);
                    } catch (DateTimeParseException e) {
                        ui.showError("Please provide date in the format: yyyy-mm-dd");
                    }
                } else if (input.trim().isEmpty()) {
                    throw new EmptyDescriptionException("Please enter a command!");
                } else {
                    ui.showError("I don't understand '" + input + "'. Please try a valid command!");
                }
            } catch (EmptyDescriptionException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("Invalid format, use the format yyyy-mm-dd hhmm for dates and times!\nFor example: 2023-10-15 1430");
            } catch (IOException e) {
                ui.showError("Error saving to file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new PoopieMeow("data/tasks.txt").run();
    }
}