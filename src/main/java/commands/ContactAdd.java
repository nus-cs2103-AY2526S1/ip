package commands;

import java.util.HashMap;

import exceptions.ApunableException;
import models.Contact;
import models.ContactBook;
import models.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Handles the {@code addcontact} command from user and add a new contact. Throws exception if name already in contact.
 */
public class ContactAdd implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactBook, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        if (firstParam.isEmpty()) {
            throw new ApunableException("Oops! You need to tell me the contact's name first.");
        }

        if (!params.containsKey("phone") || !params.containsKey("email") || !params.containsKey("address")) {
            throw new ApunableException(
                    "Hmm... I'm missing some details. Please include the phone, email, and address.");
        }

        params.put("name", firstParam);

        Contact contactToAdd = new Contact(params);

        if (contactBook.hasPerson(contactToAdd)) {
            throw new ApunableException("Looks like this name is already in your contact list!");
        }

        contactBook.add(contactToAdd);

        new Storage("data/contacts.txt").save(contactBook);

        ui.echo("got it, I have saved this contact: ");
        ui.echo("  " + contactToAdd.basicInfo());
    }
}
