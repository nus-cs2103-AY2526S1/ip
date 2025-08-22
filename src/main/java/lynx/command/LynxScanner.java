package lynx.command;

import lynx.exception.LynxException;
import lynx.ui.LynxUI;

import java.util.Scanner;

public class LynxScanner {
    // Scanner object shared within the program
    public static final Scanner SCANNER = new Scanner(System.in);

    // Continuously scan and identify the type of command to run
    public static void scanForCommands() {
        String input;
        System.out.println("How can I assist you with your tasks today? \nTasklynx is ready. Type your command:");

        while (true) {
            input = SCANNER.nextLine().trim();

            try {
                if (input.trim().equals("bye")) {
                    break;
                } else if (input.trim().equals("reload")) {
                    LynxCommand.reload();
                } else if (input.startsWith("list")) {
                    LynxCommand.listTasks(input);
                } else if (input.startsWith("mark ")) {
                    LynxCommand.markTask(input);
                } else if (input.startsWith("unmark ")) {
                    LynxCommand.unmarkTask(input);
                } else if (input.startsWith("delete ")) {
                    LynxCommand.deleteTask(input);
                } else if (input.startsWith("todo ")) {
                    LynxCommand.addTodo(input);
                } else if (input.startsWith("deadline ")) {
                    LynxCommand.addDeadline(input);
                } else if (input.startsWith("event ")) {
                    LynxCommand.addEvent(input);
                } else if (!input.isEmpty()) {
                    throw new LynxException("Sorry, I didn't understand that command. " +
                            "Please try again or type 'list' to see available tasks.");
                }
            } catch (LynxException e) {
                LynxUI.printBox(e.getMessage());
            }
        }
    }
}
