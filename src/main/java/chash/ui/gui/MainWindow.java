package chash.ui.gui;

import chash.command.Command;
import chash.exception.ChashException;
import chash.parser.CommandParser;
import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

/**
 * The main controller class for the JavaFX-based CHASH GUI window.
 * Handles user input events and updates the chat history.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatHistBox;
    @FXML
    private TextField userInputBox;
    @FXML
    private Button submitButton;

    private ChashUi gui;
    private ChashDb db;
    private TaskList tasks;

    /** Constructs the main window and loads the associated FXML layout. */
    public MainWindow() {
        //Load view info from fxml file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //FXML control objects should be created by the loader
    }

    //Called after fxml injects control objects
    @FXML
    private void initialize() {
        //Setup gui and model objects
        this.gui = new ChashGui(this.chatHistBox);
        this.db = new ChashDb();
        try {
            this.tasks = new TaskList(this.db.loadDb(this.gui));
        } catch (IOException ex) {
            this.tasks = new TaskList();
            this.gui.printErr(ex.getMessage());
        }

        //bind elements to functions / set event handler
        this.userInputBox.setOnAction(event -> handleUserInput());
        this.submitButton.setOnMouseClicked(event -> handleUserInput());

        scrollPane.vvalueProperty().bind(this.chatHistBox.heightProperty());

        this.gui.printWelcome();
    }

    @FXML
    private void handleUserInput() {
        String fullCommand = this.userInputBox.getText();
        this.gui.printUserInput(fullCommand);

        try {
            Command cmd = CommandParser.parse(fullCommand);
            cmd.execute(this.tasks, this.gui, this.db);
            if (cmd.isExit()) {
                //Cant use Thread.sleep(3000); as it will hang the JFX thread
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> Platform.exit());

                //Start the sleep and then exit
                //But as the thread is still alive it maybe possible to sneak some commands in
                delay.play();
            }
        } catch (ChashException ex) {
            this.gui.printErr(ex.getMessage());
        }
        this.userInputBox.clear();
    }
}
