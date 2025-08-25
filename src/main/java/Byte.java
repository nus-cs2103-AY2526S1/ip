import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Entry point for the Byte chatbot
 */
public class Byte {
    private static final List<Task> storage = new ArrayList<>();
    private static final Storage fileStorage = new Storage();

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String greetMessage = "Hello! I'm Byte.\nWhat can I do for you?\n";

        System.out.println(line + greetMessage + line);

        loadTasksFromFile(line);

        Scanner scanner = new Scanner(System.in);

        String command = "";
        while (!command.equals("bye")) {
            command = scanner.nextLine();
            try {
                System.out.println(reply(command, line));
            } catch (ByteException e) {
                System.out.println("\t" + line + "\t" + e.getMessage() + "\n" + "\t" + line);
            }
        }
        scanner.close();
    }

    /**
     * Loads tasks from file and adds them to storage.
     *
     * @param line Horizontal rule string used for formatting
     */
    private static void loadTasksFromFile(String line) {
        try {
            List<Task> loadedTasks = fileStorage.loadTasks();
            storage.addAll(loadedTasks);
            if (!loadedTasks.isEmpty()) {
                System.out.println("\t" + line + "\t" + "Loaded " + loadedTasks.size() + " tasks from file.\n" + "\t" + line);
            }
        } catch (IOException e) {
            System.out.println("\t" + line + "\t" + "Warning: Could not load tasks from file: " + e.getMessage() + "\n" + "\t" + line);
        }
    }

    /**
     * Saves the current task list to file.
     */
    private static void saveTasks() {
        try {
            fileStorage.saveTasks(storage);
        } catch (IOException e) {
            System.err.println("Warning: Could not save tasks to file: " + e.getMessage());
        }
    }

    /**
     * Adds the given task to storage and returns a reply.
     *
     * @param task Task to add
     * @param line Horizontal rule string used for formatting
     * @return Reply after adding the task
     */
    private static String addTaskAndReply(Task task, String line) {
        storage.add(task);
        saveTasks(); // Save after adding task
        return "\t" + line + "\t" + "Got it, I've added this task:\n\t  " + task + "\n\tNow you have " + storage.size() + " tasks in the list." + "\n" + "\t" + line;
    }

    /**
     * Parses commands into three segments
     *
     * @param options Command split by spaces
     * @return Array of 3 strings
     */
    private static String[] parseSegments(String[] options) {
        String[] segments = new String[3];
        StringBuilder currentSegment = new StringBuilder();
        int segmentIndex = 0;

        for (String option : options) {
            if (option.startsWith("/")) {
                segments[segmentIndex] = currentSegment.toString().trim();
                segmentIndex++;
                currentSegment.setLength(0);
            } else {
                if (!currentSegment.isEmpty()) {
                    currentSegment.append(" ");
                }
                currentSegment.append(option);
            }
        }

        if (segmentIndex < segments.length) {
            segments[segmentIndex] = currentSegment.toString().trim();
        }

        return segments;
    }

    /**
     * Processes a raw command and returns the formatted reply string.
     *
     * @param command Command entered
     * @param line    Line (for format)
     * @return Formatted reply
     */
    public static String reply(String command, String line) throws ByteException {
        String[] parts = command.split(" ", 2);
        String keyword = parts[0];
        switch (keyword) {
        case "bye":
            return "\t" + line + "\t" + "Bye, hope to see you again soon!\n" + "\t" + line;
        case "list": {
            StringBuilder output = new StringBuilder();
            output.append("Here are the tasks in your list:");
            for (int i = 0; i < storage.size(); i++) {
                output.append("\n\t").append(i + 1).append(".").append(storage.get(i).toString());
            }
            return "\t" + line + "\t" + output.toString() + "\n" + "\t" + line;
        }
        case "todo": {
            String[] options = command.split(" ");
            String[] segment = parseSegments(options);
            if (segment[0] == null || segment[0].trim().equals("todo")) {
                throw new ByteException("Note that the description of a todo cannot be empty");
            }
            Task task = new Todo(segment[0]);
            return addTaskAndReply(task, line);
        }
        case "deadline": {
            String[] segment = parseSegments(command.split(" "));
            if (segment[0] == null || segment[0].trim().equals("deadline")) {
                throw new ByteException("The description of a deadline cant be empty");
            }
            if (segment[1] == null || segment[1].trim().isEmpty()) {
                throw new ByteException("The /by time of a deadline cant be empty");
            }
            Task task = new Deadline(segment[0], segment[1]);
            return addTaskAndReply(task, line);
        }
        case "event": {
            String[] segment = parseSegments(command.split(" "));
            if (segment[0] == null || segment[0].trim().equals("event")) {
                throw new ByteException("The description of an event cant be empty");
            }
            if (segment[1] == null || segment[1].trim().isEmpty()) {
                throw new ByteException("The /from time of an event cant be empty");
            }
            if (segment[2] == null || segment[2].trim().isEmpty()) {
                throw new ByteException("The /to time of an event cant be empty");
            }
            Task task = new Event(segment[0], segment[1], segment[2]);
            return addTaskAndReply(task, line);
        }
        case "mark": {
            int index = Integer.parseInt(parts[1]);
            Task task = storage.get(index - 1);
            task.mark();
            saveTasks();
            return "\t" + line + "\t" + "Nice! I've marked this task as done:\n\t  " + task.toString() + "\n" + "\t" + line;
        }
        case "unmark": {
            int index = Integer.parseInt(parts[1]);
            Task task = storage.get(index - 1);
            task.unmark();
            saveTasks(); // Save after unmarking task
            return "\t" + line + "\t" + "OK, I've marked this task as not done yet:\n\t  " + task.toString() + "\n" + "\t" + line;
        }
        case "delete": {
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ByteException("Specify the task number to delete");
            }

            int index = Integer.parseInt(parts[1].trim());

            if (index < 1 || index > storage.size()) {
                throw new ByteException("The number to delete is invalid");
            }
            Task removed = storage.remove(index - 1);
            saveTasks(); // Save after deleting task
            return "\t" + line + "\t" + "I have removed this task:\n\t  " + removed.toString() + "\n\tNow you have " + storage.size() + " tasks in the list." + "\n" + "\t" + line;
        }
        default:
            throw new ByteException("I dont know what that means!");
        }
    }

}
