package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.Contact;
import models.ContactBook;
import models.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Handles the {@code deletecontact} command from user and delete a contact with given name.
 */
public class ContactDelete implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactBook, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        String name = firstParam;

        assert !name.isEmpty() : "Erm... have you told me the person's name that you gonna delete?";

        HashMap<String, String> filterCriteria = new HashMap<>(1);
        filterCriteria.put("name", name);

        Integer[] index = contactBook.getIndices(filterCriteria);
        Contact contactToDelete = null;

        if (index.length == 0) {
            throw new ApunableException("Hmm... I couldn't find anyone with that name in your contacts.");
        } else {
            contactToDelete = contactBook.get(index[0]);
            contactBook.remove(index[0]);

            new Storage("data/contacts.txt").save(contactBook);
            
            ui.echo("Got it, I have deleted this contact: ");
            ui.echo("  " + contactToDelete.basicInfo());
        }
    }
}
