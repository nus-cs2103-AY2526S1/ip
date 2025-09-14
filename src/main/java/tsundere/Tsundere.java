package tsundere;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tsundere.command.AbstractCommand;
import tsundere.log.Log;
import tsundere.parser.CommandParser;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.GraphicsUi;
import tsundere.ui.UiMessages;

/**
 * The entry point to the tsundere.Tsundere chatbot.
 */
public class Tsundere {
    private final GraphicsUi ui;
    private final TextStorage storage;
    private final TaskList tasks;
    private final Log log;

    /**
     * Initializes a new tsundere.Tsundere chatbot with a text file storage.
     */
    public Tsundere() {
        TextStorage storage;
        tasks = new TaskList();
        ui = new GraphicsUi();
        try {
            storage = TextStorage.of();
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
            throw new RuntimeException();
        }
        this.storage = storage;
        this.log = new Log(tasks, ui, storage);
    }
    /**
     * Starts the main command loop for tsundere.Tsundere.
     */
    public void run() {
        ui.displayMessage(UiMessages.WELCOME);
        try {
            tasks.addAll(storage.retrieveAll());
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(br);

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = sc.nextLine();
            AbstractCommand command = CommandParser.parse(fullCommand);
            command.execute(tasks, ui, storage, log);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        Tsundere tsundere = new Tsundere();
        tsundere.run();
    }

    /**
     * Initializes GraphicsUi with a welcome message.
     * @param vBox DialogContainer to display output
     * @param image Profile picture of Tsundere
     */
    public void setGraphicsUi(VBox vBox, Image image) {
        try {
            tasks.addAll(storage.retrieveAll());
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
        }
        ui.setResources(vBox, image);
        ui.displayMessage(UiMessages.WELCOME);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public void displayResponse(String input) {
        AbstractCommand command = CommandParser.parse(input);
        command.execute(tasks, ui, storage, log);
        if (command.isExit()) {
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    javafx.application.Platform.exit();
                } catch (InterruptedException e) {
                    ui.displayMessage(new TsundereExitDelayException().getMessage());
                }
            }).start();
        }
    }
}
