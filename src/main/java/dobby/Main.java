package dobby;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dobbyImage = new Image(this.getClass().getResourceAsStream("/images/dobby.jpg"));

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

        stage.setTitle("Dobby");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        // Anchors
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

        // Auto-scroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        // Add initial welcome message
        showWelcomeMessage();

        stage.show();
    }

    private void showWelcomeMessage() {
        String welcomeText = "Hello! I'm Dobby 🤖\n"
                + "Here are some commands you can try:\n"
                + "1. list - Display all tasks\n"
                + "2. todo <description> - Add a ToDo task\n"
                + "3. deadline <description> /by <yyyy-MM-dd HHmm> - Add a Deadline task\n"
                + "4. event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an Event\n"
                + "5. mark <task number> - Mark a task as done\n"
                + "6. unmark <task number> - Mark a task as not done\n"
                + "7. delete <task number> - Delete a task\n"
                + "8. find <keyword> - Search tasks by keyword\n"
                + "9. help - Show help message\n"
                + "10. bye - Exit the application";


    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = new DialogBox(input, userImage);

        // Use Dobby.getResponse() instead of just echoing
        String response = Dobby.getResponse(input);
        DialogBox dobbyDialog = new DialogBox(response, dobbyImage);

        dialogContainer.getChildren().addAll(userDialog, dobbyDialog);
        userInput.clear();

        // Exit GUI if input is "bye" (case-insensitive)
        if (input.trim().equalsIgnoreCase("bye")) {
            // Close the Stage after a short delay so the user sees the message
            new Thread(() -> {
                try {
                    Thread.sleep(500); // half a second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                javafx.application.Platform.runLater(() -> {
                    Stage stage = (Stage) scene.getWindow();
                    stage.close();
                });
            }).start();
        }
    }


    public static class DialogBox extends HBox {

        private Label text;
        private ImageView displayPicture;

        public DialogBox(String s, Image i) {
            text = new Label(s);
            displayPicture = new ImageView(i);

            // Resize image
            double size = 50.0; // desired diameter
            displayPicture.setFitWidth(size);
            displayPicture.setFitHeight(size);

            // Make circular
            Circle clip = new Circle(size / 2, size / 2, size / 2);
            displayPicture.setClip(clip);

            // Add a border by snapshotting the clip
            SnapshotParameters parameters = new SnapshotParameters();
            WritableImage image = displayPicture.snapshot(parameters, null);
            displayPicture.setClip(null);
            displayPicture.setImage(image);

            // Styling the dialog box
            text.setWrapText(true);
            this.setAlignment(Pos.TOP_RIGHT);

            this.getChildren().addAll(text, displayPicture);
        }

    }
}
