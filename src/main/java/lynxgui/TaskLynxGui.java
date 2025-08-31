package lynxgui;

import java.util.ArrayList;
import java.util.List;

import lynxgui.parser.LynxGeneral;
import lynxgui.parser.LynxScanner;
import lynxgui.parser.LynxTaskEditor;
import objectclasses.exception.LynxException;

/**
 * Main interface between the application frontend and backend.
 */
public class TaskLynxGui {

    public String load() throws LynxException {
        return LynxGeneral.reloadGui();
    }

    public String save() throws LynxException {
        return LynxGeneral.saveGui();
    }

    public String getCommandResponse(String input) {
        return LynxScanner.scanForCommandsGui(input);
    }

    public List<String> getGreetings() {
        List<String> greetings = new ArrayList<>();
        greetings.add("Hello! I'm Tasklynx. \n"
                + "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
        greetings.add(LynxTaskEditor.tasksTodayGui());
        return greetings;
    }

    public List<String> getFarewells() {
        List<String> farewells = new ArrayList<>();
        farewells.add(LynxTaskEditor.tasksFromTodayGui());
        farewells.add("Goodbye. I'll be here whenever you need to stay on track. (Exiting in 5 seconds)");
        return farewells;
    }

}
