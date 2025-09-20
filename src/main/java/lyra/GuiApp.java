package lyra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lyra.util.Storage;
import lyra.task.TaskList;
import lyra.command.Command;
import lyra.util.Parser;
import lyra.exception.LyraException;

/**
 * GUI Application class for the Lyra task management application.
 * This class extends Application to properly initialize JavaFX.
 */
public class GuiApp extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    private Storage storage;
    private TaskList tasks;
    private GuiUi guiUi;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        // Initialize components
        storage = new Storage("data/lyra.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (LyraException e) {
            tasks = new TaskList();
        }
        guiUi = new GuiUi();

        // Set up the main window
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Set up the layout with improved styling
        stage.setTitle("Lyra - Your Personal Task Manager");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);
        stage.setWidth(600.0);
        stage.setHeight(700.0);

        // Modern gradient background
        mainLayout.setStyle(
            "-fx-background-color: linear-gradient(to bottom, #f8fafc 0%, #f1f5f9 100%);"
        );

        // Configure scroll pane with better styling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-border-color: transparent; " +
            "-fx-background-insets: 0;"
        );
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);

        // Configure dialog container
        dialogContainer.setPrefHeight(VBox.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(12);
        dialogContainer.setPadding(new Insets(20, 0, 20, 0));
        dialogContainer.setStyle("-fx-background-color: transparent;");

        // Configure input field with modern styling
        userInput.setFont(Font.font("Segoe UI", 14));
        userInput.setStyle(
            "-fx-background-color: white; " +
            "-fx-border-color: #d1d5db; " +
            "-fx-border-radius: 25; " +
            "-fx-background-radius: 25; " +
            "-fx-padding: 12 20; " +
            "-fx-font-size: 14; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 3, 0, 0, 1);"
        );
        userInput.setPromptText("Type your message here...");

        // Configure send button with modern styling
        sendButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        sendButton.setStyle(
            "-fx-background-color: #0078d4; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 25; " +
            "-fx-font-weight: bold; " +
            "-fx-font-size: 14; " +
            "-fx-padding: 12 24; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,120,212,0.3), 3, 0, 0, 1);"
        );
        sendButton.setOnMouseEntered(e -> 
            sendButton.setStyle(
                "-fx-background-color: #106ebe; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 25; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 14; " +
                "-fx-padding: 12 24; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,120,212,0.4), 4, 0, 0, 1);"
            )
        );
        sendButton.setOnMouseExited(e -> 
            sendButton.setStyle(
                "-fx-background-color: #0078d4; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 25; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 14; " +
                "-fx-padding: 12 24; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,120,212,0.3), 3, 0, 0, 1);"
            )
        );

        // Set up responsive layout constraints
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 80.0);

        AnchorPane.setBottomAnchor(sendButton, 20.0);
        AnchorPane.setRightAnchor(sendButton, 20.0);

        AnchorPane.setBottomAnchor(userInput, 20.0);
        AnchorPane.setLeftAnchor(userInput, 20.0);
        AnchorPane.setRightAnchor(userInput, 100.0);

        // Add event handlers
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Show welcome message
        guiUi.showWelcome();
        String welcomeMessage = guiUi.getOutput();
        dialogContainer.getChildren().add(
            DialogBox.getLyraDialog(welcomeMessage)
        );
    }

    /**
     * Handles user input from the text field.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        // Add user input to dialog
        dialogContainer.getChildren().add(
            DialogBox.getUserDialog(input)
        );

        // Process command
        try {
            guiUi.clearOutput(); // Clear previous output
            Command c = Parser.parse(input);
            c.execute(tasks, guiUi, storage);
            
            // Get the output from the GUI UI
            String response = guiUi.getOutput();
            if (response.trim().isEmpty()) {
                response = "Command executed successfully.";
            }
            
            // Determine dialog type based on command type
            DialogBox responseDialog;
            if (isSuccessCommand(c)) {
                responseDialog = DialogBox.getSuccessDialog(response);
            } else {
                responseDialog = DialogBox.getLyraDialog(response);
            }
            
            dialogContainer.getChildren().add(responseDialog);

            // Check if exit command
            if (c.isExit()) {
                stage.close();
            }
        } catch (LyraException e) {
            // Show error with distinct styling
            dialogContainer.getChildren().add(
                DialogBox.getErrorDialog("⚠️ " + e.getMessage())
            );
        }

        // Clear input and scroll to bottom
        userInput.clear();
        scrollPane.setVvalue(1.0);
    }

    /**
     * Determines if a command should be displayed as a success message.
     */
    private boolean isSuccessCommand(Command c) {
        String className = c.getClass().getSimpleName();
        return className.equals("AddTodoCommand") || 
               className.equals("AddDeadlineCommand") || 
               className.equals("AddEventCommand") ||
               className.equals("MarkCommand") ||
               className.equals("UnmarkCommand") ||
               className.equals("DeleteCommand");
    }

    /**
     * Main entry point for the JavaFX GUI application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
