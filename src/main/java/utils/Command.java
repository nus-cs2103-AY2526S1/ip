package utils;

import java.util.HashMap;

import commands.CommandType;
import exceptions.ApunableException;
import models.ContactBook;
import models.TaskList;

/**
 * A class that stores the info extracted from user input. 
 */
public class Command {
    private CommandType commandType;
    private String firstParam;
    private HashMap<String, String> params;
    private boolean isExitAfter;

    public Command() {
        params = new HashMap<>();
    }

    public Command(CommandType cmd, String fstParam, HashMap<String, String> params) {
        assert cmd != null : "Invalid command type";
        
        commandType = cmd;
        firstParam = fstParam;
        this.params = params;
    }

    /**
     * Returns if this is an exit command, if yes the program will terminate after this command. 
     */
    public boolean isExit() {
        return isExitAfter;
    }

    /**
     * Executes the command. 
     */
    public void execute(TaskList tasks, ContactBook contacts, Ui ui, Storage storage, Storage contactStorage) throws ApunableException {
        if (commandType == CommandType.BYE) {
            try {
                ui.echo("Bye. Hope to see you again soon!");
                isExitAfter = true;
                storage.save(tasks);
                contactStorage.save(contacts);
            } catch (ApunableException ex) {
                ui.echo(ex.getMessage());
            }
        } else {
            commandType.getHandler().handle(tasks, contacts, ui, firstParam, params);
        }
    }
}
