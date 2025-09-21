package merlie;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import merlie.command.Command;
import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.parser.Parser;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Main application class for Merlie the Merlion chatbot.
 * Handles the main application loop, input processing, and command execution.
 */
public class Merlie {
    private final Ui ui;
    private final TaskList taskList;
    private final ListFile listFile;
    private boolean isExited = false;
    private final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputBuffer);


    /**
     * Constructs a Merlie application instance with the specified data file.
     *
     * @param filepath The path of the file used to store tasks.
     */
    public Merlie(String filepath) {
        this.ui = new Ui();
        this.listFile = new ListFile(filepath);
        this.taskList = listFile.load();
    }

    /**
     * Runs the main loop of the application.
     * Reads user input, parses commands, and executes them until exit.
     * Empty MerlieException indicates the user entered an unrecognized or empty command.
     */
    public void run() {
        ui.start();
        while (!this.isExited) {
            String input = ui.readInput();
            try {
                Command c = Parser.parse(input);
                assert c != null : "Parser must return valid command or throw exception";
                c.execute(taskList, ui, listFile);
                this.isExited = c.isExited();
            } catch (MerlieException e) {
                if (e.getMessage().isEmpty()) {
                    ui.echo(input);
                } else {
                    ui.errorOutput(e.getMessage());
                }
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Gets the welcome message for GUI initialization.
     *
     * @return The formatted welcome message as a String.
     */
    public String getStartMessage() {
        return ui.getStartForGui();
    }

    /**
     * Processes a single user input and returns the chatbot's response as a String for the GUI.
     *
     * @param input The raw input string entered by the user.
     * @return The chatbot's response, formatted for GUI display.
     */
    public String getResponse(String input) {
        PrintStream originalOut = System.out;

        outputBuffer.reset();
        System.setOut(this.printStream);

        try {
            Command c = Parser.parse(input);
            assert c != null : "Parser must return valid command or throw exception";
            c.execute(taskList, ui, listFile);
            this.isExited = c.isExited();
        } catch (MerlieException e) {
            if (e.getMessage().isEmpty()) {
                ui.echo(input);
            } else {
                ui.errorOutput(e.getMessage());
            }
        } catch (Exception e) {
            ui.errorOutput("unexpected error");
        } finally {
            printStream.flush();
            System.setOut(originalOut);
        }

        String response = outputBuffer.toString();
        return this.ui.formatForGui(response);
    }

    /**
     * Checks if the application should exit.
     *
     * @return true if exit command was executed, false otherwise.
     */
    public boolean isExited() {
        return this.isExited;
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Merlie("./data/tasklist.txt").run();
    }
}
