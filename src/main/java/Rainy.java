import java.util.ArrayList;
import java.util.Scanner;

public class Rainy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Rainy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();

                if (input.equals("bye")) {
                    ui.showBye();
                    isExit = true;

                } else if (input.equals("list")) {
                    ui.showLine();
                    tasks.printList();
                    ui.showLine();

                } else if (input.startsWith("mark")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("oh no!!! please specify which task number to mark.");
                    }
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.markTask(taskNumber);
                    storage.save(tasks.getAllTasks());
                    ui.showLine();
                    System.out.println("yay! :D i've marked this task as done:\n  " + tasks.getTask(taskNumber));
                    ui.showLine();

                } else if (input.startsWith("unmark")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("oh no!!! please specify which task number to unmark.");
                    }
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.unmarkTask(taskNumber);
                    storage.save(tasks.getAllTasks());
                    ui.showLine();
                    System.out.println("oki, i've marked this task as not done yet:\n  " + tasks.getTask(taskNumber));
                    ui.showLine();

                } else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new RainyException("oh no!!! the description of a todo cannot be empty.");
                    }
                    String desc = input.substring(4).trim();
                    Task t = new Todo(desc);
                    tasks.addTask(t);
                    storage.save(tasks.getAllTasks());
                    ui.showLine();
                    System.out.println("oki! i've added this task:\n  " + t +
                            "\nnow you have " + tasks.size() + " tasks left!");
                    ui.showLine();

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).split(" /by ");
                    if (parts.length < 2) {
                        throw new RainyException("oh no!!! please specify task and deadline.");
                    }
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks.addTask(t);
                    storage.save(tasks.getAllTasks());
                    ui.showLine();
                    System.out.println("oki! i've added this task:\n  " + t +
                            "\nnow you have " + tasks.size() + " tasks left!");
                    ui.showLine();

                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new RainyException("oh no!!! please specify task from when to when.");
                    }
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    tasks.addTask(t);
                    storage.save(tasks.getAllTasks());
                    ui.showLine();
                    System.out.println("oki! i've added this task:\n  " + t +
                            "\nnow you have " + tasks.size() + " tasks left!");
                    ui.showLine();

                } else if (input.startsWith("delete")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("oh no!!! please specify which task number to delete.");
                    }
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task removedTask = tasks.deleteTask(taskNumber);
                    storage.save(tasks.getAllTasks());
                    ui.showLine();
                    System.out.println("oki! i've removed this task:\n  " + removedTask +
                            "\nnow you have " + tasks.size() + " tasks left!");
                    ui.showLine();

                } else {
                    throw new RainyException("oh no!!! idk what that means... :-(");
                }

            } catch (RainyException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("oopsies, something went wrong: " + e.getMessage());
            }
        }

        ui.close();
    }

    public static void main(String[] args) {
        new Rainy("data/rainy.txt").run();
    }
}