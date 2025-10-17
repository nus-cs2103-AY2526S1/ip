package iris;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Manages a list of contacts **/
public class ContactList {
    private List<Contact> contacts = new ArrayList<>();

    /** Add a new contact */
    public void add(Contact contact) {
        assert contact != null : "Contact cannot be null";
        contacts.add(contact);
    }

    /** Delete a contact by index (0-based) */
    public Contact delete(int index) throws IrisException {
        if (index < 0 || index >= contacts.size()) {
            throw new IrisException("Invalid contact number.");
        }
        return contacts.remove(index);
    }

    /** List all contacts */
    public void list(Ui ui) {
        assert ui != null : "Ui must not be null";
        if (contacts.isEmpty()) {
            ui.showMessage("No contacts in the list.");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are your contacts:\n");
        for (int i = 0; i < contacts.size(); i++) {
            sb.append(i + 1).append(". ").append(contacts.get(i)).append("\n");
        }
        ui.showMessage(sb.toString().trim());
    }

    /** Get the number of contacts */
    public int size() {
        return contacts.size();
    }

    /** Get all contacts */
    public List<Contact> getAll() {
        return new ArrayList<>(contacts);
    }

    /** Load contacts from storage */
    public static ContactList fromStorage(ContactStorage storage) throws IOException {
        ContactList list = new ContactList();
        list.contacts.addAll(storage.load());
        return list;
    }
}
