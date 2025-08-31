import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toFileString() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }
}

class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, String deadlineStr) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
        }
        this.deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toFileString() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + 
               deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}

class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String startTimeStr, String endTimeStr) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of an event cannot be empty.");
        }
        this.startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toFileString() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + 
               startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "|" + 
               endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + 
               startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: " +
               endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}

class Ui {
    private static final String LINE = "____________________________________________________________";
    
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm PoopieMeow");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(" Oops! " + message);
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showTasksOnDate(ArrayList<Task> tasks, LocalDateTime date) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        int count = 0;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDeadline().toLocalDate().equals(date.toLocalDate())) {
                    System.out.println(" " + (++count) + "." + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().toLocalDate().equals(date.toLocalDate()) || 
                    event.getEndTime().toLocalDate().equals(date.toLocalDate())) {
                    System.out.println(" " + (++count) + "." + task);
                }
            }
        }
        if (count == 0) {
            System.out.println(" No tasks found on this date.");
        }
        System.out.println(LINE);
    }
}

class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        FileWriter writer = new FileWriter(filePath.toFile());
        for (Task task : tasks) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }

        Scanner fileScanner = new Scanner(new File(filePath.toString()));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            try {
                String[] parts = line.split("\\|");
                if (parts.length < 3) continue;

                Task task = null;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        task = new Event(description, parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (isDone) task.markAsDone();
                    tasks.add(task);
                }
            } catch (Exception e) {
                continue;
            }
        }
        fileScanner.close();
        return tasks;
    }
}

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}

class Parser {
    public static Task parseTask(String input) throws EmptyDescriptionException, DateTimeParseException {
        if (input.startsWith("todo ")) {
            return new Todo(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.split(" /by ");
            if (parts.length != 2) {
                throw new EmptyDescriptionException("Please provide a deadline in the format: deadline <description> /by yyyy-MM-dd HHmm");
            }
            return new Deadline(parts[0].substring(9), parts[1]);
        } else if (input.startsWith("event ")) {
            String[] parts = input.split(" /from ");
            if (parts.length != 2) {
                throw new EmptyDescriptionException("Please provide event times in the format: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            String[] secondParts = parts[1].split(" /to ");
            if (secondParts.length != 2) {
                throw new EmptyDescriptionException("Please provide event times in the format: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            return new Event(parts[0].substring(6), secondParts[0], secondParts[1]);
        }
        throw new EmptyDescriptionException("Unknown command type!");
    }
}

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
