package lynx;

import java.util.ArrayList;
import java.util.List;

import lynx.parser.LynxControl;
import lynx.ui.LynxUI;
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

    public String getCommandResponse(String input) throws LynxException {
        return LYNX_CONTROL.scanForCommandsGui(input);
    }

    public List<String> getGreetings() {
        List<String> greetings = new ArrayList<>();
        greetings.add(LynxUI.hello());
        greetings.add(LYNX_CONTROL.tasksToday());
        return greetings;
    }

    public List<String> getFarewells() {
        List<String> farewells = new ArrayList<>();
        farewells.add(LYNX_CONTROL.tasksFromToday());
        farewells.add(LynxUI.bye());
        return farewells;
    }

}
