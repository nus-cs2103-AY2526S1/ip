package lynx;

import lynx.command.LynxScanner;
import lynx.storage.LynxFileManager;
import lynx.storage.LynxStorage;
import lynx.ui.LynxUI;

public class TaskLynx {

    public static void run() {
        // Finds or creates the data file and load its contents
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());

        // Starts the process of scanning for commands
        LynxUI.hello();
        LynxScanner.scanForCommands();

        // Once finished, unload contents into data file
        LynxFileManager.writeToFile(LynxStorage.unloadTasks());
        LynxScanner.SCANNER.close();
        LynxUI.bye();
    }

    public static void main(String[] args) {
        run();
    }

}



