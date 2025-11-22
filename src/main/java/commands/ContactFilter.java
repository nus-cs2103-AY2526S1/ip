package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code filtercontact} command from user and delete a contact with given name.
 */
public class ContactFilter implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactList, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        Integer[] indices = contactList.getIndices(params);

        if (indices.length == 0) {
            ui.echo("There is no one in the contact fulfilling the criteria.");
        }

        for (int index : indices) {
            ui.echo(contactList.get(index).basicInfo());
        }
    }
}
