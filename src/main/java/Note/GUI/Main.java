package Note.GUI;

import Note.ui.Note;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Note note = new Note();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainWindow controller = fxmlLoader.getController();
        controller.setNote(note);

        stage.setScene(scene);
        stage.setTitle("Note");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
