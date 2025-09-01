package lynx;

import lynx.parser.LynxGeneral;
import lynx.parser.LynxScanner;
import lynx.parser.LynxTaskEditor;
import lynx.ui.LynxUI;
import objectclasses.exception.LynxException;

/**
 * Main class for the CLI program
 */
public class TaskLynx {

    /**
     * Runs the main program.
     */
    public static void run() {
        // Finds or creates the data file and load its contents
        try {
            LynxUI.printBox(LynxGeneral.reload());
        } catch (LynxException e) {
            LynxUI.printBox(e.getMessage());
        }

        // Starts the process of scanning for commands
        LynxUI.printLineAfter(LynxUI.hello());
        LynxUI.printLineAfter(LynxTaskEditor.tasksToday());
        LynxScanner.scanForCommands();

        // Once finished, unload contents into data file
        LynxScanner.SCANNER.close();
        LynxUI.printLineAfter(LynxTaskEditor.tasksFromToday());
        LynxUI.printLineAfter(LynxUI.bye());
    }

    public static void main(String[] args) {
        run();
    }

}



