package nerpbot.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nerpbot.NerpBot;
import javafx.scene.layout.Region;

/**
 * Controller for the MainWindow. Provides the layout for the other controls.
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

    private NerpBot nerpBot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaNerpBot.jpeg"));

    @FXML
    public void initialize() {
        // Configure ScrollPane to work properly
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Allow dialog container to grow
        dialogContainer.setMinHeight(100);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setMaxHeight(Double.MAX_VALUE);

        // Auto-scroll to bottom when content changes
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) ->
                scrollPane.setVvalue(1.0));

        System.out.println("Dialog container initialized");
    }

    public void setNerpBot(NerpBot nerpBot) {
        this.nerpBot = nerpBot;

        // Show welcome message
        String welcomeMessage = nerpBot.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getNerpBotDialog(welcomeMessage, botImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return; // Don't process empty messages
        }

        String response = nerpBot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );

        // Used copilot to help implement error handling
        if (response.startsWith(NerpBot.ERROR_PREFIX)) {
            String errorMessage = response.substring(NerpBot.ERROR_PREFIX.length());
            dialogContainer.getChildren().add(
                    DialogBox.getNerpBotErrorDialog(errorMessage, botImage)
            );
        } else if (response.startsWith(NerpBot.CONFIRM_PREFIX)) {
            String confirmMessage = response.substring(NerpBot.CONFIRM_PREFIX.length());
            dialogContainer.getChildren().add(
                    DialogBox.getNerpBotConfirmDialog(confirmMessage, botImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getNerpBotDialog(response, botImage)
            );
        }

        userInput.clear();

        // Force scrolling to bottom
        scrollPane.setVvalue(1.0);

        // Close application if user types "bye"
        if (input.trim().equalsIgnoreCase("bye")) {
            // Add delay to allow seeing the goodbye message
            new Thread(() -> {
                try {
                    Thread.sleep(1500); // 1.5 second delay
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        if (input.trim().equalsIgnoreCase("help")) {
            nerpBot.showHelpWindow();
        }
    }
}
