package iris;

/** Parses user input and executes corresponding commands **/
public class Parser {
    /** Parses the input command and executes the corresponding action **/
    public static String parse(String input, TaskList tasks, ContactList contacts, Ui ui, Storage storage, ContactStorage contactStorage) throws IrisException {
        validateInputs(input, tasks, ui, storage);

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye":
            return handleBye(ui);

        case "list":
            return handleList(tasks, ui);

        case "mark":
            return handleMark(parts, tasks, ui, storage, true);

        case "unmark":
            return handleMark(parts, tasks, ui, storage, false);

        case "todo":
        case "deadline":
        case "event":
            return handleAddTask(command, parts, tasks, ui, storage);

        case "delete":
            return handleDelete(parts, tasks, ui, storage);

        case "find":
            return handleFind(parts, tasks, ui);

        case "contact-add":
            return handleAddContact(parts, contacts, ui, contactStorage);

        case "contact-delete":
            return handleDeleteContact(parts, contacts, ui, contactStorage);

        case "contacts":
            return handleListContacts(contacts, ui);

        default:
            throw new IrisException("Unknown command.");
        }
    }

    /** Validates the inputs for parsing **/
    private static void validateInputs(String input, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (input == null || input.trim().isEmpty()) {
            throw new IrisException("Input cannot be empty.");
        }
    }

    /** Handles the 'bye' command **/
    private static String handleBye(Ui ui) {
        ui.showExit();
        String message = ui.getLastMessage(); // Capture the exit message before exiting
        System.exit(0);
        return message; // This line will never be reached, but is needed for compilation
    }

    /** Handles the 'list' command **/
    private static String handleList(TaskList tasks, Ui ui) {
        tasks.list(ui);
        return ui.getLastMessage();
    }

    /** Handles the 'mark' and 'unmark' commands **/
    private static String handleMark(String[] parts, TaskList tasks, Ui ui, Storage storage, boolean isMark) throws IrisException {
        CommandHandler.mark(parts, tasks, ui, storage, isMark);
        return ui.getLastMessage();
    }

    /** Handles adding a new task **/
    private static String handleAddTask(String command, String[] parts, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        CommandHandler.addTask(command, parts, tasks, ui, storage);
        return ui.getLastMessage();
    }

    /** Handles the 'delete' command **/
    private static String handleDelete(String[] parts, TaskList tasks, Ui ui, Storage storage) throws IrisException {
        CommandHandler.delete(parts, tasks, ui, storage);
        return ui.getLastMessage();
    }

    /** Handles the 'find' command **/
    private static String handleFind(String[] parts, TaskList tasks, Ui ui) throws IrisException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IrisException("Please provide a keyword to search.");
        }
        tasks.find(parts[1].trim(), ui);
        return ui.getLastMessage();
    }

    /** Handles adding a new contact **/
    private static String handleAddContact(String[] parts, ContactList contacts, Ui ui, ContactStorage contactStorage) throws IrisException {
        CommandHandler.addContact(parts, contacts, ui, contactStorage);
        return ui.getLastMessage();
    }

    /** Handles deleting a contact **/
    private static String handleDeleteContact(String[] parts, ContactList contacts, Ui ui, ContactStorage contactStorage) throws IrisException {
        CommandHandler.deleteContact(parts, contacts, ui, contactStorage);
        return ui.getLastMessage();
    }

    /** Handles listing all contacts **/
    private static String handleListContacts(ContactList contacts, Ui ui) {
        CommandHandler.listContacts(contacts, ui);
        return ui.getLastMessage();
    }
}
