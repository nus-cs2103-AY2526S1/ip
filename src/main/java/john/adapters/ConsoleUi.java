package john.adapters;

import john.ports.Ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Console-backed implementation of Ui.
 * <p>
 * Reads user commands from a Scanner and writes output to a PrintStream.
 * <p>
 * Behavior:
 * - nextCommand() trims input, skips empty lines, and returns the next non-empty line.
 * - nextCommand() returns null on end-of-input (EOF).
 * - showMessage(msg) prints the message and then a separator line.
 * - showWelcome(count) prints a startup banner and the number of loaded tasks.
 * <p>
 * Resource ownership:
 * - This class closes the provided Scanner in close().
 * - The provided PrintStream is not closed by this class.
 * - Closing a Scanner that wraps System.in will close System.in.
 */
public class ConsoleUi implements Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a new console UI.
     *
     * @param in  input scanner to read commands from (typically wrapping System.in). Must not be null.
     * @param out output stream to write messages to (for example, System.out). Must not be null.
     */
    public ConsoleUi(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Returns the next non-empty, trimmed input line, or null if EOF is reached.
     * This method skips empty or whitespace-only lines and blocks until a non-empty line is available
     * or the underlying scanner has no more input.
     *
     * @return the next command line (trimmed), or null on EOF
     */
    @Override
    public String nextCommand() {
        while (true) {
            if (!in.hasNextLine()) {
                return null;
            }
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            return line;
        }
    }

    /**
     * Prints a message followed by a separator line for readability.
     * A newline is appended after each line.
     *
     * @param msg the message to display; if null, the literal "null" is printed
     */
    @Override
    public void showMessage(String msg) {
        out.println(msg);
        out.println("_________________________________");
    }

    /**
     * Closes the underlying Scanner.
     * Note: if the scanner wraps System.in, that stream will be closed as well.
     * The PrintStream provided at construction is not closed by this method.
     */
    @Override
    public void close() {
        in.close();
    }

    /**
     * Shows a startup banner and the number of tasks loaded.
     *
     * @param taskCount the number of tasks currently loaded
     */
    @Override
    public void showWelcome(int taskCount) {
        out.println("Loaded " + taskCount + " tasks.");
        showMessage("Hello! I'm John. What can I do for you?");
    }
}