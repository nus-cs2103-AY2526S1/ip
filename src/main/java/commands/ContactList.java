package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.ContactBook;
import models.TaskList;
import utils.Ui;

/**
 * Handles the {@code listcontact} command from user, and either list out all the contacts,
 * or give detailed information of a person. If {@code /a} is supplemented, all info of a person will be listed.
 */
public class ContactList implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactBook, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        if (contactBook.size() == 0) {
            ui.echo("There is no contact in your list");
        } else if (!firstParam.isEmpty()) {
            HashMap<String, String> criteria = new HashMap<>();
            criteria.put("name", firstParam);

            Integer[] index = contactBook.getIndices(criteria);
            if (index.length == 0) {
                throw new ApunableException("No such name in your list");
            }

            if (params.containsKey("a")) {
                ui.echo(contactBook.get(index[0]).allInfo());
            } else {
                ui.echo(contactBook.get(index[0]).detailedInfo());
            }
        } else {
            ui.echo("Here are the list of contacts: ");

            for (int i = 0; i < contactBook.size(); i++) {
                ui.echo(String.format("%s\n\n", contactBook.get(i).basicInfo()));
            }
        }
    }
}
