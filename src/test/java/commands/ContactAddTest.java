package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import exceptions.ApunableException;
import models.Contact;
import models.ContactBook;
import models.TaskList;
import utils.Ui;

public class ContactAddTest {
    @Test
    public void handle_allFieldPresent_success() throws ApunableException {
        TaskList taskList = new TaskList();
        ContactBook contactList = new ContactBook();
        Ui ui = new Ui();

        Contact contact = new ContactBuilder().build();

        ContactBook expectedContactList = new ContactBook();
        expectedContactList.add(contact);

        ContactAdd contactAdd = new ContactAdd();
        contactAdd.handle(taskList, contactList, ui, ContactBuilder.DEFAULT_NAME, new ContactBuilder().params);

        assertEquals(expectedContactList, contactList);
    }
}