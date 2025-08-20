import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Entry point for the Byte chatbot
 */
public class Byte {
    private static final List<Task> storage = new ArrayList<>();

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String greetMessage = "Hello! I'm Byte.\nWhat can I do for you?\n";

        System.out.println(line + greetMessage + line);

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
     * Adds the given task to storage and returns a reply.
     *
     * @param task Task to add
     * @param line Horizontal rule string used for formatting
     * @return Reply after adding the task
     */
    private static String addTaskAndReply(Task task, String line) {
        storage.add(task);
        StringBuilder output = new StringBuilder();
        output.append("Got it, I've added this task:\n\t  " + task + "\n\tNow you have " + storage.size() + " tasks in the list.");
        return "\t" + line + "\t" + output.toString() + "\n" + "\t" + line;
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
                if (currentSegment.length() > 0) {
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
     * @param line Line (for format)
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
                String[] segs = parseSegments(options);
                if (segs[0] == null || segs[0].trim().equals("todo")) {
                    throw new ByteException("Note that the description of a todo cannot be empty");
                }
                Task task = new Todo(segs[0]);
                return addTaskAndReply(task, line);
            }
            case "deadline": {
                String[] segs = parseSegments(command.split(" "));
                if (segs[0] == null || segs[0].trim().equals("deadline")) {
                    throw new ByteException("The description of a deadline cant be empty");
                }
                if (segs[1] == null || segs[1].trim().isEmpty()) {
                    throw new ByteException("The /by time of a deadline cant be empty");
                }
                Task task = new Deadline(segs[0], segs[1]);
                return addTaskAndReply(task, line);
            }
            case "event": {
                String[] segs = parseSegments(command.split(" "));
                if (segs[0] == null || segs[0].trim().equals("event")) {
                    throw new ByteException("The description of an event cant be empty");
                }
                if (segs[1] == null || segs[1].trim().isEmpty()) {
                    throw new ByteException("The /from time of an event cant be empty");
                }
                if (segs[2] == null || segs[2].trim().isEmpty()) {
                    throw new ByteException("The /to time of an event cant be empty");
                }
                Task task = new Event(segs[0], segs[1], segs[2]);
                return addTaskAndReply(task, line);
            }
            case "mark": {
                int index = Integer.parseInt(parts[1]);
                Task task = storage.get(index - 1);
                task.mark();
                return "\t" + line + "\t" + "Nice! I've marked this task as done:\n\t  " + task.toString() + "\n" + "\t" + line;
            }
            case "unmark": {
                int index = Integer.parseInt(parts[1]);
                Task task = storage.get(index - 1);
                task.unmark();
                return "\t" + line + "\t" + "OK, I've marked this task as not done yet:\n\t  " + task.toString() + "\n" + "\t" + line;
            }
            default:
                throw new ByteException("I dont know what that means!");
        }
    }

}
