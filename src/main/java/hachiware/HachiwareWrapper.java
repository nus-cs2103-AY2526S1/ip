package hachiware;

public class HachiwareWrapper {
    private final TaskList tasks;
    private final StoreFile storage;
    private final Ui ui;
    private boolean isExit = false;

    public HachiwareWrapper(String filePath) {
        this.ui = new Ui();

        assert filePath != null : "filePath cannot be null";
        this.storage = new StoreFile(filePath);

        TaskList loaded;

        try {
            loaded = new TaskList(storage.load());
        } catch (HachiwareException e) {
            loaded = new TaskList();
        }
        this.tasks = loaded;
    }

    /**
     * Handles user input and returns Hachiware's response.
     *
     * @param input user input string
     * @return response string for GUI display
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String result = command.execute(tasks, ui, storage); // now returns String
            isExit = command.isExit();
            return result;
        } catch (HachiwareException e) {
            return ui.showError(e.getMessage()); // returns string instead of printing
        }
    }

    /** Returns welcome message for startup */
    public String getWelcome() {
        return ui.welcome(); // now returns string
    }

    public boolean isExit() {
        return isExit;
    }
}
