package cat;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.layout.Region;




public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image meowImage = new Image(this.getClass().getResourceAsStream("/images/miao.png"));
    private Meow meow = new Meow();




    @Override
    public void start(Stage stage) {
        //Setting up required components

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("\uD83D\uDC08"); // Cat emoji
        sendButton.setStyle("-fx-background-color: orange; -fx-text-fill: black;");// orange button with black text/emoji

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //More code to be added here later
        //Formatting the window to look as expected

        stage.setTitle("Cat");
        //stage.setTitle("Cat");

        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(600.0);//400

        mainLayout.setPrefSize(400, 600);//400, 600

        scrollPane.setPrefSize(585, 535);// 385, 535
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);//325

        sendButton.setPrefWidth(55.0);// 55.0

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().add(
                DialogBox.getMeowDialog(Ui.showWelcome() + Ui.showAsk(), meowImage)
        );

        //More code to be added here later
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }



    private void handleUserInput() {
        String userText = userInput.getText();
        String meowText = meow.main(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText,userImage, 1),
                DialogBox.getMeowDialog(meowText, meowImage)
        );
        userInput.clear();
    }
}