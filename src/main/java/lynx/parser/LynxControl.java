package lynx.parser;

import java.util.Scanner;

import lynx.storage.LynxFileManager;
import lynx.storage.LynxTaskList;
import lynx.ui.LynxUI;
import objectclasses.exception.CommandFormatException;
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
     * @throws LynxException If command is invalid.
     */
    public String scanForCommandsGui(String input) throws LynxException {
        input = input.trim();
        if (input.length() > 300) {
            return "Sorry, I'm only partially literate. Keep your commands under 300 characters.";
        }

        try {
            if (input.equals("reload")) {
                return LynxGeneral.reload(fileManager, taskList);
            }

            if (input.equals("save")) {
                return LynxGeneral.save(fileManager, taskList);
            }

            if (input.equals("help")) {
                return LynxUI.printHelpGui();
            }

            if (input.startsWith("list ")) {
                return taskEditor.listTasks(input);
            }

            if (input.startsWith("mark ")) {
                return taskEditor.markTasks(input);
            }

            if (input.startsWith("unmark ")) {
                return taskEditor.unmarkTasks(input);
            }

            if (input.startsWith("delete ")) {
                return taskEditor.deleteTasks(input);
            }

            if (input.startsWith("todo ")) {
                return LynxGeneral.addTodo(input, taskList);
            }

            if (input.startsWith("deadline ")) {
                return LynxGeneral.addDeadline(input, taskList);
            }

            if (input.startsWith("event ")) {
                return LynxGeneral.addEvent(input, taskList);
            }
        } catch (Exception e) {
            return (e.getMessage());
        }

        if (input.toLowerCase().contains("lynx")) {
            throw LynxException.secret();
        } else if (!input.isEmpty()) {
            throw CommandFormatException.invalidCommand();
        } else {
            return "";
        }
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
                    LynxUI.printBox(e.getMessage() + "\nUse \"bye!\" to put me out of this misery.");
                    continue;
                }
            }

            try {
                LynxUI.printBox(scanForCommandsGui(input));
            } catch (LynxException e) {
                LynxUI.printBox(e.getMessage());
            }
        }
    }

}
