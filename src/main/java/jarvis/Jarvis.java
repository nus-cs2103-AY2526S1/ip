package jarvis;
import jarvis.commands.Command;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;
import jarvis.tasks.TaskList;

import java.io.IOException;

public class Jarvis {
    private String commandType;
    private final Ui ui = new Ui();
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(java.nio.file.Paths.get("data", "Duke.txt"));
    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseTask(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (DarrenAssistantException e) {
            return "Error: " + e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getCommandType() {
        return commandType;
    }
}
