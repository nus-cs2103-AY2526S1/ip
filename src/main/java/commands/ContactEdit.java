package commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

import exceptions.ApunableException;
import models.Contact;
import models.ContactBook;
import models.Phone;
import models.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Handles the {@code editcontact} command from user and edit info of the contact with given name.
 */
public class ContactEdit implements ContactHandler {
    @Override
    public void handle(TaskList taskList, ContactBook contactBook, Ui ui,
                       String firstParam, HashMap<String, String> params) throws ApunableException {

        String name = firstParam;

        assert !name.isEmpty() : "What's the person's name that you want to edit? ";

        HashMap<String, String> filterCriteria = new HashMap<>(1);
        filterCriteria.put("name", name);

        Integer[] index = contactBook.getIndices(filterCriteria);
        Contact contactToEdit = null;

        if (index.length == 0) {
            throw new ApunableException("Opps! The name is not in the contact.");
        }

        // Retrieve the original contact
        Contact oldContact = contactBook.get(index[0]);
        Contact updatedContact = new Contact(oldContact);

        // Update only fields that are provided
        if (params.containsKey("name")) {
            updatedContact.setName(params.getOrDefault("name", oldContact.getName()));
        }
        if (params.containsKey("firstname")) {
            updatedContact.setFirstName(params.get("firstname"));
        }
        if (params.containsKey("lastname")) {
            updatedContact.setLastName(params.get("lastname"));
        }
        if (params.containsKey("phone")) {
            updatedContact.setPhoneNumbers(
                    Arrays.stream(params.get("phone").split(";")).map(Phone::new).toList()
            );
        }
        if (params.containsKey("address")) {
            updatedContact.setAddress(params.get("address"));
        }
        if (params.containsKey("org")) {
            updatedContact.setOrganization(params.get("org"));
        }
        if (params.containsKey("email")) {
            updatedContact.setEmails(
                    Arrays.asList(params.get("email").split(";"))
            );
        }
        if (params.containsKey("notes")) {
            updatedContact.setNotes(params.get("notes"));
        }
        if (params.containsKey("nickname")) {
            updatedContact.setNickName(params.get("nickname"));
        }
        if (params.containsKey("birthday")) {
            String birthdayStr = params.get("birthday");
            updatedContact.setBirthday(
                    LocalDate.parse(birthdayStr, DateTimeFormatter.ofPattern("dd MMM"))
            );
        }

        // Save changes back into the list
        contactBook.set(index[0], updatedContact);

        new Storage("data/contacts.txt").save(contactBook);

        ui.echo("Contact \"" + name + "\" has been updated successfully.");
    }

}
