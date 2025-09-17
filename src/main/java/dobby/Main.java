package dobby;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        // --- Anchors ---
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0); // leave space for input
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(userInput, 55.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        scene = new Scene(mainLayout);
        stage.setScene(scene);

        // --- Auto-scroll ---
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        stage.show();
    }


    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = new DialogBox(input, userImage);

        // Use Dobby.getResponse() instead of just echoing
        String response = Dobby.getResponse(input);
        DialogBox dobbyDialog = new DialogBox(response, userImage);

        dialogContainer.getChildren().addAll(userDialog, dobbyDialog);
        userInput.clear();
    }


    private String getResponse(String input) {
        // Later you’ll call your Duke logic here
        return "Dobby says: " + input;
    }

    public static class DialogBox extends HBox {

        private Label text;
        private ImageView displayPicture;

        public DialogBox(String s, Image i) {
            text = new Label(s);
            displayPicture = new ImageView(i);

            // Styling the dialog box
            text.setWrapText(true);
            displayPicture.setFitWidth(100.0);
            displayPicture.setFitHeight(100.0);
            this.setAlignment(Pos.TOP_RIGHT);

            this.getChildren().addAll(text, displayPicture);
        }
    }
}
