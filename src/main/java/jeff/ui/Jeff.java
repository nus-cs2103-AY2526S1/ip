package jeff.ui;

import java.util.ArrayList;

import jeff.command.Command;
import jeff.command.Parser;
import jeff.storage.JeffException;
import jeff.storage.Storage;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.task.Todo;

/**
 * Main application for Jeff the automated chatbot.
 */
public class Jeff {

    private static final String SPACER = "______________________________";

    /**
     * Entry point for the Jeff chatbot application. Initializes and runs the
     * system.
     *
     * @param args command line arguments (not used)
     * @throws JeffException if an error occurs during execution
     */
    public static void main(String[] args) throws JeffException {

        UserInterface ui = new UserInterface();
        ui.welcome();

        Storage storage = new Storage();
        ArrayList<String> storedLines = storage.load();
        TaskList tasks = new TaskList(parseStoredLines(storedLines));

        System.out.println("Loaded " + tasks.size() + " task(s) from storage.");

        boolean shouldBreak = false;

        while (!shouldBreak) {
            try {
                String input = ui.readCommand();

                Parser.Result result = Parser.parseCommand(input);
                Command cmd = result.command;
                String description = result.description; // this is the args

                if (cmd == Command.BYE) {
                    shouldBreak = true;
                }

                if (cmd == null) {
                    throw new JeffException("EXCUSEEE MEEEE. THIS IS A INVALID COMMAND??!!! Try again.");
                }

                shouldBreak = handleCommand(cmd, description, tasks, input);
                updateStorage(tasks, storage);

            } catch (JeffException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye! Hope to you see you again soon!");

    }

    /**
     * Reads stored lines into Task list.
     *
     * Expected formats per line: - T|0 or 1|<description>
     * - D|0 or 1|<description>|<yyyy-MM-dd HHmm>
     * - E|0 or 1|<description>|<datetime or original string>
     *
     * Lines that cannot be parsed are skipped.
     */
    private static ArrayList<Task> parseStoredLines(ArrayList<String> tasks) {
        ArrayList<Task> result = new ArrayList<>();
        if (tasks == null) {
            return result;
        }
        for (String task : tasks) {
            try {
                if (task == null || task.isBlank()) {
                    continue;
                }
                String[] parts = task.split("\\|", -1);
                if (parts.length < 3) {
                    continue;
                }
                String type = parts[0];
                String done = parts[1];
                String desc = parts[2];

                Task t;
                switch (type) {
                    case "T":
                        t = new Todo(desc);
                        break;
                    case "D":
                        if (parts.length < 4) {
                            continue;
                        }
                        t = new Deadline(desc, parts[3]);
                        break;
                    case "E":
                        if (parts.length < 4) {
                            continue;
                        }
                        t = new Event(desc, parts[3]);
                        break;
                    default:
                        continue;
                }

                if ("1".equals(done)) {
                    t.markAsDone();
                }
                result.add(t);
            } catch (Exception e) {
                System.out.println("Skipping invalid task: " + task);
            }
        }
        return result;
    }

    /**
     * Handles the execution of a parsed command.
     *
     * Processes different types of commands including LIST, BYE, MARK, UNMARK,
     * DELETE, TODO, DEADLINE, EVENT, and FIND. E
     *
     * @param command the command to execute
     * @param description the command arguments or description
     * @param tasks the task list to operate on
     * @param input the original user input string
     * @return true if the application should exit, false otherwise
     * @throws JeffException if an error occurs during command execution
     */
    private static boolean handleCommand(Command command, String description, TaskList tasks, String input) throws JeffException {
        switch (command) {
            case LIST:
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) != null) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                break;
            case BYE:
                return true;

            case MARK:

                int markIdx = Integer.parseInt(description);
                updateTask(tasks, true, markIdx - 1);
                break;

            case UNMARK:

                int unmarkIdx = Integer.parseInt(description);
                updateTask(tasks, false, unmarkIdx - 1);
                break;

            case DELETE:

                int idx = (Integer.parseInt(description) - 1); // This would be the index to be deleted.

                if (idx < 0 || idx >= tasks.size()) {
                    throw new JeffException("Invalid task number. Please try again.");
                }
                deleteTask(tasks, idx);
                break;

            case TODO:
                tasks.add(new Todo(description));
                added(input, tasks);
                break;

            case DEADLINE:
                String[] parts;
                if (description.contains(" by ")) {
                    parts = description.split(" by ", 2);
                } else if (description.contains("/by")) {
                    parts = description.split("/by", 2);
                } else {
                    parts = description.split(" ", 2);
                }
                tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                added(input, tasks);
                break;

            case EVENT:

                if (description.contains("/at")) {
                    parts = description.split("/at", 2);
                } else {
                    parts = description.split(" ", 2);
                }
                tasks.add(new Event(parts[0].trim(), parts[1].trim()));
                added(input, tasks);
                break;

            case FIND:
                String keyword = description;
                findTasks(tasks, keyword);
                break;

        }
        return false;
    }

    /**
     * Updates the completion status of a task at the specified index and
     * provides user print statement.
     *
     * @param tasks task list containing the task to update
     * @param isDone true to mark as done, false to mark as undone
     * @param idx idx of the task to update
     */
    private static void updateTask(TaskList tasks, boolean isDone, int idx) {

        if (isDone) {
            tasks.get(idx).markAsDone();
            System.out.println("Task marked as done!");
            System.out.println(tasks.get(idx));
            System.out.println(SPACER);
        } else {
            tasks.get(idx).undo();
            System.out.println("Task marked as undone!");
            System.out.println(tasks.get(idx));
            System.out.println(SPACER);
        }
    }

    /**
     * Deletes a task from the task list at the specified index and provides
     * user print statement.
     *
     * @param tasks task list to remove the task from
     * @param idx idx of the task to delete
     */
    private static void deleteTask(TaskList tasks, int idx) {
        tasks.remove(idx);
        System.out.println("Task has been deleted.");
        System.out.println("You now have " + tasks.size() + " tasks in the list.");
        System.out.println(SPACER);
    }

    /**
     * Searches for and displays tasks containing the specified keyword and
     * provides user print statement.
     *
     * @param tasks task list to search through
     * @param keyword the keyword to search for in task descriptions
     */
    private static void findTasks(TaskList tasks, String keyword) {

        System.out.println(SPACER);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {

                System.out.println((i + 1) + ". " + task);
            }
        }
        System.out.println(SPACER);

    }

    /**
     * Formats a task for storage persistence and provides user print statement.
     *
     * @param t task to format
     * @return string representation of the task for storage
     */
    private static String formatTask(Task t) {

        String done = t.isDone() ? "1" : "0";
        String type = t.getType();
        String description = t.getDescription();

        String formatted = String.format("%s|%s|%s", type, done, description);

        if ("D".equals(type)) {
            formatted += "|" + ((Deadline) t).getForStorage(); // this is ok beacuse type is deadline, safe typecast
        } else if ("E".equals(type)) {
            formatted += "|" + ((Event) t).getForStorage(); // this is ok beacuse type is event, safe typecast
        }

        return formatted;
    }

    /**
     * Displays confirmation message when a task is successfully added and
     * provides user print statement.
     *
     * @param input original user input that created the task
     * @param tasks task list that the task was added to
     */
    private static void added(String input, TaskList tasks) {

        System.out.println(SPACER);
        System.out.println("Task has been added: " + input);
        System.out.println("You now have " + tasks.size() + " tasks in the list.");
        System.out.println(SPACER);

    }

    /**
     * Converts all tasks in the task list to their storage format and saves
     * them provides user print statement.
     *
     * @param tasks task list to save
     * @param storage storage instance to save tasks to
     */
    private static void updateStorage(TaskList tasks, Storage storage) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            lines.add(formatTask(task));
        }
        storage.save(lines);
    }

}
