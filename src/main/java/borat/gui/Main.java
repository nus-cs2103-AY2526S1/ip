package borat.gui;

import borat.Borat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Borat borat;

    private Image userImage;
    private Image dukeImage;

    @Override
    public void start(Stage stage) {
        borat = new Borat("data/tasks.txt");

        userImage = new Image(java.util.Objects.requireNonNull(
                getClass().getResourceAsStream("/images/borat.png"),
                "Missing /images/borat.png"));
        dukeImage = new Image(java.util.Objects.requireNonNull(
                getClass().getResourceAsStream("/images/mcgregor.png"),
                "Missing /images/mcgregor.png"));

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        sendButton.setOnAction(e -> handleUserInput());
        userInput.setOnAction(e -> handleUserInput());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Simple anchoring
        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 10.0);
        AnchorPane.setRightAnchor(scrollPane, 10.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);

        AnchorPane.setLeftAnchor(userInput, 10.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);
        AnchorPane.setRightAnchor(userInput, 80.0);

        AnchorPane.setRightAnchor(sendButton, 10.0);
        AnchorPane.setBottomAnchor(sendButton, 10.0);
        sendButton.setPrefWidth(60);

        scene = new Scene(mainLayout, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Borat");
        stage.show();

        // Greeting message
        dialogContainer.getChildren().add(new DialogBox("Hello! I'm Borat.\nWhat can I do for you?", dukeImage));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }
        dialogContainer.getChildren().add(new DialogBox(input, userImage));
        String response = borat.getResponse(input);
        dialogContainer.getChildren().add(new DialogBox(response, dukeImage));
        userInput.clear();
        scrollPane.setVvalue(1.0);
    }
}


