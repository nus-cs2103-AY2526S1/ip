package com.arnavjhajharia.penguin.app;

import com.arnavjhajharia.penguin.common.exceptions.PenguinException;
import com.arnavjhajharia.penguin.logic.Parser;
import com.arnavjhajharia.penguin.logic.commands.Command;
import com.arnavjhajharia.penguin.logic.commands.CommandResult;
import com.arnavjhajharia.penguin.model.TaskList;
import com.arnavjhajharia.penguin.ui.Ui;

/**
 * Coordinates the execution of the Penguin application.
 * <p>
 * The {@code Simulator} is responsible for:
 * <ul>
 *   <li>Initializing and managing the {@link TaskList}.</li>
 *   <li>Reading user input through the {@link Ui}.</li>
 *   <li>Parsing input into {@link Command} objects via {@link Parser}.</li>
 *   <li>Executing commands and showing results back to the user.</li>
 *   <li>Persisting tasks and shutting down gracefully when the user exits.</li>
 * </ul>
 */
public final class Simulator {

    /** The parser that interprets raw user input into {@link Command} objects. */
    private final Parser parser = new Parser();

    /** The in-memory list of tasks being managed. */
    private final TaskList tasks;

    /** The user interface implementation used to interact with the user. */
    private final Ui ui;

    /**
     * Constructs a new {@code Simulator}.
     *
     * @param filePath the file path used to load and save tasks
     * @param ui       the user interface for reading input and displaying output
     */
    public Simulator(String filePath, Ui ui) {
        this.tasks = new TaskList(100, filePath);
        this.ui = ui;
    }

    /**
     * Starts the main program loop.
     * <p>
     * Continuously reads input from the user, parses it into commands, and executes them.
     * Displays feedback or errors through the {@link Ui}.
     * <p>
     * The loop ends when either:
     * <ul>
     *   <li>The user explicitly issues an exit command (e.g., {@code bye}).</li>
     *   <li>Input is closed (EOF).</li>
     * </ul>
     */
    public void start() {
        ui.showIntro();

        while (true) {
            String prompt = ui.readLine();
            if (prompt == null) { // EOF / input closed: behave like bye
                shutdown();
                return;
            }

            try {
                Command cmd = parser.parse(prompt);
                CommandResult result = cmd.execute(tasks);

                ui.showDivider();
                ui.showText(result.message());
                ui.showDivider();

                if (result.isExit()) {
                    shutdown();
                    return;
                }
            } catch (PenguinException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                String msg = e.getMessage();
                ui.showError(msg == null || msg.isBlank() ? "An unexpected error occurred while processing your input." : msg);
            }
        }
    }

    /**
     * Shuts down the application gracefully.
     * <p>
     * Saves the current state of the {@link TaskList} to disk and
     * displays the exit message through the {@link Ui}.
     */
    private void shutdown() {
        tasks.save();
        ui.showExit();
    }
}
