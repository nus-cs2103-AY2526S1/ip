import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
            System.out.println(reply(command, line));
        }
        scanner.close();
    }


    public static String reply(String command, String line) {
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
                if (command.trim().isEmpty()) {
                    return "\t" + line + "\t" + "\n" + "\t" + line;
                }
                storage.add(new Task(command));
                return "\t" + line + "\t" + "added: " + command + "\n" + "\t" + line;
        }
    }

}
