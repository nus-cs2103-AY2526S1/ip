package lynx.parser;

import java.util.Scanner;

import lynx.storage.LynxFileManager;
import lynx.storage.LynxTaskList;
import lynx.ui.LynxUI;
import objectclasses.exception.LynxException;

/**
 * Contains all methods related to the main program flow.
 * Manages a <code>LynxFileManager</code> alongside a <code>LynxTaskList</code> and a <code>LynxTaskEditor</code>.
 */
public class LynxControl {

    private final Scanner scanner = new Scanner(System.in);
    private final LynxFileManager fileManager;
    private final LynxTaskList taskList;
    private final LynxTaskEditor taskEditor;

    /**
     * Sets up a <code>LynxFileManager</code> using the specified file path.
     * Creates a <code>LynxTaskList</code> and <code>LynxTaskEditor</code>.
     *
     * @param filePath File path relative to the program root.
     */
    public LynxControl(String filePath) {
        fileManager = new LynxFileManager(filePath);
        taskList = new LynxTaskList();
        taskEditor = new LynxTaskEditor(taskList);
    }

    public String load() throws LynxException {
        return LynxGeneral.reload(fileManager, taskList);
    }

    public String save() throws LynxException {
        return LynxGeneral.save(fileManager, taskList);
    }

    public String tasksToday() {
        return taskEditor.tasksToday();
    }

    public String tasksFromToday() {
        return taskEditor.tasksFromToday();
    }

    /**
     * Parses and executes an input command.
     *
     * @param input Input command.
     * @return Response from executing the command.
     */
    public String scanForCommandsGui(String input) {
        input = input.trim();
        if (input.length() > 300) {
            return "Sorry, commands cannot exceed 300 characters in length.%n";
        }

        try {
            if (input.equals("reload")) {
                return LynxGeneral.reload(fileManager, taskList);
            } else if (input.equals("save")) {
                return LynxGeneral.save(fileManager, taskList);
            } else if (input.equals("help")) {
                return LynxUI.printHelpGui();
            } else if (input.startsWith("list ")) {
                return taskEditor.listTasks(input);
            } else if (input.startsWith("mark ")) {
                return taskEditor.markTasks(input);
            } else if (input.startsWith("unmark ")) {
                return taskEditor.unmarkTasks(input);
            } else if (input.startsWith("delete ")) {
                return taskEditor.deleteTasks(input);
            } else if (input.startsWith("todo ")) {
                return LynxGeneral.addTodo(input, taskList);
            } else if (input.startsWith("deadline ")) {
                return LynxGeneral.addDeadline(input, taskList);
            } else if (input.startsWith("event ")) {
                return LynxGeneral.addEvent(input, taskList);
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
    public void scanForCommands() {
        String input;
        while (true) {
            input = scanner.nextLine().trim();

            if (input.trim().equals("bye!")) {
                scanner.close();
                break;
            }

            if (input.trim().equals("bye")) {
                try {
                    LynxUI.printBox(LynxGeneral.save(fileManager, taskList));
                    scanner.close();
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
