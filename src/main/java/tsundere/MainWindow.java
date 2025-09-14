package tsundere;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tsundere.command.Autocomplete;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView backgroundView;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private StackPane dialogWithBackground;
    @FXML
    private Label autocomplete;

    private Tsundere tsundere;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/emiya.jpg"));
    private Image tsundereImage = new Image(this.getClass().getResourceAsStream("/images/tohsaka.jpg"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/house.jpg"));

    /**
     * Binds scrollbar to the dialog container, and sets up Tsundere Graphics UI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        backgroundView.setPreserveRatio(false);
        backgroundView.setSmooth(true);
        backgroundView.setImage(backgroundImage);
        backgroundView.fitWidthProperty().bind(scrollPane.widthProperty());
        backgroundView.fitHeightProperty().bind(scrollPane.heightProperty());
    }
    /** Injects the Duke instance */
    public void setTsundere(Tsundere t) {
        tsundere = t;
        tsundere.setGraphicsUi(dialogContainer, tsundereImage);
    }

    /**
     * Every time the user types, attempts to provide an autocomplete box.
     * If the current input is wrong, set the text color to red.
     */
    @FXML
    private void handleKeyType() {
        String text = userInput.getText();
        int len = text.length();
        autocomplete.setTranslateX(6.0 * len);
        String autocompleteText = Autocomplete.generateAutocomplete(text);
        if (autocompleteText == null) {
            userInput.setStyle("-fx-text-fill: red;");
            autocomplete.setVisible(false);
        } else {
            autocomplete.setVisible(!text.isEmpty() && !autocompleteText.isEmpty());
            autocomplete.setText(autocompleteText);
            userInput.setStyle("-fx-text-fill: black;");
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(dialogContainer, input, userImage));
        tsundere.displayResponse(input);
        userInput.clear();
    }
}
