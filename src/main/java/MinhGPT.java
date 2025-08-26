import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinhGPT {
    /**
     * Main entry-point for the program.
     */
    public static void main(String[] args) {
        // Get all flags
        boolean isFresh = args.length > 0 && args[0].equals("--fresh");

        // Initialisation
        Task.initialise();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = isFresh ? new ArrayList<>() : storage.loadTasks();
        ui.printWelcome();

        // Main program loop
        while (true) {
            ui.printNext();
            String input = scanner.nextLine().trim();
            ui.printSeperate();

            if (input.matches("^bye$")) {
                // User want to exit
                scanner.close();
                storage.saveTasks(tasks);
                break;
            } else if (input.matches("^list$")) {
                // List all tasks
                ui.printList(tasks);
            } else if (input.matches("^mark \\d+$")) {
                // Mark a task as done
                int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    ui.printIndexError();
                } else {
                    tasks.get(index).markAsDone();
                    ui.printMark(tasks.get(index));
                }
            } else if (input.matches("^unmark \\d+$")) {
                // Mark a task as undone
                int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    ui.printIndexError();
                } else {
                    tasks.get(index).markAsUndone();
                    ui.printUnmark(tasks.get(index));
                }
            } else if (input.matches("^delete \\d+$")) {
                // Delete a task
                int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    ui.printIndexError();
                } else {
                    ui.printDelete(tasks.get(index));
                    tasks.remove(index);
                }
            } else {
                // All other inputs are considered as trying to add a task
                try {
                    tasks.add(Task.parseTask(input));
                    ui.printAdd(tasks.get(tasks.size() - 1));
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // Cleaning up resources and exit
        scanner.close();
        ui.printExit();
    }
}
