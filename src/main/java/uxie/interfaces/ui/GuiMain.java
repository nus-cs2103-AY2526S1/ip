package uxie.interfaces.ui;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uxie.Uxie;
import uxie.interfaces.ui.fxelements.MainWindow;

/**
 * Main class of JavaFX GUI.
 */
public class GuiMain extends Application {

    /** Pokemon font used in dialog. */
    private static Optional<Font> pokemonFont;

    private Uxie uxie = new Uxie();

    /**
     * Initializes GUI.
     * Loads custom font for dialog from .otf file.
     */
    @Override
    public void init() {
        // load and apply font in java instead of css because supposedly something's broken with css fonts and javafx

        // wrapping font in an optional because I'm afraid something terrible may happen with loadFont
        pokemonFont = Optional.of(
                Font.loadFont(GuiMain.class.getResourceAsStream(
                        "/fonts/pokemon-gen-4-regular.otf"
                ), 14));
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource(
                    "/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Uxie");
            fxmlLoader.<MainWindow>getController().setUxie(uxie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns Optional containing font used in dialog, or null.
     */
    public static Optional<Font> getFont() {
        return pokemonFont;
    }

}
