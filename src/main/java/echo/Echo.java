package echo;

import echo.command.Command;
import echo.echoexception.EchoException;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.tasklist.TaskList;
import echo.ui.UI;

public class Echo {
    protected final Storage storage;
    protected final UI ui;
    protected final TaskList tasklist;

    public Echo() {
        this.storage = new Storage("data/echo.txt");
        this.ui = new UI();
        this.tasklist = this.storage.readFile();
    }

    public TaskList getTasklist() {
        return this.tasklist;
    }

    public UI getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the corresponding text output based on the user's input
     *
     * @param input String input by the user
     * @return Response to be shown to user
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(this, input);
            if (command == null) {
                return "I'm not sure I can do that.";
            }
            return command.execute();
        } catch (EchoException | IllegalArgumentException error) {
            return error.getMessage();
        }
    }
}
