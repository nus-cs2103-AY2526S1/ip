import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinhGPT {
    /**
     * Print the divider between each pair of input and response.
     */
    private static void printDivider() {
        System.out.println("------------------------------------------");
    }

    /**
     * Print empty lines.
     * 
     * @param size Number of empty lines to be printed.
     */
    private static void printPadding(int size) {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += "\n";
        }
        System.out.print(str);
    }

    /**
     * Print the message when user first enter the program.
     */
    private static void printStartupMessage() {
        String logo = """
                  ███    ███ ██ ███    ██ ██   ██  ██████  ██████  ████████
                  ████  ████ ██ ████   ██ ██   ██ ██       ██   ██    ██
                  ██ ████ ██ ██ ██ ██  ██ ███████ ██   ███ ██████     ██
                  ██  ██  ██ ██ ██  ██ ██ ██   ██ ██    ██ ██         ██
                  ██      ██ ██ ██   ████ ██   ██  ██████  ██         ██
                """;
        printPadding(2);
        System.out.print(logo);
        printPadding(2);
        System.out.print("Hello! I'm MinhGPT.\nWhat can I do for you?");
        printPadding(2);
    }

    /**
     * Print the message when user say 'bye'.
     */
    private static void printExitMessage() {
        System.out.println("(╥﹏╥) Bye. Hope to see you again soon!");
    }

    /**
     * Print the error message when the task with that index does not exist for mark / unmark and
     * delete operation.
     */
    private static void printIndexError() {
        System.out.println(
                "<( ⸝⸝•̀ - •́⸝⸝)> There is no tasks with that index. You could have caused an IndexOutOfBoundsException.");
    }

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
     * Keep polling inputs from users until they say 'bye'.
     *
     * @param initialTasks Initial list of tasks.
     */
    private static void program(ArrayList<Task> initialTasks) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = initialTasks;

        while (true) {
            printDivider();
            System.out.print("Your input: ");
            String input = scanner.nextLine().trim();
            printPadding(1);

            if (input.matches("^bye$")) {
                // User want to exit
                scanner.close();
                saveTasks(tasks);
                break;
            } else if (input.matches("^list$")) {
                // List all tasks
                System.out.println(
                        String.format("(˶˃ ᵕ ˂˶) Here are the list of tasks. You have %d in total.",
                                tasks.size()));
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
                }
            } else if (input.matches("^mark \\d+$")) {
                // Mark a task as done
                int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    printIndexError();
                    continue;
                }
                tasks.get(index).markAsDone();
                System.out.println("(˵˃ ᗜ ˂˵) Congrats on finishing the task.");
                System.out.println(tasks.get(index));
            } else if (input.matches("^unmark \\d+$")) {
                // Mark a task as undone
                int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    printIndexError();
                    continue;
                }
                tasks.get(index).markAsUndone();
                System.out.println("(¬`‸´¬) Huh? Why did you lie?");
                System.out.println(tasks.get(index));
            } else if (input.matches("^delete \\d+$")) {
                // Delete a task
                int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    printIndexError();
                }
                System.out.println(String.format("(˶ᵔ ᵕ ᵔ˶) Removed: %s", tasks.get(index)));
                tasks.remove(index);
            } else {
                // All other inputs are considered as trying to add a task
                try {
                    tasks.add(Task.parseTask(input));
                    System.out.println(
                            String.format("(˶ᵔ ᵕ ᵔ˶) Added: %s", tasks.get(tasks.size() - 1)));
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Main entry-point for the program.
     */
    public static void main(String[] args) {
        // Get all flags
        boolean isFresh = args.length > 0 && args[0].equals("--fresh");

        // Initialisation
        Task.initialise();
        ArrayList<Task> initialTasks = isFresh ? new ArrayList<>() : loadTasks();

        printStartupMessage();

        program(initialTasks);

        printExitMessage();
    }
}
