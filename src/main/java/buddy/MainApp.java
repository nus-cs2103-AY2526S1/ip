package buddy;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Buddy buddy = new Buddy();
    private VBox dialogContainer;
    private ScrollPane scrollPane;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage;
    private Image buddyImage;

    @Override
    public void start(Stage stage) {
        loadImages();
        initializeComponents();
        setupLayout();
        configureStage(stage);
        setupEventHandlers();
        showWelcomeMessage();

        stage.show();
    }

    private void loadImages() {
        try {
            userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        } catch (Exception e) {
            userImage = createDefaultImage();
        }

        try {
            buddyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
        } catch (Exception e) {
            buddyImage = createDefaultImage();
        }
    }

    private Image createDefaultImage() {
        return new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==", 40, 40, true, true);
    }

    private void initializeComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        // Enhanced styling for input controls
        userInput.setPromptText("Type your message here...");
        userInput.setFont(Font.font("System", FontWeight.NORMAL, 14));
        userInput.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #E0E0E0; "
                         + "-fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 8 15;");

        sendButton.setFont(Font.font("System", FontWeight.BOLD, 12));
        sendButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; "
                          + "-fx-background-radius: 20; -fx-border-radius: 20; "
                          + "-fx-cursor: hand;");
    }

    private void setupLayout() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #FAFAFA, #F0F0F0);");
        mainLayout.setPadding(new Insets(10));

        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        dialogContainer.setStyle("-fx-background-color: transparent;");
        dialogContainer.setSpacing(8.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
    }

    private void configureStage(Stage stage) {
        stage.setScene(scene);
        stage.setTitle("🚀 Buddy - Your Enthusiastic Productivity Companion! ✨");
        stage.setResizable(true); // Enable resizing
        stage.setMinHeight(500.0);
        stage.setMinWidth(400.0);

        // Set initial size
        stage.setWidth(450.0);
        stage.setHeight(650.0);

        setupResponsiveLayout();
    }

    private void setupResponsiveLayout() {
        // Make components responsive to window resizing
        scrollPane.prefWidthProperty().bind(scene.widthProperty().subtract(20));
        scrollPane.prefHeightProperty().bind(scene.heightProperty().subtract(80));

        userInput.prefWidthProperty().bind(scene.widthProperty().subtract(100));

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);

        // Positioning
        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 10.0);
        AnchorPane.setRightAnchor(scrollPane, 10.0);

        AnchorPane.setBottomAnchor(userInput, 15.0);
        AnchorPane.setLeftAnchor(userInput, 10.0);
        AnchorPane.setRightAnchor(userInput, 80.0);

        AnchorPane.setBottomAnchor(sendButton, 15.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);
        sendButton.setPrefWidth(60.0);
        sendButton.setPrefHeight(35.0);
    }

    private void setupEventHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Auto-scroll to bottom when new messages are added
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Add hover effects
        sendButton.setOnMouseEntered(e -> sendButton.setStyle(
            "-fx-background-color: #45A049; -fx-text-fill: white; "
          + "-fx-background-radius: 20; -fx-border-radius: 20; -fx-cursor: hand;"));

        sendButton.setOnMouseExited(e -> sendButton.setStyle(
            "-fx-background-color: #4CAF50; -fx-text-fill: white; "
          + "-fx-background-radius: 20; -fx-border-radius: 20; -fx-cursor: hand;"));
    }

    private void showWelcomeMessage() {
        buddy.initializeWithGui();
        String welcomeMessage = buddy.getWelcomeMessage();
        dialogContainer.getChildren().add(
            DialogBox.getBuddyDialog(welcomeMessage, buddyImage)
        );
    }

    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = buddy.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getBuddyDialog(response, buddyImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            // Add a small delay before closing to show the goodbye message
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    System.exit(0);
                } catch (InterruptedException e) {
                    System.exit(0);
                }
            }).start();
        }
    }
}