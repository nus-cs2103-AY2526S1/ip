package lynx.parser;

import java.util.Scanner;

import lynx.ui.LynxUI;
import objectclasses.exception.LynxException;

/**
 * Contains the main parser method.
 */
public abstract class LynxScanner {

    // Scanner object shared within the program
    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Parses and executes an input command.
     *
     * @param input Input command.
     * @return Response from executing the command.
     */
    public static String scanForCommandsGui(String input) {
        if (input.length() > 300) {
            return "Sorry, commands cannot exceed 300 characters in length.%n";
        }

        try {
            if (input.trim().equals("reload")) {
                return LynxGeneral.reload();
            } else if (input.trim().equals("save")) {
                return LynxGeneral.save();
            } else if (input.trim().equals("help")) {
                return LynxUI.printHelpGui();
            } else if (input.startsWith("list ")) {
                return LynxTaskEditor.listTasks(input);
            } else if (input.startsWith("mark ")) {
                return LynxTaskEditor.markTasks(input);
            } else if (input.startsWith("unmark ")) {
                return LynxTaskEditor.unmarkTasks(input);
            } else if (input.startsWith("delete ")) {
                return LynxTaskEditor.deleteTasks(input);
            } else if (input.startsWith("todo ")) {
                return LynxGeneral.addTodo(input);
            } else if (input.startsWith("deadline ")) {
                return LynxGeneral.addDeadline(input);
            } else if (input.startsWith("event ")) {
                return LynxGeneral.addEvent(input);
            } else if (!input.isEmpty()) {
                throw new LynxException("Sorry, I didn't understand that command. "
                        + "Please try again or type \"help\" to access the user guide.");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
        return "";
    }

    /**
     * Scans, parses and executes input commands.
     */
    public static void scanForCommands() {
        String input;
        while (true) {
            input = SCANNER.nextLine().trim();

            if (input.trim().equals("bye!")) {
                break;
            }

            if (input.trim().equals("bye")) {
                try {
                    LynxUI.printBox(LynxGeneral.save());
                    break;
                } catch (LynxException e) {
                    LynxUI.printBox(e.getMessage() + "\nUse \"save\" to resave or \"bye!\" to force quit.");
                    continue;
                }
            }

            LynxUI.printBox(scanForCommandsGui(input));
        }
    }

}
