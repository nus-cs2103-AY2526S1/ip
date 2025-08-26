package rafayel.ui;

import java.io.IOException;
import java.util.Scanner;

import rafayel.RafayelException;
import rafayel.Storage;
import rafayel.command.Parser;
// import rafayel.command.Parser.Command;
import rafayel.task.Deadline;
import rafayel.task.Event;
import rafayel.task.Task;
import rafayel.task.TaskList;
import rafayel.task.Todo;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;

public class Rafayel {

    private static String FILE_PATH = "./data/rafayel.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    private static void handleMarkCommand(String input, TaskList tasks, boolean markTask) throws RafayelException {
        int minLen = markTask ? 5 : 7;
        if (input.length() <= minLen) {
            throw new RafayelException("Please state what task to be marked/unmarked.");
        }
        String[] temp = input.split(" ");
        int taskNumber = Integer.parseInt(temp[1]);

        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new RafayelException("Invalid task number.");
        }
        taskNumber--;
        if (markTask) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber).toString());
        } else {
            tasks.get(taskNumber).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber).toString());

        }

    }

    private static void printNewTaskString(Task newTask, int counter) {
        // FOR CREATING TASKS
        String TASK_START = "Got it. I've added this task:";

        System.out.println(TASK_START);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    private static void handleTodoCommand(String input, TaskList tasks) throws RafayelException {
        if (input.length() <= 5) {
            throw new RafayelException("Please add in the description of the Todo task.");
        }

        Todo newTask = new Todo(input.substring(5).trim());
        tasks.add(newTask);

        printNewTaskString(newTask, tasks.getSize());
    }

    private static void handleDeadlineCommand(String input, TaskList tasks) throws RafayelException {
        if (input.length() <= 10) {
            throw new RafayelException("Please add in the description of the Deadline task.");
        }
        if (!input.contains("/by")) {
            throw new RafayelException("Deadline format is wrong. Example: deadline [desc] /by [time]");
        }

        String[] taskInfo = input.substring(9).split("/by ");
        LocalDateTime dateTime = handleReadDate(taskInfo[1].trim());
        Deadline newTask = new Deadline(taskInfo[0].trim(), dateTime);
        tasks.add(newTask);

        printNewTaskString(newTask, tasks.getSize());
    }

    public static LocalDateTime handleReadDate(String input) {
        // check if valid format
        DateTimeFormatter[] differenTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differenTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        System.out.println("Please use one of: MMM d yyyy HH:mm | yyyy/MM/dd HH:mm | dd-MM-yyyy HH:mm");

        return null;
    }

    private static void handleEventCommand(String input, TaskList tasks) throws RafayelException {
        if (input.length() <= 6) {
            throw new RafayelException("Please add in the description of the Event task.");
        }
        if (!input.contains("/from")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }
        if (!input.contains("/to")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }

        String[] taskInfo = input.substring(6).split("/");
        LocalDateTime dateTimeFrom = handleReadDate(taskInfo[1].substring(5).trim());
        LocalDateTime dateTimeTo = handleReadDate(taskInfo[2].substring(3).trim());

        Event newTask = new Event(taskInfo[0].trim(), dateTimeFrom, dateTimeTo);

        tasks.add(newTask);

        printNewTaskString(newTask, tasks.getSize());
    }

    public Rafayel(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RafayelException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws RafayelException, Exception {
        ui.showWelcome();
        boolean isExit = false;

        Scanner sc = new Scanner(System.in);

        while (!isExit) {
            try {
                if (!sc.hasNextLine()) {
                    ui.showLine();
                    ui.showExit();
                    break;
                }

                String input = sc.nextLine();

                if (input == null) {
                    ui.showLine();
                    ui.showExit();
                    break;
                }

                ui.showLine();

                Parser.Command command = Parser.parse(input);
                // System.out.println(command);

                switch (command) {
                case BYE:
                    ui.showExit();
                    return;

                case LIST:
                    tasks.getTaskList();
                    break;

                case MARK:
                    // Mark task
                    handleMarkCommand(input, tasks, true);
                    break;

                case UNMARK:
                    // Unmark task
                    handleMarkCommand(input, tasks, false);
                    break;

                case TODO:
                    // Create Todo Task
                    handleTodoCommand(input, tasks);
                    break;

                case DEADLINE:
                    // Create Deadline Task
                    handleDeadlineCommand(input, tasks);
                    break;

                case EVENT:
                    // Create Event Task
                    handleEventCommand(input, tasks);
                    break;

                case DELETE:
                    // Delete tasks
                    if (input.length() <= 7) {
                        throw new RafayelException("Please indicate which task to delete (i.e. delete 1)");
                    }
                    String[] temp = input.split(" ");
                    int taskNumber = Integer.parseInt(temp[1]);

                    taskNumber--;
                    Task deletedTask = tasks.remove(taskNumber);

                    System.out.println("Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have "
                            + tasks.getSize() + " tasks in the list.");
                    break;

                case UNKNOWN:
                    throw new RafayelException("Please enter a valid prompt! (i.e. todo/deadline/event)");
                }
            } catch (RafayelException e) {
                ui.showError(e.getMessage());
            } finally {
                storage.save(tasks.getAll());
                ui.showLine();
            }
        }
        storage.save(tasks.getAll());
        // ui.showLine();
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        new Rafayel(FILE_PATH).run();
    }
}