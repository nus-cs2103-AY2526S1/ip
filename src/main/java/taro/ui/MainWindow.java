package taro.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import taro.corecomp.Taro;

/**
 * Controller for the main GUI window of Taro.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Taro taro;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/ganadi.png"));
    private final Image taroImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));


    /**
     * Initialize the welcome message
     * **/
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getTaroDialog("Hello! I'm Taro\nYour personal assistant bot\n"
                        + "What can I do for you?", taroImage)
        );
    }

    /**
     * Injects the Taro instance.
     * */
    public void setTaro(Taro t) {
        taro = t;
    }

    /**
     * Handles user input by creating two dialog boxes:
     * one for the user message and one for Taro's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taro.getResponse(input);

        // Add in user dialog box
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        // Add in Taro dialog box, if correct command, normal display or show in red
        if (response.startsWith("Opps!")) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(response, taroImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getTaroDialog(response, taroImage)
            );
        }

        userInput.clear();
    }
}
