import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

public class Aurora {

    public static final String INTRO = "Hello! I'm Aurora. What can I do for you?";
    public static final String OUTRO = "Bye. Hope to see you again soon!";
    public static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$");

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
            Matcher matcher = MARK.matcher(input);
            if (input.equals("list")) {
                list(list);
            } else if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                mark(list, x);
            }else {
                add(input, list);
            }
            input = s.nextLine();
        }
    }

    public static void add(String content, List<Task> list) {
        list.add(new Task(content));
        speak("Added " + content + " as a task into your list.");
    }

    public static void list(List<Task> list) {
        if (list.isEmpty()) {
            speak("Your list is empty.");
            return;
        }

        speak("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i));
        }
    }

    public static void mark(List<Task> list, int x) {
        if (x == 0 || x > list.size()) {
            speak("There is no task numbered " + x + ".");
            return;
        }
        Task task = list.get(x - 1);
        task.complete();
        speak("Nice! I've marked this task as completed.");
        System.out.println(task);
    }
}
