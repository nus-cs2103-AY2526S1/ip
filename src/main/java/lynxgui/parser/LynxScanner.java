package lynxgui.parser;

import lynxgui.ui.LynxUI;

import objectclasses.exception.LynxException;

/**
 * Contains the central <code>Scanner</code> object, and the main program loop.
 */
public abstract class LynxScanner {

    public static String scanForCommandsGui(String input) {
        if (input.length() > 300) {
            return "Sorry, commands cannot exceed 200 characters in length.%n";
        }

        try {
            /*
            if (input.trim().equals("bye")) {
                break;
            } else
            */
            if (input.trim().equals("reload")) {
                return LynxGeneral.reloadGui();
            } else if (input.trim().equals("save")) {
                return LynxGeneral.saveGui();
            } else if (input.trim().equals("help")) {
                return LynxUI.printHelpGui();
            } else if (input.startsWith("list ")) {
                return LynxTaskEditor.listTasksGui(input);
            } else if (input.startsWith("mark ")) {
                return LynxTaskEditor.markTasksGui(input);
            } else if (input.startsWith("unmark ")) {
                return LynxTaskEditor.unmarkTasksGui(input);
            } else if (input.startsWith("delete ")) {
                return LynxTaskEditor.deleteTasksGui(input);
            } else if (input.startsWith("todo ")) {
                return LynxGeneral.addTodoGui(input);
            } else if (input.startsWith("deadline ")) {
                return LynxGeneral.addDeadlineGui(input);
            } else if (input.startsWith("event ")) {
                return LynxGeneral.addEventGui(input);
            } else if (!input.isEmpty()) {
                throw new LynxException("Sorry, I didn't understand that command. " +
                        "Please try again or type \"help\" to access the user guide.");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
        return "";
    }

}
