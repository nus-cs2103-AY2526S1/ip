import command.Command;
import command.Parser;
import components.DialogBox;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;

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
    private Button sendButton;

    private Pepe pepe;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/happy-pepe.jpg"));
    private Image pepeImage = new Image(this.getClass().getResourceAsStream("/images/swaggy-pepe.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
        dialogContainer.getChildren().add(
                DialogBox.getPepeDialog(pepe.displayWelcomeMessage(), pepeImage, false)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Ui ui = pepe.getUi();
        Storage storage = pepe.getStorage();
        TaskList taskList = pepe.getTaskList();

        String input = userInput.getText();
        String pepeResponse = "";
        Boolean canContinue = true;
        boolean hasError = false;
        try {
            Command command = Parser.parse(input);
            Pair<String, Boolean> response = command.execute(ui, storage, taskList);
            pepeResponse = response.getKey();
            canContinue = response.getValue();
        } catch (PepeException e) {
            pepeResponse = ui.handleException(e);
            hasError = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPepeDialog(pepeResponse, pepeImage, hasError)
        );

        userInput.clear();
        if (!canContinue) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
    }
}
