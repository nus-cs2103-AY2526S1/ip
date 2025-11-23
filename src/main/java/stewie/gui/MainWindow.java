package stewie.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import stewie.Stewie;
import stewie.command.CommandType;
import stewie.ui.Ui;

/**
 * Enhanced controller for the main GUI with better UX and visual feedback
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
    @FXML
    private Label statusLabel;

    private Stewie stewie;
    private boolean isProcessing = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/stewie/gui/images/user.png"));
    private Image stewieImage = new Image(this.getClass().getResourceAsStream("/stewie/gui/images/stewie.png"));

    /** Initialize enhanced GUI layout */
    @FXML
    public void initialize() {
        // Bind scroll pane to always scroll to bottom when new content is added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Enable proper scrolling
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Make dialog container grow with content
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));

        // Add welcome message
        showWelcomeMessage();

        // Enhanced input field behavior
        setupInputField();

        // Setup status label
        setupStatusLabel();

        // Enable scrolling with mouse wheel and keyboard
        setupScrollBehavior();
    }

    /**
     * Sets up proper scroll behavior for better user experience
     */
    private void setupScrollBehavior() {
        // Allow scrolling with mouse wheel
        scrollPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * 0.005;
            scrollPane.setVvalue(scrollPane.getVvalue() - deltaY);
        });

        // Auto-scroll to bottom when new messages are added
        dialogContainer.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            Platform.runLater(() -> {
                scrollPane.vvalueProperty().unbind();
                scrollPane.setVvalue(1.0);
            });

        });
    }

    /**
     * Shows welcome message with typing animation effect
     */
    private void showWelcomeMessage() {
        String welcomeText = Ui.showGreeting(false);
        showTypingAnimation("", welcomeText, CommandType.UNKNOWN);
    }

    /**
     * Sets up enhanced input field with better UX
     */
    private void setupInputField() {
        // Add placeholder text effect
        userInput.setPromptText("Type your command here...");

        // Enhanced keyboard shortcuts
        userInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !isProcessing) {
                handleUserInput();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                userInput.clear();
            }
        });

        // Visual feedback when typing
        userInput.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.trim().isEmpty()) {
                sendButton.setDisable(false);
                sendButton.getStyleClass().add("button-ready");
            } else {
                sendButton.setDisable(true);
                sendButton.getStyleClass().remove("button-ready");
            }
        });

        // Initially disable send button
        sendButton.setDisable(true);
    }

    /**
     * Sets up status label for user feedback
     */
    private void setupStatusLabel() {
        if (statusLabel != null) {
            statusLabel.setText("Ready");
            statusLabel.getStyleClass().add("status-ready");
        }
    }

    /**
     * Updates status with visual feedback
     */
    private void updateStatus(String message, String styleClass) {
        if (statusLabel != null) {
            // Remove existing status styles
            statusLabel.getStyleClass().removeIf(s -> s.startsWith("status-"));
            statusLabel.setText(message);
            statusLabel.getStyleClass().add(styleClass);

            // Auto-clear status after 5 seconds
            Timeline clearStatus = new Timeline(
                    new KeyFrame(Duration.seconds(5), e -> {
                        statusLabel.setText("Ready");
                        statusLabel.getStyleClass().removeIf(s -> s.startsWith("status-"));
                        statusLabel.getStyleClass().add("status-ready");
                    })
            );
            clearStatus.play();
        }
    }

    /** Injects the Stewie instance */
    public void setStewie(Stewie d) {
        stewie = d;
    }

    /**
     * Enhanced user input handling with better feedback and validation
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        // Validate input
        if (input.isEmpty()) {
            updateStatus("Please enter a command", "status-warning");
            userInput.requestFocus();
            return;
        }

        if (isProcessing) {
            updateStatus("Processing previous command...", "status-processing");
            return;
        }

        // Set processing state
        isProcessing = true;
        sendButton.setDisable(true);
        sendButton.setText("...");
        updateStatus("Processing...", "status-processing");

        // Add user dialog immediately
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        // Process command in background to maintain UI responsiveness
        Platform.runLater(() -> {
            try {
                String response = stewie.getResponse(input);
                CommandType commandType = stewie.getCommandType();

                // Add Stewie's response directly (no typing animation)
                dialogContainer.getChildren().add(
                        DialogBox.getStewieDialog(response, stewieImage, commandType)
                );

                // Update status based on command type
                updateStatusForCommand(commandType);

            } catch (Exception e) {
                // Handle errors gracefully
                String errorResponse = "Oops! Something went wrong. Please try again.";
                dialogContainer.getChildren().add(
                        DialogBox.getStewieDialog(errorResponse, stewieImage, CommandType.UNKNOWN)
                );
                updateStatus("Error occurred", "status-error");
            } finally {
                // Reset processing state
                isProcessing = false;
                sendButton.setDisable(userInput.getText().trim().isEmpty());
                sendButton.setText("Send");
                userInput.requestFocus();
            }
        });
    }

    /**
     * Shows a simple typing indicator followed by the complete message
     */
    private void showTypingAnimation(String currentText, String fullText, CommandType commandType) {
        // Show typing indicator for a brief moment
        DialogBox typingBox = DialogBox.getStewieDialog("Stewie is typing...", stewieImage, CommandType.UNKNOWN);
        typingBox.getStyleClass().add("typing-indicator");
        dialogContainer.getChildren().add(typingBox);

        // After a short delay, replace with the actual message
        Timeline showMessage = new Timeline(
                new KeyFrame(Duration.millis(800), e -> {
                    // Remove typing indicator
                    dialogContainer.getChildren().remove(typingBox);

                    // Add the complete message
                    DialogBox messageBox = DialogBox.getStewieDialog(fullText, stewieImage, commandType);
                    dialogContainer.getChildren().add(messageBox);
                })
        );
        showMessage.play();
    }

    /**
     * Updates status based on command type for better user feedback
     */
    private void updateStatusForCommand(CommandType commandType) {
        switch (commandType) {
        case DEADLINE:
        case EVENT:
        case TODO:
            updateStatus("Task added successfully!", "status-success");
            break;
        case MARK:
            updateStatus("Task marked as done!", "status-success");
            break;
        case UNMARK:
            updateStatus("Task unmarked!", "status-info");
            break;
        case DELETE:
            updateStatus("Task deleted!", "status-warning");
            break;
        case LIST:
            updateStatus("Showing all tasks", "status-info");
            break;
        case FIND:
            updateStatus("Search completed", "status-info");
            break;
        default:
            updateStatus("Command processed", "status-ready");
        }
    }
}
