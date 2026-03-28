package nacho.ui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import nacho.Nacho;
import nacho.commons.QueryResult;
import nacho.commons.UiType;

/**
 * Main Class for JavaFX GUI ChatRoom with Nacho Chatbot
 */
public class Main extends Application {

    private static final double MIN_HEIGHT = 600.0;
    private static final double MIN_WIDTH = MIN_HEIGHT / 6 * 4;
    private static final double INPUT_WIDTH = MIN_WIDTH * 0.8125;
    private static final double INPUT_HEIGHT = MIN_HEIGHT * 0.05;
    private static final double BUTTON_WIDTH = MIN_WIDTH * 0.1375;
    private static final double SCROLL_WIDTH = MIN_WIDTH * 0.9925; // Ratio of Scroll Area
    private static final double SCROLL_HEIGHT = MIN_HEIGHT * 0.944666;

    private TextField userInput;
    private Scene scene;
    private Button sendButton;
    private ScrollPane scrollPane;
    private VBox dialogContainer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image nachoImage = new Image(this.getClass().getResourceAsStream("/images/DaNacho.jpg"));
    private Nacho nacho = new Nacho(UiType.GUI);

    @Override
    public void start(Stage stage) {

        Scene configuredScene = setScene();
        stage.setScene(configuredScene);
        stage.show();

        stage.setTitle("Nacho - Personal Cheesy Todo Tracker");
        stage.setResizable(false);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String nachoText = "";
        boolean toCloseWindow = false;
        boolean isReplyError = false;

        // Handle Bye Case
        if (Objects.equals(userText, "bye")) {
            isReplyError = false;
            nachoText = "Bye. Hope to see you again soon!\nClosing Chat in 3...2..1...";
            toCloseWindow = true;
        } else {
            // General Case -> Get Nacho's reply
            QueryResult result = nacho.handleQuery(userInput.getText());
            nachoText = result.getReply();
            isReplyError = result.checkIfError();
        }

        // Show message on screen
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getNachoDialog(nachoText, nachoImage, isReplyError)
        );

        userInput.clear();

        // If Time to end query -> Close stage
        if (toCloseWindow) {
            // Using sendButton to get stage context
            Stage stage = ((Stage) sendButton.getScene().getWindow());

            // Delay one second for user to view goodbye message
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }

    private Scene setScene() {
        // Styling Settings
        userInput = new TextField();
        userInput.setPrefWidth(INPUT_WIDTH);
        userInput.setPrefHeight(INPUT_HEIGHT);

        AnchorPane mainLayout = configAnchorPane();
        scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return scene;
    }

    private ScrollPane configScrollPane() {

        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        VBox dialogContainer = configDialogContainer();
        scrollPane.setContent(dialogContainer);
        scrollPane.getStyleClass().add("my-scrollPane");
        return scrollPane;
    }

    private AnchorPane configAnchorPane() {
        AnchorPane mainLayout = new AnchorPane();

        ScrollPane scrollPane = configScrollPane();
        Button sendButton = configSendButton();

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(MIN_WIDTH, MIN_HEIGHT);
        return mainLayout;
    }

    private VBox configDialogContainer() {
        dialogContainer = new VBox();
        dialogContainer.getStyleClass().add("my-dialogContainer");
        dialogContainer.setSpacing(20);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);


        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Show First Message
        dialogContainer.getChildren().add(
                DialogBox.getNachoDialog("Hello I'm Nacho\nWhat can I do for you?", nachoImage, false)
        );
        return dialogContainer;
    }

    private Button configSendButton() {
        sendButton = new Button("Send");
        sendButton.setPrefWidth(BUTTON_WIDTH);
        sendButton.setPrefHeight(INPUT_HEIGHT);

        // Handle User Inputs
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        return sendButton;
    }



}
