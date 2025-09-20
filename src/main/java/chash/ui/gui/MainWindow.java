package chash.ui.gui;

import chash.command.Command;
import chash.exception.ChashException;
import chash.parser.CommandParser;
import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

//ChashGui's Controller
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
                //Maybe sleep 2 seconds before exit
                Platform.exit();
            }
        } catch (ChashException ex) {
            this.gui.printErr(ex.getMessage());
        }
        this.userInputBox.clear();
    }
}
