package models;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ApunableException;

/**
 * Stores names added by user.
 */
public class ContactBook implements Savable {
    public ArrayList<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
    }

    /**
     * Creates a {@code Contact} object from the provided parameter arguments.
     *
     * @param paramArgs a map containing the contact details as key-value pairs.
     * @return a {@code Contact} created from the given parameters.
     * @throws ApunableException if invalid or incomplete details are provided.
     */
    private Contact createContactFromParams(HashMap<String, String> paramArgs) throws ApunableException {
        return new Contact(paramArgs);
    }

    /**
     * Constructs a {@code ContactBook} by lines read from file.
     * Each contact entry is separated by a blank line.
     *
     * @param contactStrs a list of lines from file representing contact details in {@code key: value} format.
     * @throws ApunableException if one or more contacts fail to parse correctly.
     */
    public ContactBook(ArrayList<String> contactStrs) throws ApunableException {
        contacts = new ArrayList<>();
        HashMap<String, String> paramArgs = new HashMap<>();
        ArrayList<Integer> errorSavedContacts = new ArrayList<>();

        for (int i = 0; i < contactStrs.size(); i++) {
            if (contactStrs.get(i).contains(":")) {
                String[] argument = contactStrs.get(i).split(":", 2);
                if (argument.length >= 2) {
                    paramArgs.put(argument[0].trim(), argument[1].trim());
                }
            }
            if (contactStrs.get(i).isBlank() || i == contactStrs.size() - 1) {
                if (!paramArgs.isEmpty()) {
                    try {
                        contacts.add(createContactFromParams(paramArgs));
                    } catch (ApunableException e) {
                        errorSavedContacts.add(i + 1);
                    } finally {
                        paramArgs.clear();
                    }
                }
            }
        }

        if (!errorSavedContacts.isEmpty()) {
            throw new ApunableException("Error parsing contacts at indices: " +
                    String.join(", ", errorSavedContacts.stream().map(String::valueOf).toList()));
        }
    }

    /**
     * Checks whether the {@code ContactBook} already contains a contact
     * with the same name as the specified {@code Contact}.
     *
     * @param contact the contact to check for.
     * @return true if a contact with the same name exists, false otherwise.
     */
    public boolean hasPerson (Contact  contact) {
        return contacts.stream().anyMatch(c -> c.getName().equals(contact.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ContactBook that)) {
            return false;
        }

        return contacts.equals(that.contacts);
    }

    @Override
    public String getFormattedString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < contacts.size(); i++) {
            builder.append(i + 1).append(".\n")
                    .append(contacts.get(i).getFormattedString())
                    .append("\n");
        }

        return builder.toString();
    }

    /**
     * Returns the contact at specific 0-based {@code index}.
     *
     * @param index index of the name to be retrieved(0-based).
     */
    public Contact get(int index) {
        return contacts.get(index);
    }

    /**
     * Sets the contact at specific 0-based {@code index}.
     *
     * @param index index of the contact to be updated(0-based).
     * @param toUpdate contact to be updated to.
     */
    public Contact set(int index, Contact toUpdate) {
        return contacts.set(index, toUpdate);
    }

    /**
     * @return the number of names in this {@code contactList}.
     */
    public int size() {
        return contacts.size();
    }

    /**
     * Adds a new name into this {@code contactList}.
     *
     * @param name name to be added.
     */
    public void add(Contact name) {
        contacts.add(name);
    }

    /**
     * Removes a name from this {@code contactList}.
     *
     * @param index index of name to be removed.
     */
    public void remove(int index) {
        contacts.remove(index);
    }

    /**
     * Returns the indices of all contacts that match the given search criteria.
     *
     * @param criteria a map of contact fields and their desired values.
     * @return an array of integer indices corresponding to matching contacts.
     */
    public Integer[] getIndices(HashMap<String, String> criteria) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).fit(criteria)) {
                indices.add(i);
            }
        }

        return indices.toArray(new Integer[0]);
    }
}
