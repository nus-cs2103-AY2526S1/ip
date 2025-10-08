package stella;

/**
 *  Initialises the Stella application.
 */
public class Stella {
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a new Stella object with the corresponding tasks, parser and ui.
     * The tasks are created based on what is stored in local storage.
     * The tasks are then used to create the parser object,
     * which in turn the parser object is used to create the ui object.
     */
    public Stella() {
        tasks = new TaskList(Storage.readFile());
        parser = new Parser(tasks);
        ui = new Ui(parser);
    }


    /**
     * Generates Stella's response based on User's response.
     *
     * @param input User's response.
     * @return Stella's response.
     */
    public String getResponse(String input) {
        return ui.callInteraction(input);
    }
}
