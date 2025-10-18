
package uy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A variant entry-point similar to Uy. Kept for demonstration/compatibility.
 */
public class ProfessorQuokka {

    private TaskList tasks = new TaskList();
    private Storage storage;
    private UI ui = new UI();
    private Parser parser = new Parser();

    public static Scanner input = new Scanner(System.in);

    private static final DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter output_date_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Generates a response for the user's chat message.
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

    public ProfessorQuokka(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.loadTasks();
        } catch (Exception e) {
            e.printStackTrace();
            this.tasks = new TaskList();
        }
    }

    public ProfessorQuokka() {
        this("data/ProfessorQuokka.txt");
    }

    /**
     * Run the interactive command loop on the console. Intended for CLI use.
     */
    public void run() {
        ui.showWelcome();
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

    public static String format_date(LocalDate date) {
        return date.format(output_date_formatter);
    }

    public static String readString() {
        return input.next();
    }

    public static int readInt() {
        return input.nextInt();
    }

    public static void main(String[] args) {
        Uy uy = new Uy("data/Duke.txt");
        uy.run();
    }
}
