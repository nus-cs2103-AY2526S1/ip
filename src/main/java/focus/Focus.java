package focus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * The main entry point for the Focus application.
 * Initializes storage, loads tasks, and runs the command loop.
 */
public class Focus {

    private final TaskList taskList;
    private final TaskStorage storage;
    private boolean exitRequested = false;
    private boolean lastCallWasFocusException = false;

    /**
     * Load tasks into Focus.
     */
    public Focus() {

        Ui ui = new Ui();
        this.storage = new TaskStorage("data/Focus.txt");
        TaskList tl;

        try {
            tl = storage.loadTasks();
        } catch (IOException e) {
            ui.showError("No stored task list found!");
            tl = new TaskList();
        }

        this.taskList = tl;

    }

    /**
     * Returns the greeting banner.
     *
     * @return Returns the greeting banner.
     */
    public String getGreeting() {
        return "    Hello! I'm Focus\n" + "    I am laser focused on serving you. Let me know how may I be of service?";
    }

    /**
     * Parse + run a command against the in-memory TaskList and persist if needed.
     * Returns whatever the respective command prints (captured from stdout).
     */
    public String getResponse(String input) {

        PrintStream originalOut = System.out;
        ByteArrayOutputStream capture = new ByteArrayOutputStream();
        PrintStream tempStream = new PrintStream(capture);
        System.setOut(tempStream);

        String fallbackMessage = null;

        this.lastCallWasFocusException = false;

        try {

            FocusCommand cmd = InputParser.parse(input, taskList.size());
            cmd.execute(taskList);
            if (cmd.isMutating()) {
                try {
                    storage.saveTasks(taskList);
                } catch (IOException ioe) {
                    fallbackMessage = "Error saving task list.";
                }
            }

            this.exitRequested = cmd.isExit();

        } catch (FocusException fe) {
            this.lastCallWasFocusException = true;
            fallbackMessage = fe.getMessage();
        } catch (Exception e) {
            fallbackMessage = "Unexpected error: " + e.getMessage();
        } finally {
            tempStream.flush();
            System.setOut(originalOut);
        }

        String printed = capture.toString().trim();
        if (!printed.isEmpty()) {
            return printed;
        }
        if (fallbackMessage != null) {
            return fallbackMessage;
        }

        this.lastCallWasFocusException = true;
        return "Something went wrong. Could not perform text I/O!";

    }

    /** Lets the controller close the app after an exit command. */
    public boolean checklastCallWasFocusException() {
        return this.lastCallWasFocusException;
    }

    /** Lets the controller close the app after an exit command. */
    public boolean isExitRequested() {
        return this.exitRequested;
    }

}
