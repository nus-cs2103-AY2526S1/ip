package stella;

/**
 *  Responsible for initialising the Stella application
 */
public class Stella {
    private TaskList tasks;
    private Parser parser;
    private Ui ui;


    public Stella() {
        tasks = new TaskList(Storage.readFile());
        parser = new Parser(tasks);
        ui = new Ui(parser);
    }


    /**
     * Generate Stella's response based on User's response
     *
     * @param input User's response
     * @return Stella's response
     */
    public String getResponse(String input) {
        return ui.callInteraction(input);
    }
}
