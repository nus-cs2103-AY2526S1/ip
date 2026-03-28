import exceptions.DukeyException;
import tasklist.TaskList;


/** Dukey class represents chatbot */
public class Dukey {

    /* storage object to handle interactions with .txt file */
    private Storage storage;
    /* list of tasks */
    private TaskList taskList;
    /* parser to decide how to respond to user input */
    private Parser parser;
    /* ui to receive and process user input */
    private Ui ui;
    /* reply message to output */
    private String output;

    /**
     * Initialises Dukey chatbot with the required supporting classes.
     */
    public Dukey(String filePath) {

        //Initialise supporting classes
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, filePath);
        storage.load();
        this.parser = new Parser(this, taskList, storage);
        this.ui = new Ui(parser);
    }

    /**
     * Ends program.
     */
    public String end() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Gets reply message from ui.
     * @return String return message
     */
    String reply(String input) {
        try {
            return ui.reply(input); // Start taking and replying to user input
        } catch (DukeyException exception) {
            return exception.getMessage(); // Handle any exceptions
        }
    }

}
