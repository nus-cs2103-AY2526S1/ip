import java.util.Scanner;

public class TaskLynx {

    public static void main(String[] args) {
        LynxFileManager.createFile();
        hello();
        scanForCommands();
        bye();
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
                    break;
                } else if (input.equalsIgnoreCase("reload")) {
                    LynxFileManager.createFile();
                } else if (input.equalsIgnoreCase("list")) {
                    LynxCommand.printListBox();
                } else if (input.startsWith("mark")) {
                    LynxCommand.markTask(input);
                } else if (input.startsWith("unmark")) {
                    LynxCommand.unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    LynxCommand.deleteTask(input);
                } else if (input.startsWith("todo")) {
                    LynxCommand.addTodo(input);
                } else if (input.startsWith("deadline")) {
                    LynxCommand.addDeadline(input);
                } else if (input.startsWith("event")) {
                    LynxCommand.addEvent(input);
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



