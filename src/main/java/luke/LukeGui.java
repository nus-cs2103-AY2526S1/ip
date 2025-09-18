package luke;

import java.util.ArrayList;

public class LukeGui {

    protected final Ui ui;
    protected final Parser parser;
    protected final ArrayList<Task> tasks;

    public LukeGui() {
        Storage storage = new Storage("tasks.txt");
        storage.initialize();
        this.tasks = storage.tasks;

        this.ui = new Ui();
        this.parser = new Parser("", tasks);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        LukeGui lukeGui = new LukeGui();
        Parser parser = new Parser(input, lukeGui.tasks);
        return parser.runGui();
    }
}

