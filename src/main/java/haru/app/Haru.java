package haru.app;

import java.io.IOException;
import java.util.stream.Stream;

import haru.command.AddDeadlineCommand;
import haru.command.AddEventCommand;
import haru.command.AddTagCommand;
import haru.command.AddToDoCommand;
import haru.command.Command;
import haru.command.CommandContext;
import haru.command.DeleteTaskCommand;
import haru.command.FilterTasksCommand;
import haru.command.FindTasksCommand;
import haru.command.GoodbyeCommand;
import haru.command.HelloCommand;
import haru.command.ListTasksCommand;
import haru.command.MarkTaskCommand;
import haru.command.UnmarkTaskCommand;
import haru.exception.EmptyCommandException;
import haru.exception.HaruException;
import haru.exception.UnknownCommandException;
import haru.model.TaskList;
import haru.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main application class for Haru.
 */
public class Haru extends Application {
    private static final String TASK_FILE_PATH = "tasks.ser";
    private CommandContext ctx;
    private Ui ui;
    private VBox chat;

    /**
     * Runs a command from user input.
     *
     * @param str the command string
     * @throws HaruException if command parsing or execution fails
     * @throws IOException   if IO error occurs
     */
    private void runCommand(String str) throws HaruException, IOException {
        assert str != null : "input cannot be null";
        assert ctx != null : "CommandContext must be initialized";
        String[] tokens = Stream.of(str.split(" "))
                .filter(t -> !t.isEmpty())
                .toArray(String[]::new);
        if (tokens.length == 0) {
            throw new EmptyCommandException();
        }
        String name = tokens[0];
        // IntelliJ IDEA auto formatting doesn't work for switch expressions
        // @formatter:off
        Command command = switch (name) {
        case "bye" -> new GoodbyeCommand(ctx);
        case "todo" -> new AddToDoCommand(ctx);
        case "deadline" -> new AddDeadlineCommand(ctx);
        case "event" -> new AddEventCommand(ctx);
        case "list" -> new ListTasksCommand(ctx);
        case "mark" -> new MarkTaskCommand(ctx);
        case "unmark" -> new UnmarkTaskCommand(ctx);
        case "delete" -> new DeleteTaskCommand(ctx);
        case "find" -> new FindTasksCommand(ctx);
        case "tag" -> new AddTagCommand(ctx);
        case "filter" -> new FilterTasksCommand(ctx);
        default -> throw new UnknownCommandException();
        };
        // @formatter:on
        command.parse(tokens);
        command.execute();
    }

    private void setStage(Stage stage) {
        assert javafx.application.Platform.isFxApplicationThread() : "Must be on FX thread";
        assert stage != null : "stage is required";
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/view/main.fxml")
        );
        assert loader.getLocation() != null : "/view/main.fxml not found on classpath";
        AnchorPane root;
        try {
            root = loader.load();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to load FXML", e);
        }

        haru.ui.UiController c = loader.getController();
        assert c != null : "UiController not wired in FXML";

        this.chat = c.getChat();
        assert this.chat != null : "chat VBox missing";
        assert c.getScroll() != null : "ScrollPane missing";
        assert c.getBottom() != null : "bottom pane missing";
        assert c.getSend() != null : "send button missing";
        assert c.getInput() != null : "input field missing";
        this.chat.setFillWidth(true);
        c.getScroll().setFitToWidth(true);

        this.chat.heightProperty().addListener((
                o, oldH, newH) -> c.getScroll().setVvalue(1.0)
        );

        c.getBottom().heightProperty().addListener((
                o, oldH, newH) -> AnchorPane.setBottomAnchor(c.getScroll(), newH.doubleValue())
        );

        c.getSend().setOnAction(event -> {
            handleInput(c.getInput().getText());
            c.getInput().clear();
        });
        c.getInput().setOnAction(event -> {
            handleInput(c.getInput().getText());
            c.getInput().clear();
        });

        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Haru");
        stage.setResizable(false);
        stage.show();
    }

    private void handleInput(String str) {
        assert ui != null : "Ui must be initialized";
        assert str != null : "input cannot be null";
        ui.showUserMessage(str);
        try {
            runCommand(str);
        } catch (HaruException | IOException e) {
            if (e instanceof HaruException) {
                ui.showHaruMessage(e.getMessage());
            } else {
                ui.showHaruMessage("Eh?! Something went wrong with reading/saving your file!");
            }
            ui.showHaruMessage("It's okay, you can try again~!");
        }
    }

    @Override
    public void start(Stage stage) {
        assert javafx.application.Platform.isFxApplicationThread() : "start() must be on FX thread";
        assert stage != null : "stage is required";
        this.setStage(stage);
        assert chat != null : "chat must be set by setStage()";

        TaskList taskList;
        try {
            taskList = TaskList.fromFile(TASK_FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            taskList = TaskList.empty(TASK_FILE_PATH);
        }
        ui = new Ui(chat);
        ctx = new CommandContext(taskList, ui);
        new HelloCommand(ctx).execute();
    }
}
