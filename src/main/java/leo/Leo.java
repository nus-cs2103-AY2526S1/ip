package leo;

import java.io.IOException;

import leo.display.Messages;
import leo.display.UI;
import leo.functions.Functions;
import leo.functions.task.TaskList;

/**
 * Represents the chatbot application Leo.
 */
public class Leo {
    private FileHandler fileHandler;
    private UI ui;
    private TaskList tasks;
    private Functions functions;

    /**
     * Instantiates the chatbot Leo.
     *
     * @param filePath The file path where the data will be stored.
     * @throws IOException If file path is incorrect and a new file cannot be created
     *      due to input errors.
     */
    public Leo(String filePath) throws IOException {
        this.ui = new UI();
        this.fileHandler = new FileHandler(filePath);

        try {
            Messages.greetings();
            this.tasks = new TaskList(fileHandler.loadFile());
        } catch (IOException e) {
            throw new IOException("File not found and failed to create.");
        } catch (ArrayIndexOutOfBoundsException e) {
            Messages.messageBreak();
            System.out.println(e.getMessage());
            Messages.messageBreak();
        }
    }

    /**
     * Starts Leo to prompt user for input.
     */
    public void run() {
        this.functions = new Functions(this.tasks);
        this.ui.getUserInput(this.functions);
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param userInput Text containing the user input into the application.
     * @return String containing the response from the chatbot.
     */
    public String getResponse(String userInput) {
        this.functions = new Functions(this.tasks);
        return this.ui.getLeoResponse(this.functions, userInput);
    }

    /**
     * The main entry point for the chatbot application.
     *
     * @param args Command line input
     * @throws IOException If file fails to load
     */
    public static void main(String[] args) throws IOException {
        new Leo(".data/Leo.txt").run();
    }
}
