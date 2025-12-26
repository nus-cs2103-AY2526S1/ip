package Chunky;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Label taskCounterLabel; // New task counter label

    private Chunky chunky;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chunkyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Create label programmatically if FXML fails
        if (taskCounterLabel == null) {
            taskCounterLabel = new Label("Tasks: 0 total | 0 completed | 0 remaining");
            // Add it to the scene (you'll need to position it)
            this.getChildren().add(taskCounterLabel);
            AnchorPane.setBottomAnchor(taskCounterLabel, 45.0);
            AnchorPane.setLeftAnchor(taskCounterLabel, 5.0);
        }
    }

    public void setChunky(Chunky c) {
        chunky = c;
        String welcomeMessage = "Hello! I'm Chunky!\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getChunkyDialog(welcomeMessage, chunkyImage));
        updateTaskCounter(); // Initialize task counter
    }

    /**
     * Updates the task counter display with current task statistics
     */
    private void updateTaskCounter() {
        if (chunky != null && taskCounterLabel != null) {
            String stats = chunky.getTaskStats();
            taskCounterLabel.setText(stats);
        }
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        String response = chunky.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChunkyDialog(response, chunkyImage)
        );
        userInput.clear();

        // Update task counter after any command that might change tasks
        updateTaskCounter();
    }

    /**
     * Handles the stats button click to show detailed task statistics
     */
    @FXML
    private void handleStatsButton() {
        String detailedStats = chunky.getDetailedStats();
        dialogContainer.getChildren().add(
                DialogBox.getChunkyDialog(detailedStats, chunkyImage)
        );
    }


}