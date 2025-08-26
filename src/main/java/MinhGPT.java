import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinhGPT {
    /**
     * Save the provided list of tasks 'tasks' into a file.
     */
    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("mem.txt");
            String content = "";
            for (int i = 0; i < tasks.size(); i++) {
                ArrayList<String> commands = tasks.get(i).toCommands();
                for (String cmd : commands) {
                    content += content.equals("") ? cmd : "\n" + cmd;
                }
            }
            writer.write(content);
            writer.close();
            Logger.info("Tasks are saved in mem.txt.");
        } catch (IOException e) {
            Logger.error(e.getMessage());
            Logger.error("Tasks are not saved.");
        }
    }

    /**
     * Load list of tasks from disk if it exists.
     */
    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("mem.txt"));
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.matches("^mark$")) {
                    // Mark the last task as done
                    if (tasks.size() > 0) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                } else {
                    try {
                        tasks.add(Task.parseTask(command));
                    } catch (ParseException e) {
                        System.out.println("ERROR: memory file is corrupted.");
                    }
                }
            }
            scanner.close();
            Logger.info(
                    "Found mem.txt, loading tasks from previous sessions. To disable, run with --fresh flag.");
        } catch (FileNotFoundException e) {
            Logger.info("No memory file detected. Starting fresh.");
        }

        return tasks;
    }

    /**
     * Main entry-point for the program.
     */
    public static void main(String[] args) {
        // Get all flags
        boolean isFresh = args.length > 0 && args[0].equals("--fresh");

        // Initialisation
        Task.initialise();
        Ui ui = new Ui();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = isFresh ? new ArrayList<>() : loadTasks();
        ui.printWelcome();

        while (true) {
            ui.printNext();
            String input = scanner.nextLine().trim();
            ui.printSeperate();

            if (input.matches("^bye$")) {
                // User want to exit
                scanner.close();
                saveTasks(tasks);
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

        scanner.close();
        ui.printExit();
    }
}
