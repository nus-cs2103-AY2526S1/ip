package abang;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Abang abang;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image abangImage = new Image(this.getClass().getResourceAsStream("/images/DaAbang.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAbang(Abang a) {
        abang = a;
        dialogContainer.getChildren().add(
                DialogBox.getAbangDialog("Hello! I'm Abang\nWhat can I do for you?", abangImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = abang.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAbangDialog(response, abangImage)
        );
        userInput.clear();
    }
}
