package peppa.gui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import peppa.command.Peppa;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/george.png"));
    private Image peppaImage = new Image(this.getClass().getResourceAsStream("/images/peppa.png"));
    private Peppa peppa;

    @FXML
    public void initialise() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Ensure scroll pane scrolls to bottom when new content is added
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    public void setPeppa(Peppa p) {
        peppa = p;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     * If user types "bye", the application will exit after showing the goodbye message.
     * Automatically scrolls to show the latest messages.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = peppa.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, peppaImage));
        userInput.clear();
        
        // Ensure scrolling to bottom after adding new messages
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
        
        // Check if user said bye and exit the application
        if (input.trim().equalsIgnoreCase("bye")) {
            // Use a background thread for the delay to avoid blocking the UI
            new Thread(() -> {
                try {
                    // Wait for 1 second to let user read the goodbye message
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                // Exit on the JavaFX Application Thread
                Platform.runLater(Platform::exit);
            }).start();
        }
    }
}




/*
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our scene
        stage.show(); // Render the stage.
    }
}
 */