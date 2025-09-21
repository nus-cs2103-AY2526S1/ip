package jeff.ui;

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

/* Code from this class was taken from the CS2103T/iP teaching materials and SE-EDU sample projects. */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/DaJeff.png"));
    private final GuiLogic logic = new GuiLogic();

    @Override
    public void start(Stage stage) {
        //Setting up required components

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

        //More code to be added here later
        stage.setTitle("Jeff the Chatbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String text = userInput.getText().trim();
        if (text.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(text, userImage));

        GuiLogic.Reply r = logic.handle(text);
        dialogContainer.getChildren().add(DialogBox.getBotDialog(r.message, jeffImage));

        userInput.clear();
        if (r.isExit) {
            javafx.application.Platform.exit();
        }

    }
}
