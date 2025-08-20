import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Aurora {

    public static final String INTRO = "Hello! I'm Aurora. What can I do for you?";
    public static final String OUTRO = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<Task>();

        speak(INTRO);
        loop(scanner, list);
        speak(OUTRO);
        scanner.close();
    }

    public static void speak(String content) {
        System.out.println("Aurora: " + content);
    }

    public static void loop(Scanner s, List<Task> list) {
        String input = s.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list(list);
            } else {
                add(input, list);
            }
            input = s.nextLine();
        }
    }

    public static void add(String content, List<Task> list) {
        list.add(new Task(content));
        System.out.println("Added: " + content);
    }

    public static void list(List<Task> list) {
        if (list.isEmpty()) {
            speak("Your list is empty.");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i));
        }
    }
}
