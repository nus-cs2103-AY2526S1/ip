package winnie.ui;

import java.time.LocalDateTime;

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

import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.util.DateTimeUtil;
import winnie.uitool.GuiReader;
import winnie.uitool.GuiWriter;
import winnie.chatmessage.GoodByeMessage;
import winnie.chatmessage.GreetingMessage;
import winnie.chatmessage.TaskListMessage;
import winnie.chatmessage.TaskAddedMessage;
import winnie.chatmessage.MarkTaskMessage;
import winnie.chatmessage.UnmarkTaskMessage;
import winnie.chatmessage.DeleteTaskMessage;
import winnie.chatmessage.ErrorMessage;
import winnie.chatmessage.FoundTasksMessage;
import winnie.chatmessage.Readable;
import winnie.command.Command;
import winnie.command.VoidCommand;
import winnie.exception.WinnieException;
import winnie.parser.Parser;

public class Gui extends Application implements Ui {

    private GuiWriter guiWriter;
    private GuiReader guiReader;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList tasks;
    private Storage storage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image winnieImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Gui() {
        // Initialize later in start method when JavaFX components are created
    }

    public void setData(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public void start(Stage stage) {
        // Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        DialogBox dialogBox = DialogBox.getUserDialog("Hello!", userImage);
        dialogContainer.getChildren().addAll(dialogBox);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Formatting the window to look as expected
        stage.setTitle("Winnie");
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

        // Initialize GUI components now that they exist
        this.guiWriter = new GuiWriter(this.dialogContainer, this.winnieImage);
        this.guiReader = new GuiReader(this.dialogContainer, this.userInput, this.userImage);

        sendButton.setOnMouseClicked((event) -> {
            handleCommand();
        });

        userInput.setOnAction((event) -> {
            handleCommand();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Show welcome message
        showWelcome();
    }

    private void handleCommand() {
        Command command = readCommand();
        command.execute(tasks, this, storage);
        if (command.isExit()) {
            javafx.application.Platform.exit();
        }
    }

    @Override
    public void showTaskList(TaskList tasks) {
        guiWriter.write(new TaskListMessage(tasks));
    }

    @Override
    public void showTaskAdded(Task task, int taskCount) {
        guiWriter.write(new TaskAddedMessage(task, taskCount));
    }

    @Override
    public void showTaskMarked(Task task) {
        guiWriter.write(new MarkTaskMessage(task));
    }

    @Override
    public void showTaskUnmarked(Task task) {
        guiWriter.write(new UnmarkTaskMessage(task));
    }

    @Override
    public void showTaskDeleted(Task task, int taskCount) {
        guiWriter.write(new DeleteTaskMessage(task, taskCount));
    }

    @Override
    public void showError(String errorMessage) {
        guiWriter.write(new ErrorMessage(errorMessage));
    }

    @Override
    public void showFoundTasks(TaskList foundTasks) {
        guiWriter.write(new FoundTasksMessage(foundTasks));
    }

    @Override
    public void showGoodbye() {
        guiWriter.write(new GoodByeMessage());
    }

    @Override
    public Command readCommand() {
        Readable userInput = guiReader.read();
        try {
            return Parser.parse(userInput.getMessageContent().trim());
        } catch (WinnieException e) {
            showError(e.getMessage());
        }
        return new VoidCommand();
    }

    @Override
    public void showWelcome() {
        guiWriter.write(new GreetingMessage());
    }

    @Override
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    @Override
    public void showTaskSnoozed(Task task, LocalDateTime snoozeUntil) {
        String message = "Nice! I've snoozed this task:\n  " + task.toString() + "\nIt will reappear at: "
                + DateTimeUtil.formatForDisplay(snoozeUntil);
        guiWriter.write(new ErrorMessage(message));
    }
}
