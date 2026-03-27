package marvin.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import marvin.Marvin;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-64.png"));
    private final Image marvinImage = new Image(this.getClass().getResourceAsStream("/images/marvin-64.png"));

    @FXML
    private ImageView titleImage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Marvin marvin;

    /**
     * Initializes the main window with some predefined properties. Chiefly binds
     * scrollPane height with dialogContainer.
     */
    @FXML
    public void initialize() {
        // bind vbox height to scrollpane vvalue, so it scrolls down once the dialog container gets bigger.
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Load a font
        Font.loadFont(Main.class.getResource("/fonts/Red_Hat_Mono/static/RedHatMono-Bold.ttf").toExternalForm(), 32);
        Font.loadFont(Main.class.getResource("/fonts/Red_Hat_Mono/static/RedHatMono-Regular.ttf").toExternalForm(), 16);
        titleImage.setImage(marvinImage);
    }

    /**
     * Injects the Marvin instance
     */
    public void setMarvin(Marvin m) {
        marvin = m;
    }

    /**
     * Creates two dialog boxes, one containing the user's input and the other containing Marvin's reply,
     * and then appends them to the dialog container. Clears user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String marvinReply = marvin.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMarvinDialog(marvinReply, marvinImage)
        );

        userInput.clear();
    }
}
