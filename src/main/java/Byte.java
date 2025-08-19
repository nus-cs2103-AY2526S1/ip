import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Byte {
    private static List<String> storage = new ArrayList<>();

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
        switch (command) {
        case "bye":
            return "\t" + line + "\t" + "Bye, hope to see you again soon!\n" + "\t" + line;
        case "list": {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < storage.size(); i++) {
                if (i > 0) {
                    output.append("\n\t");
                }
                output.append(i + 1).append(". ").append(storage.get(i));
            }
            return "\t" + line + "\t" + output.toString() + "\n" + "\t" + line;
        }
        default:
            storage.add(command);
            return "\t" + line + "\t" + "added: " + command + "\n" + "\t" + line;
        }
    }
}
