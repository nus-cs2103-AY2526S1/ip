package lynx;

import lynx.parser.LynxCommandManager;
import lynx.parser.LynxScanner;
import lynx.storage.LynxFileManager;
import lynx.storage.LynxStorage;
import lynx.ui.LynxUI;

public class TaskLynx {

    /**
     * Runs the main program.
     */
    public static void run() {
        // Finds or creates the data file and load its contents
        LynxCommandManager.reload();

        // Starts the process of scanning for commands
        LynxUI.hello();
        LynxScanner.scanForCommands();

        // Once finished, unload contents into data file
        LynxCommandManager.save();
        LynxScanner.SCANNER.close();
        LynxUI.bye();
    }

    public static void main(String[] args) {
        run();
    }

}



