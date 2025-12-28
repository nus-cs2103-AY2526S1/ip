package benn.ui;

import benn.parser.Parser;
import benn.tasks.TaskManager;
import benn.commands.Command;
import benn.exceptions.BennException;
import benn.messages.MessageManager;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles all user interactions for Benn the Chatbot.
 *
 * <p>The {@code Ui} class is responsible for displaying messages
 * to the user, reading input from the console, and running the
 * main event loop of the chatbot. It delegates command parsing
 * to the {@link benn.parser.Parser} and execution to individual
 * {@link benn.commands.Command} objects.</p>
 *
 * <p>This class also implements {@link java.io.Closeable} to ensure
 * the underlying {@link java.util.Scanner} resource is properly closed.</p>
 */
public class Ui implements Closeable {
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} with a {@link Scanner} bound to standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the chatbot introduction message, including the ASCII logo and greeting.
     */
    private void showIntro() {
        System.out.println(MessageManager.retrieveIntroductionMessage());
    }

    /**
     * Reads a line of input from the user, trimming leading/trailing whitespace.
     *
     * @return the trimmed input string, or {@code null} if no more input is available
     */
    private String readLine() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }

    /**
     * Runs the main chatbot loop.
     *
     * <p>This method:
     * <ol>
     *   <li>Displays the introduction message</li>
     *   <li>Continuously reads user input until exit</li>
     *   <li>Parses the input into a {@link benn.commands.Command}</li>
     *   <li>Executes the command against the provided {@link benn.tasks.TaskManager}</li>
     *   <li>Prints the output of the command</li>
     *   <li>Terminates when the command signals exit</li>
     * </ol>
     * </p>
     *
     * @param taskManager the {@link TaskManager} managing the list of tasks
     * @throws BennException if a parsing or execution error occurs
     * @throws IOException if a storage operation fails
     */
    public void run(TaskManager taskManager) throws BennException, IOException {
        showIntro();

        boolean shouldExit = false;
        while (!shouldExit) {
            String input = readLine();
            if (input == null) break;
            Command command = Parser.parse(input);
            String output = command.execute(taskManager);
            System.out.println(output);
            shouldExit = command.shouldExit();
        }
    }

    /**
     * Closes the underlying {@link Scanner} resource.
     */
    @Override
    public void close() {
        scanner.close();
    }
}

