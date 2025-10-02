package com.neokortex;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.neokortex.commands.CommandHandler;
import com.neokortex.commands.factory.CompleteCommandFactory;
import com.neokortex.commands.parsers.CompleteCommandParser;
import com.neokortex.core.ApplicationContext;
import com.neokortex.core.Storage;
import com.neokortex.task.ListLoadWrapper;
import com.neokortex.ui.gui.Gui;
import com.neokortex.ui.gui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The entry point for the Graphical version of the program, NeoKortex. makes use of
 * this class for initialisation. The name of the graphical version of the {@link Chatbot}
 * is ACE, they facilitate user interaction.
 *
 * <p>
 * The graphical version of the {@code Chatbot} has the following features:
 * <ul>
 *     <li>Graphical interface for the user to interact with the {@code Chatbot}</li>
 *     <li>Adding different types of tasks to a task list</li>
 *     <li>Persistent Storage of TaskLists</li>
 *     <li>The ability to process the task list</li>
 * </ul>
 * </p>
 *
 * <p><b>Credit:</b></p>
 * <p>
 * This code and associated FXML heavily references the JavaFX tutorial, without which
 * I would not have been able to write this class. The tutorial can be found here:
 * <ul><li>https://se-education.org/guides/tutorials/javaFx.html</li></ul>
 * </p>
 *
 * <p>
 * Furthermore, this code was written under partial guidance from generative AI.
 * </p>
 *
 * @see ApplicationContext
 * @see CommandHandler
 * @see Gui
 * @see Storage
 */
public class Main extends Application {
    public static final String DEFAULT_LIST_STORAGE_DIRECTORY = "data";
    public static final String DEFAULT_TO_DO_LIST_FILENAME = "ToDoList";
    private static final String NAME = "ACE";

    private boolean hasLoadError = false;

    private User user;
    private Chatbot chatbot;

    /**
     * Initialises all the necessary items to run the program. The initialise function
     * called by when the program starts.
     */
    public void initialize() {
        Path path = Path.of(DEFAULT_LIST_STORAGE_DIRECTORY,
                DEFAULT_TO_DO_LIST_FILENAME);

        Storage storage = new Storage(path);
        ListLoadWrapper wrapper = storage.loadListFromStorage();

        ApplicationContext context =
                new ApplicationContext(
                        wrapper.getTaskList(),
                        storage);
        CompleteCommandParser parser = new CompleteCommandParser();
        CompleteCommandFactory factory = new CompleteCommandFactory(context);
        CommandHandler handler = new CommandHandler(parser, factory);

        this.chatbot = new Chatbot(NAME);
        this.chatbot.setCommandHandler(handler);

        this.chatbot.setProfilePicture(new Image(this.getClass().getResourceAsStream("/images/Ace.png")));
        this.user = new User("User", new Image(this.getClass().getResourceAsStream("/images/Anon.png")));

    }

    @Override
    public void start(Stage stage) {
        this.initialize();
        InputStream fontStream = getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf");
        if (fontStream != null) {
            Font customFont = Font.loadFont(fontStream, 20);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            Gui gui = new Gui(controller.getDialogueContainer());
            gui.setResponderProfilePicture(this.chatbot.getProfilePicture());
            gui.setSenderProfilePicture(this.user.getProfilePicture());
            controller.setCharacters(chatbot, user);
            controller.setGui(gui);
            this.chatbot.setUi(gui);
            this.chatbot.displayStartupMessage(this.hasLoadError);
            stage.setTitle("NeoKortex");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/NeoKortex Icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
