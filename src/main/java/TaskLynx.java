import java.util.Scanner;

public class TaskLynx {

    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void run() {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        LynxUI.hello();
        scanForCommands();
        LynxFileManager.writeToFile(LynxStorage.unloadTasks());
        scanner.close();
        LynxUI.bye();
    }

    public static void scanForCommands() {
        String input;
        System.out.println("How can I assist you with your tasks today? \nTasklynx is ready. Type your command:");

        while (true) {
            input = scanner.nextLine().trim();

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("reload")) {
                    LynxFileManager.createFile();
                    LynxStorage.loadTasks(LynxFileManager.readFromFile());
                    LynxUI.line();
                } else if (input.startsWith("list")) {
                    LynxCommand.listTasks(input);
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

    public static void main(String[] args) {
        run();
    }

}



