package diablo.ui;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.Thread;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML



    private Diablo diablo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/diabloUser.png"));
    private Image diabloImage = new Image(this.getClass().getResourceAsStream("/images/diablo.png"));
    private String greeting = "Hello! I'm Diablo.\nWhat can I do for you?";

    /**
     * Initialises the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(
                Bindings.createDoubleBinding(
                        () -> 1.0,
                        dialogContainer.heightProperty()
                )
        );

        dialogContainer.getChildren().addAll(
                DialogBox.getDiabloDialog(greeting, diabloImage)
        );

    }

    /** Injects the Diablo instance */
    public void setDiablo(Diablo d) {
        diablo = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Diablo's reply and then appends them to
     * the dialog container. Clears the user input after processing. Will exit program if input "bye" is found.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";

        String[] output = diablo.getOutput(input);
        assert output.length >= 2 : "Output has to at least have 1 code and 1 message";

        diablo.addToStorage();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDiabloDialog(output[1], diabloImage)
        );

        if (output[0].equals("1")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        } else {
            userInput.clear();
        }
    }




}

