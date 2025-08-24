package lynx.parser;

import lynx.exception.LynxException;
import lynx.ui.LynxUI;

import java.util.Scanner;

/**
 * Class containing the central <code>Scanner</code> object, and the main program loop.
 */
public abstract class LynxScanner {

    // Scanner object shared within the program
    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Continuously interpret and respond to user input until the "bye" command is called.
     */
    public static void scanForCommands() {
        String input;
        System.out.println("How can I assist you with your tasks today? " +
                "\nTasklynx is ready. Type your command:");

        while (true) {
            input = SCANNER.nextLine().trim();

            try {
                if (input.trim().equals("bye")) {
                    break;
                } else if (input.trim().equals("reload")) {
                    LynxCommandManager.reload();
                } else if (input.startsWith("list")) {
                    LynxCommandManager.listTasks(input);
                } else if (input.startsWith("mark")) {
                    LynxCommandManager.markTasks(input);
                } else if (input.startsWith("unmark")) {
                    LynxCommandManager.unmarkTasks(input);
                } else if (input.startsWith("delete")) {
                    LynxCommandManager.deleteTasks(input);
                } else if (input.startsWith("todo ")) {
                    LynxCommandManager.addTodo(input);
                } else if (input.startsWith("deadline ")) {
                    LynxCommandManager.addDeadline(input);
                } else if (input.startsWith("event ")) {
                    LynxCommandManager.addEvent(input);
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
