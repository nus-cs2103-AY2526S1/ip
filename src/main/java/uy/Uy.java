package uy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.sun.source.tree.Tree;

/**
 * Main application logic for Uy. Holds task state, storage and parser,
 * and provides an API to generate responses for incoming messages.
 */
public class Uy {

    private TaskList tasks = new TaskList();
    private Storage storage;
    private UI ui = new UI();
    private Parser parser = new Parser();

    public static Scanner input = new Scanner(System.in);

    private static final DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter output_date_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Generates a response for the user's chat message. This is the primary
     * public API used by the GUI to obtain a reply for display.
     *
     * @param input user input command
     * @return response string to show to the user
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return ui.showGoodbye();
            }
            return parser.parseAndRun(input, tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Construct Uy and load tasks from the given file path.
     *
     * @param filePath relative path under src where the data file resides
     */
    public Uy(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.loadTasks();
        } catch (Exception e) {
            e.printStackTrace();
            this.tasks = new TaskList();
        }
    }

    /**
     * Default constructor uses the default "data" path.
     */
    public Uy() {
        this("data");
    }

    /**
     * Run the interactive command loop on the console. Intended for CLI use.
     */
    public void run() {
        while (true) {
            try {
                String message = readString();
                parser.parseAndRun(message, tasks, ui, storage);

                assert message != null;
                assert storage != null;
                
                if(message.equals("bye")) {
                    break;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Parse a date string. Accepts yyyy-MM-dd and the app's output format MMM dd yyyy.
     *
     * @param date input date string
     * @return parsed LocalDate or null on error
     */
    public static LocalDate parse_date(String date) {
        try {
            return LocalDate.parse(date, date_formatter);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date, output_date_formatter);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
        return null;
    }

    /**
     * Format a LocalDate into the app's human friendly date format.
     *
     * @param date date to format
     * @return formatted date string
     */
    public static String format_date(LocalDate date) {
        return date.format(output_date_formatter);
    }

    /**
     * Read the next token from the console input. (wrapper around Scanner)
     */
    public static String readString() {
        return input.next();
    }

    /**
     * Read the next integer from the console input.
     */
    public static int readInt() {
        return input.nextInt();
    }

    public static void main(String[] args) {
        Uy uy = new Uy("data/Duke.txt");
        uy.run();
    }
}
