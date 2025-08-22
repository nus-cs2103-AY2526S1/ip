import java.util.Scanner;

public class TaskLynx {

    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void main(String[] args) {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        hello();
        scanForCommands();
        LynxFileManager.writeToFile(LynxStorage.unloadTasks());
        bye();
    }

    public static void hello() {
        LynxUI.printBox("Hello! I'm Tasklynx. \n" +
                "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
    }

    public static void bye() {
        scanner.close();
        LynxUI.printBox("Goodbye. I'll be here whenever you need to stay on track.");
    }

    public static void scanForCommands() {
        String input;
        System.out.println("How can I assist you with your tasks today? \nTasklynx is ready. Type your command:");

        while (true) {
            input = scanner.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("reload")) {
                    LynxFileManager.createFile();
                    LynxStorage.loadTasks(LynxFileManager.readFromFile());
                    LynxUI.line();
                } else if (input.equalsIgnoreCase("list")) {
                    LynxStorage.printTasks();
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

    }

}



