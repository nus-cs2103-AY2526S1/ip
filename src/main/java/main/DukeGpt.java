package main;

import java.util.Scanner;

import command.Command;
import command.Exit;
import command.Greet;
import exception.DukeException;
import notebook.NoteBook;
import parser.Parser;
import storage.Storage;
import ui.Ui;

/**
 * Entry of the program.
 */
public class DukeGpt {
    /**
     * Instance of storage being used
     */
    private final Storage storage;
    private final NoteBook noteBook;
    private String lastCommandType;

    /**
     * Initialises instance of chatbot with storage
     */
    public DukeGpt() {
        this.storage = new Storage();
        this.noteBook = new NoteBook();
    }

    /**
     * The main repeating loop, where users type command and bot
     * responds accordingly.
     */
    public void loop() {
        Command c = new Greet();
        Scanner sc = new Scanner(System.in);

        while (!(c instanceof Exit)) {
            assert c != null;
            try {
                c.execute(this.storage, this.noteBook);
                c = Parser.parse(sc.nextLine());
            } catch (DukeException e) {
                Ui.displayError(e);
            }
        }
    }

    public String[] getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.lastCommandType = c.getClass().getSimpleName();
            return c.execute(this.storage, this.noteBook);
        } catch (DukeException e) {
            Ui.displayError(e);
            return new String[]{"Invalid input!" + e.toString()};
        }
    }

    /**
     * Returns last command type in string format
     * @return String format of last command
     */
    public String getCommandType() {
        assert this.lastCommandType != null;
        return this.lastCommandType;
    }

    /**
     * Entry of program when using CLI
     * @param args  Optional args, not used
     */
    public static void main(String[] args) {
        DukeGpt bot = new DukeGpt();
        bot.loop();
    }
}
