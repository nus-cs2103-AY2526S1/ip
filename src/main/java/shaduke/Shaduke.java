package shaduke;

import shaduke.clientcommands.ClientCommand;
import shaduke.clientcommands.ClientParser;
import shaduke.clients.ClientList;
import shaduke.commands.Command;
import shaduke.commands.Parser;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

import java.io.IOException;
import java.util.Objects;

/**
 * The ACow program is the main entry point to an application
 * that stores and manages tasks.
 *
 * @author isaactoh
 */
public class Shaduke {
    private final Ui ui;
    private static TaskList todolist;
    private static Storage storage;
    private static ClientList clients;

    /**
     * Constructs a Shaduke instance with the specified storage file
     *
     * @param filePath the file path to where tasks are stored
     */
    public Shaduke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        clients = new ClientList();

        try {
            todolist = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("No previous data found like my T100 money.");
            ui.showError("Starting fresh!");
            todolist = new TaskList();
        }
    }

    /**
     * Runs the main program loop, reading user commands,
     * executing them, and displaying results until exit command is issued
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.printDashes();
                if (Objects.equals(command.split(" ")[0], "client")) {
                    ClientCommand c = ClientParser.parse(command);
                    c.execute(todolist, clients, ui);
                } else {
                    Command c = Parser.parse(command);
                    c.execute(todolist, ui, storage);
                    isExit = c.isExit();
                }
            } catch (ShadukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printDashes();
            }
        }
    }

    /**
     * Handles responses from the GUI.
     *
     * @param input The text input from the GUI.
     * @return The new message to be displayed on the GUI.
     */
    public String getResponse(String input) {
        try {
            if (Objects.equals(input.split(" ")[0], "client")) {
                ClientCommand c = ClientParser.parse(input);
                return c.executeAndReturn(todolist, clients);
            } else {
                Command c = Parser.parse(input);
                return c.executeAndReturn(todolist, storage);
            }
        } catch (ShadukeException e) {
            return e.getMessage();
        }
    }

    /**
     * The main entry point for the ACow application
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Shaduke("data/tasks.txt").run();
    }
}