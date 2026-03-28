package gui;

import hachi.Hachi;
import hachi.Parser;
import hachi.Ui;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hachi hachi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Hachi.png"));

    @FXML
    public void initialize() {
        assert userImage != null : "Failed to load user image!";
        assert dukeImage != null : "Failed to load duke image!";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setFillWidth(true);
        dialogContainer.setAlignment(Pos.TOP_LEFT);
    }

    /** Inject bot and show startup message */
    public void setHachi(Hachi h) {
        this.hachi = h;
        String startMsg = Ui.start();

        DialogBox first = DialogBox.getDukeDialog(startMsg, dukeImage);
        first.setMaxWidth(Double.MAX_VALUE);
        first.prefWidthProperty().bind(dialogContainer.widthProperty());
        dialogContainer.getChildren().add(first);
    }

    /** Handle user input */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.trim().isEmpty() : "User input is empty!";
        String response = Parser.parse(hachi.getTasks(), hachi.getStorage(), input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}

