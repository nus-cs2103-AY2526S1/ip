package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the chatbot model <code>Alex</code>.
 * An <code>Alex</code> object manages the chatbot's task list, aliases, storage, and UI.
 * It loads saved data on startup and processes user input to produce chatbot responses.
 */
public class Alex {

    private static final String tasklistFilePath = "./data/alex.txt";
    private static final String aliasesListFilePath = "./data/alias.txt";

    private Storage storage;
    private TaskList taskList;
    private Alias aliases;
    private Ui ui;


    /**
     * Constructs an <code>Alex</code> chatbot.
     * Attempts to load saved task and alias data from storage. If the files are not found,
     * initializes with empty task list and alias set.
     */
    public Alex() {
        this.ui = new Ui();
        this.storage = new Storage(tasklistFilePath, aliasesListFilePath);

        // Ensure files exist
        createFileIfNotExist(tasklistFilePath);
        createFileIfNotExist(aliasesListFilePath);

        try {
            this.taskList = new TaskList(storage.loadTasklist());
            this.aliases = storage.loadAliases();
        } catch (FileNotFoundException e) {
            // Fallback to empty list/aliases if something went wrong
            this.taskList = new TaskList();
            this.aliases = new Alias();
        }
    }


    private void createFileIfNotExist(String filePath) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // create parent directories if missing
            }
            if (!file.exists()) {
                file.createNewFile(); // create empty file
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + filePath);
            e.printStackTrace();
        }
    }
    // Above method was done with the help of AI

    /**
     * Returns the chatbot's response to the specified user input.
     *
     * @param input User's input to the chatbot.
     * @return Chatbot's response to the input provided by the user.
     */
    public String getResponse(String input) {
        return ui.run(taskList, storage, input, aliases);
    }
}

// JavaDocs was done with the help of AI
