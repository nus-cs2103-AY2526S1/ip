package lynx.parser;

import lynx.ui.LynxUI;

import objectclasses.exception.LynxException;

import java.util.Scanner;

/**
 * Contains the central <code>Scanner</code> object, and the main program loop.
 */
public abstract class LynxScanner {

    // Scanner object shared within the program
    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Interprets and responds to user input until the "bye" command is called.
     */
    public static void scanForCommands() {
        String input;
        System.out.println("How can I assist you with your tasks today? " +
                "\nTasklynx is ready. Type your command:");

        while (true) {
            input = SCANNER.nextLine().trim();
            if (input.length() > 300) {
                LynxUI.printBox("Sorry, commands cannot exceed 200 characters in length.");
                continue;
            }

            try {
                if (input.trim().equals("bye")) {
                    break;
                } else if (input.trim().equals("reload")) {
                    LynxGeneral.reload();
                } else if (input.trim().equals("save")) {
                    LynxGeneral.save();
                } else if (input.trim().equals("help")) {
                    LynxUI.printHelp();
                } else if (input.startsWith("list ")) {
                    LynxTaskEditor.listTasks(input);
                } else if (input.startsWith("mark ")) {
                    LynxTaskEditor.markTasks(input);
                } else if (input.startsWith("unmark ")) {
                    LynxTaskEditor.unmarkTasks(input);
                } else if (input.startsWith("delete ")) {
                    LynxTaskEditor.deleteTasks(input);
                } else if (input.startsWith("todo ")) {
                    LynxGeneral.addTodo(input);
                } else if (input.startsWith("deadline ")) {
                    LynxGeneral.addDeadline(input);
                } else if (input.startsWith("event ")) {
                    LynxGeneral.addEvent(input);
                } else if (!input.isEmpty()) {
                    throw new LynxException("Sorry, I didn't understand that command. " +
                            "Please try again or type \"help\" to access the user guide.");
                }
            } catch (Exception e) {
                LynxUI.printBox(e.getMessage());
            }
        }
    }

}
