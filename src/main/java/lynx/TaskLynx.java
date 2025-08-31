package lynx;

import lynx.parser.LynxGeneral;
import lynx.parser.LynxScanner;
import lynx.parser.LynxTaskEditor;
import lynx.ui.LynxUI;

/**
 * Main class
 */
public class TaskLynx {

    /**
     * Runs the main program.
     */
    public static void run() {
        // Finds or creates the data file and load its contents
        LynxGeneral.reload();

        // Starts the process of scanning for commands
        LynxUI.hello();
        LynxTaskEditor.tasksToday();
        LynxScanner.scanForCommands();

        // Once finished, unload contents into data file
        LynxGeneral.save();
        LynxScanner.SCANNER.close();
        LynxTaskEditor.tasksFromToday();
        LynxUI.bye();
    }

    public static void main(String[] args) {
        run();
    }

}



