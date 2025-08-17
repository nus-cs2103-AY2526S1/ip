import java.util.Scanner;
import java.util.ArrayList;

public class TaskLynx {

    private static final String NAME = "TaskLynx";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<String> COMMANDS = new ArrayList<>(100);

    public static void main(String[] args) {
        hello();
        scanForCommands();
        bye();
    }

    public static void hello() {
        System.out.println(LINE);
        System.out.println("Hello! I'm TaskLynx.");
        System.out.println("Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
        System.out.println(LINE);
        System.out.println("How can I assist you with your tasks today?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println("Goodbye. I’ll be here whenever you need to stay on track.");
        System.out.println(LINE);
    }

    public static void scanForCommands() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Tasklynx is ready. Type your command:");

        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                printBox("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printListBox(COMMANDS);
            } else if (!input.isEmpty()) {
                COMMANDS.add(input);
                printBox("added: " + input);
            }
        }

        scanner.close();
    }

    private static void printBox(String message) {
        System.out.println(LINE);
        System.out.println("     " + message);
        System.out.println(LINE);
    }

    private static void printListBox(ArrayList<String> commands) {
        System.out.println(LINE);
        System.out.println("List: ");
        if (commands.isEmpty()) {
            System.out.println("     (No tasks yet)");
        }
        for (int i = 0; i < commands.size(); i++) {
            System.out.println("     " + (i+1) + ". " + commands.get(i));
        }
        System.out.println(LINE);
    }

}

