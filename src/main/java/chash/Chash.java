package chash;

import chash.command.Command;
import chash.exception.ChashException;
import chash.parser.CommandParser;
import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashConsole;
import chash.ui.ChashUi;
import chash.ui.gui.MainWindow;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
Dependancy Priority:
ChashException.java CommandTypeEnum.java Task.java TaskDateParser.java ChashUi.java
Todo.java Deadline.java Event.java TaskList.java
TaskParser.java 
ChashDb.java 
Command.java 
ExitCommand.java ListCommand.java MarkCommand.java DeleteCommand.java AddCommand.java
CommandParser.java
Chash.java
*/

/** Main entry point for CHASH application. */
public class Chash extends Application {
    //If need to run anything before javafx start
    /*@Override
    public void init() {}*/

    //JavaFX entrypoint
    @Override
    public void start(Stage stage) {
        assert stage != null;

        AnchorPane ap = new MainWindow();
        Scene scene = new Scene(ap);
        stage.setScene(scene);

        //Configure stage window
        stage.setTitle("Crysis Heir Activity Sentre Hepdesk");
        stage.setMinHeight(220);
        stage.setMinWidth(417);

        stage.show();
    }

    /** 
     * CLI only application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        //Startup
        ChashUi ui = new ChashConsole();
        ChashDb db = new ChashDb();
        TaskList tasks;
        try {
            tasks = new TaskList(db.loadDb(ui));
        } catch (IOException ex) {
            tasks = new TaskList();
            ui.printErr(ex.getMessage());
        }

        //Start Crysis Heir Activity Sentre Hepdesk (CHASH)
        ui.printWelcome();

        //Work loop
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readLine();
            try {
                Command cmd = CommandParser.parse(fullCommand);
                cmd.execute(tasks, ui, db);
                isExit = cmd.isExit();
            } catch (ChashException ex) {
                ui.printErr(ex.getMessage());
                continue;
            }
        }
    }
}
