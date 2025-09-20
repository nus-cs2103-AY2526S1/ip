package marquess;

import marquess.command.Command;
import marquess.exception.MarquessException;

/**
 * Main class for Marquess.
 */
public class Marquess {

    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for Marquess.
     *
     * @param filePath File Path to the txt storage file.
     */
    public Marquess(String filePath) {
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(filePath);

        storage.load(taskList);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            assert c != null;
            String res = c.execute(storage, taskList);
            return res;
        } catch (MarquessException e) {
            return "Something has gone terribly wrong: " + e;
        }
    }

}
