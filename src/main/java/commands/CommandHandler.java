package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.TaskList;
import utils.Ui;

/**
 * Specifies the methods that each command handlers should contain. 
 */
public interface CommandHandler {
    /**
     * Handles the command typed by users. 
     * 
     * @param taskList a reference of taskList instance provided so commands involving adding tasks can modify it directly.
     * @param ui a ui that receive user input and return output, provided by other parts of code. 
     * @param firstParam the first parameter of the command(the one follow immediately after the command type). 
     * @param params the other parameters, extracted from "/param argument" to "param: argument" in HashMap. 
     * @throws ApunableException the only posible exception that will be thrown. 
     */
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException;
}
