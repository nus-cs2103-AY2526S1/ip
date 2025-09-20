package dobby;

import dobby.task.Task;
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

import java.nio.file.Path;
import java.util.ArrayList;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dobbyImage = new Image(this.getClass().getResourceAsStream("/images/dobby.jpg"));
    private Storage storage;
    private TaskList taskList;

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
        storage = new Storage(Path.of("./data/dobby.txt"));
        taskList = new TaskList(storage.loadTasks());
        Dobby.init(storage, taskList);


        showWelcomeMessage();

        stage.show();
    }

    private void showWelcomeMessage() {
        showIntroMessage();
        showSavedTasks();
    }

    private void showIntroMessage() {
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
        dialogContainer.getChildren().add(new DialogBox(welcomeText, dobbyImage));
    }

    private void showSavedTasks() {
        if (taskList.size() > 0) {
            dialogContainer.getChildren().add(DialogBox.getDobbyDialog(
                    "Here are your saved tasks from previous sessions:", dobbyImage
            ));
            for (Task task : taskList.getTasks()) {
                dialogContainer.getChildren().add(DialogBox.getDobbyDialog(task.toString(), dobbyImage));
            }
        } else {
            dialogContainer.getChildren().add(DialogBox.getDobbyDialog(
                    "You don't have any saved tasks yet. Try adding one with 'todo <description>'!", dobbyImage
            ));
        }
    }



    private void handleUserInput() {
        String input = userInput.getText();

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);  // right
        String response = Dobby.getResponse(input);
        DialogBox dobbyDialog = DialogBox.getDobbyDialog(response, dobbyImage); // left

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

            double size = 50.0;
            displayPicture.setFitWidth(size);
            displayPicture.setFitHeight(size);

            Circle clip = new Circle(size / 2, size / 2, size / 2);
            displayPicture.setClip(clip);

            SnapshotParameters parameters = new SnapshotParameters();
            WritableImage image = displayPicture.snapshot(parameters, null);
            displayPicture.setClip(null);
            displayPicture.setImage(image);

            text.setWrapText(true);
            this.setAlignment(Pos.TOP_RIGHT); // default user alignment
            this.getChildren().addAll(text, displayPicture);
        }

        /** Flips the dialog box for the bot (left side) */
        public void flip() {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().clear();
            this.getChildren().addAll(displayPicture, text);
        }

        /** Factory method for user dialog (right) */
        public static DialogBox getUserDialog(String s, Image i) {
            return new DialogBox(s, i); // no flip, stays right
        }

        /** Factory method for bot dialog (left) */
        public static DialogBox getDobbyDialog(String s, Image i) {
            DialogBox db = new DialogBox(s, i);
            db.flip(); // flip to left
            return db;
        }
    }




}
