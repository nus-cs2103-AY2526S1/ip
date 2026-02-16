package bambam;

import java.io.IOException;

import bambam.command.Command;

/**
 * Handles the main entry point of Bambam chatbot.
 */
public class Bambam {

    private String commandType;
    private final TaskStorage storage;
    private final TaskList taskList;
    private final Messages messages;
    private final Parser parser;

    public Bambam() throws IOException, BambamException {
        this.storage = new TaskStorage();
        this.taskList = storage.loadTasks();
        this.messages = new Messages(taskList);
        this.parser = new Parser();
    }

    /**
     * Facilitates communication between user and the chatbot.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     * @throws IOException If an input or output operation fails.
     */
    public void communication() throws IOException {
        messages.printGreetings();

        Parser parser = new Parser();
        boolean isExitCommand = false;

        while (!isExitCommand) {
            String input = messages.getInput();
            try {
                Command command = parser.parse(input);
                command.execute(storage, messages, taskList);

                isExitCommand = command.isExitCommand();
            } catch (BambamException e) {
                messages.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Handles the main method of the Bambam chatbot.
     * @param args The argument of Command-line
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     * @throws IOException If an input or output operation fails.
     */
    public static void main(String[] args) throws BambamException, IOException {
        Bambam bambam = new Bambam();
        bambam.communication();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            c.execute(storage, messages, taskList);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (BambamException e) {
            return "Error: " + e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public String getCommandType() {
        return commandType;
    }
}
