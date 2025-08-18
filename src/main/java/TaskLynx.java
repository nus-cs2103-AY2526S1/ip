import java.util.Scanner;

public class TaskLynx {

    public static void main(String[] args) {
        hello();
        scanForCommands();
    }

    public static void hello() {
        LynxUI.line();
        System.out.println("Hello! I'm Tasklynx.");
        System.out.println("Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
    }

    public static void bye() {
        LynxUI.printBox("Goodbye. I'll be here whenever you need to stay on track.");
    }

    public static void scanForCommands() {
        Scanner scanner = new Scanner(System.in);
        String input;
        LynxUI.printBox("How can I assist you with your tasks today? \nTasklynx is ready. Type your command:");

        while (true) {
            input = scanner.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    bye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    TaskManager.printListBox();
                } else if (input.startsWith("mark")) {
                    TaskManager.markTask(input);
                } else if (input.startsWith("unmark")) {
                    TaskManager.unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    TaskManager.deleteTask(input);
                } else if (input.startsWith("todo")) {
                    TaskManager.addTodo(input);
                } else if (input.startsWith("deadline")) {
                    TaskManager.addDeadline(input);
                } else if (input.startsWith("event")) {
                    TaskManager.addEvent(input);
                } else if (!input.isEmpty()) {
                    throw new LynxException("Sorry, I didn't understand that command. Please try again or type 'list' to see available tasks.");
                }
            } catch (LynxException e) {
                LynxUI.printBox(e.getMessage());
            }
        }

        scanner.close();
    }

}

