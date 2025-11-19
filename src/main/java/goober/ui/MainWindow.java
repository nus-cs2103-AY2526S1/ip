package goober.ui;

import goober.Goober;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * ChatGPT used to add light and dark mode, and generate the background images.
 */
public class MainWindow extends BorderPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image gooberImage = new Image(this.getClass().getResourceAsStream("/images/DaGoober.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Goober goober;
    @FXML
    private BorderPane root;
    @FXML
    private ToggleButton themeToggle;


    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.disableProperty().bind(userInput.textProperty().isEmpty());
        HBox.setHgrow(userInput, Priority.ALWAYS);

        userInput.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER && !e.isShiftDown()) {
                e.consume();
                handleUserInput();
            }
        });

        themeToggle.selectedProperty().addListener((obs, was, is) -> {
            if (is) {
                if (!root.getStyleClass().contains("light")) {
                    root.getStyleClass().add("light");
                }
                themeToggle.setText("Dark");
            } else {
                root.getStyleClass().remove("light");
                themeToggle.setText("Light");
            }
        });

        setBackgroundClass("bg-photo");
    }

    /**
     * Injects the Goober instance
     */
    public void setGoober(Goober g) {
        goober = g;
        DialogBox greeting = DialogBox.getGooberDialog(g.getResponse("hello"), gooberImage);
        dialogContainer.getChildren().addAll(greeting);
    }

    /**
     * Creates two dialog boxes, one answering user input and the other containing Goober's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        if (input.isEmpty()) {
            return;
        }
        String response = goober.getResponse(input);
        dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getGooberDialog(response, gooberImage));
        userInput.clear();
    }

    @FXML
    private void handleClear() {
        dialogContainer.getChildren().clear();
    }

    private void setBackgroundClass(String cls) {
        // remove any old bg-* classes
        root.getStyleClass().removeIf(s -> s.startsWith("bg-"));
        if (cls != null && !cls.isBlank()) {
            if (!root.getStyleClass().contains(cls)) {
                root.getStyleClass().add(cls);
            }
        }
    }
}
