package userinteraction;

import chatbot.Jocelyn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parse.Parser;
import storetasks.TaskList;

/**
 * This is the controller for the main graphical
 * user interface.
 * Both images are taken directly from Pixabay, which is
 * an open source image website.
 */
public class TheMainWindow extends AnchorPane {
    @FXML // in order to make it private and not public
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Button sendButton;
    private Stage s;

    private Jocelyn jocelyn;

    // setting the user image
    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/Cat.jpg"));

    // setting the image of the chatbot.
    private Image jocelynImage =
            new Image(this.getClass().getResourceAsStream("/images/Dog.jpg"));

    /**
     * This automatically scrolls the pane as it gets bigger.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * This creates a new Jocelyn instance.
     */
    public void setJocelyn(Jocelyn jocelyn) {
        this.jocelyn = jocelyn;
        String input = jocelyn.introduce();
        dialogContainer.getChildren().addAll(
                DialogChatBox.getJocelynDialog(input, jocelynImage));

    }

    /**
     * handleUserInput creates two dialog boxes, one echoing user input and the other
     * containing chatbot.Jocelyn's reply and then appends them to
     * the dialog container. After processing, the user box is
     * cleared.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Parser i = new Parser();
        TaskList text = new TaskList();
        UserInput it = new UserInput();
        String response = "";
        try {
            response = i.validityOfWords(input, text);
        } catch (Exception e) {
            response = e.getMessage();
        } finally {
            if (!response.equals("BYEBYEBYE")) {
                dialogContainer.getChildren().addAll(
                        DialogChatBox.getUserDialog(input, userImage),
                        DialogChatBox.getJocelynDialog(response, jocelynImage));
                userInput.clear();
            } else {
                s.close();
            }
        }
    }

    /**
     * sets the stage to give bye input
     * @param stage passed form theMainScene
     */
    public void setStage(Stage stage) {
        this.s = stage;
    }

}
