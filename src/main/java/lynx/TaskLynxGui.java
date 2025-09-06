package lynx;

import java.util.ArrayList;
import java.util.List;

import lynx.parser.LynxControl;
import objectclasses.exception.LynxException;

/**
 * Main class for the GUI application.
 */
public class TaskLynxGui {

    private static final LynxControl LYNX_CONTROL = new LynxControl("./data/log.txt");

    public String load() throws LynxException {
        return LYNX_CONTROL.load();
    }

    public String save() throws LynxException {
        return LYNX_CONTROL.save();
    }

    public String getCommandResponse(String input) {
        return LYNX_CONTROL.scanForCommandsGui(input);
    }

    public List<String> getGreetings() {
        List<String> greetings = new ArrayList<>();
        greetings.add("Hello! I'm Tasklynx. \n"
                + "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
        greetings.add(LYNX_CONTROL.tasksToday());
        return greetings;
    }

    public List<String> getFarewells() {
        List<String> farewells = new ArrayList<>();
        farewells.add(LYNX_CONTROL.tasksFromToday());
        farewells.add("Goodbye. I'll be here whenever you need to stay on track. (Exiting in 5 seconds)");
        return farewells;
    }

}
