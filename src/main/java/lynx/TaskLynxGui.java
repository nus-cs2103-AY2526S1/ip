package lynx;

import java.util.ArrayList;
import java.util.List;

import lynx.parser.LynxGeneral;
import lynx.parser.LynxScanner;
import lynx.parser.LynxTaskEditor;
import objectclasses.exception.LynxException;

/**
 * Main class for the GUI application.
 */
public class TaskLynxGui {

    public String load() throws LynxException {
        return LynxGeneral.reload();
    }

    public String save() throws LynxException {
        return LynxGeneral.save();
    }

    public String getCommandResponse(String input) {
        return LynxScanner.scanForCommandsGui(input);
    }

    public List<String> getGreetings() {
        List<String> greetings = new ArrayList<>();
        greetings.add("Hello! I'm Tasklynx. \n"
                + "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
        greetings.add(LynxTaskEditor.tasksToday());
        return greetings;
    }

    public List<String> getFarewells() {
        List<String> farewells = new ArrayList<>();
        farewells.add(LynxTaskEditor.tasksFromToday());
        farewells.add("Goodbye. I'll be here whenever you need to stay on track. (Exiting in 5 seconds)");
        return farewells;
    }

}
