package ip;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

/**
 * The Note class represents a simple command-line task manager chatbot.
 * Users can add todos, deadlines, and events, mark/unmark tasks as done,
 * delete tasks, find tasks by keyword, and save/load tasks from a file.
 */
public class Note {
    private static final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private List<Task> tasks;

    /**
     * Main entry point for the Note application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Note().run();
    }

    /**
     * Runs the main program loop.
     * Handles user input commands such as todo, deadline, event, list, mark,
     * unmark, delete, find, and bye. Loads tasks from storage on start and
     * saves tasks to storage after modifications.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        storage = new Storage(FILE_PATH);

        // Load existing tasks
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Could not load tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        // Print welcome message
        String logo =
                " _   _       _        \n"
                        + "| \\ | | ___ | |_ ___  \n"
                        + "|  \\| |/ _ \\| __/ _ \\ \n"
                        + "| |\\  | (_) | || (_) |\n"
                        + "|_| \\_|\\___/ \\__\\___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Note");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");

            try {
                if (input.equals("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println(" No tasks yet.");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new NoteException("Invalid task number to mark!");
                    }
                    tasks.get(index).markAsDone();
                    saveTasks();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(index));
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new NoteException("Invalid task number to unmark!");
                    }
                    tasks.get(index).markAsNotDone();
                    saveTasks();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                } else if (input.startsWith("delete ")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new NoteException("Invalid task number to delete!");
                        }
                        Task removedTask = tasks.remove(index);
                        saveTasks();
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + removedTask);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    } catch (NumberFormatException e) {
                        throw new NoteException("Please specify a valid task number to delete.");
                    }
                } else if (input.startsWith("todo ")) {
                    String desc = input.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new NoteException("The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    tasks.add(t);
                    saveTasks();
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ", 2);
                    if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                        throw new NoteException("Deadline must have a description and a /by date/time.");
                    }
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks.add(t);
                    saveTasks();
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ", 3);
                    if (parts.length < 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
                        throw new NoteException("Event must have a description, /from and /to fields.");
                    }
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(t);
                    saveTasks();
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("find ")) {
                    String keyword = input.substring(5).trim(); // get the keyword after "find "
                    if (keyword.isEmpty()) {
                        throw new NoteException("Please provide a keyword to search for.");
                    }

                    List<Task> matchingTasks = new ArrayList<>();
                    for (Task t : tasks) {
                        if (t.getDescription().contains(keyword)) {
                            matchingTasks.add(t);
                        }
                    }

                    if (matchingTasks.isEmpty()) {
                        System.out.println(" No matching tasks found.");
                    } else {
                        System.out.println(" Here are the matching tasks in your list:");
                        for (int i = 0; i < matchingTasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
                        }
                    }
                } else {
                    throw new NoteException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (NoteException e) {
                System.out.println(" OOPS!!! " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Saves the current list of tasks to storage.
     * If an IOException occurs, it prints an error message.
     */
    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println(" Error saving tasks: " + e.getMessage());
        }
    }
}
