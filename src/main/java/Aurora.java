import command.Command;
import command.CommandReader;
import task.Task;
import task.TaskReader;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * Aurora is a chatbot that manages tasks.
 */
public class Aurora {

    private static final String INTRO = "Hello! I'm Aurora. What can I do for you?";
    private static final String OUTRO = "Bye. Hope to see you again soon!";
    private static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        speak(INTRO);
        loop(scanner, list);
        speak(OUTRO);
        scanner.close();
    }

    private static void speak(String content) {
        System.out.println("Aurora: " + content);
    }

    private static void loop(Scanner s, List<Task> list) {
        String input = s.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            Command command = CommandReader.read(input);
            speak(command.execute(list));
            input = s.nextLine();
        }
    }
}
