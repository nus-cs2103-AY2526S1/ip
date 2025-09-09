package lynx;

import lynx.parser.LynxControl;
import lynx.ui.LynxUI;
import objectclasses.exception.LynxException;

/**
 * Main class for the CLI program
 */
public class TaskLynx {

    private static final LynxControl LYNX_CONTROL = new LynxControl("./data/log.txt");

    /**
     * Runs the main program.
     */
    public static void run() {
        try {
            LynxUI.printBox(LYNX_CONTROL.load());
        } catch (LynxException e) {
            LynxUI.printBox(e.getMessage());
        }
        LynxUI.printLineAfter(LynxUI.hello());
        LynxUI.printLineAfter(LYNX_CONTROL.tasksToday());

        LYNX_CONTROL.scanForCommands();

        LynxUI.printLineAfter(LYNX_CONTROL.tasksFromToday());
        LynxUI.printLineAfter(LynxUI.bye());
    }

    public static void main(String[] args) {
        run();
    }

}



