package chash;

import chash.command.Command;
import chash.command.CommandParser;
import chash.exception.ChashException;
import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

//Imports
import java.io.IOException;

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

public class Chash {
    //Entrypoint
    public static void main(String[] args) {
        //Startup
        ChashUi ui = new ChashUi();
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
