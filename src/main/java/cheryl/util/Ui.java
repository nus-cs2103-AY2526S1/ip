package cheryl.util;

import cheryl.task.Task;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Handles all interactions with the user (console side).
 * ASCII-clean to avoid encoding artefacts.
 */
public class Ui {
    private final Scanner scanner;
    private final PrintStream out;
    private StringBuilder lastOutputBuffer = new StringBuilder();

    public Ui() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        this.out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    public void showMessage(String message) {
        appendAndPrint(message);
    }

    public void showWelcome() {
        appendAndPrint("Hello! I'm Cheryl");
        appendAndPrint("What can I do for you?");
    }

    public void showGoodbye() {
        appendAndPrint("Bye! Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        appendAndPrint("OOPS!!! " + sanitize(errorMessage));
    }

    public void showTaskStatusChanged(Task task, boolean isMarked) {
        String head = isMarked
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        appendAndPrint(head);
        appendAndPrint("  " + sanitize(task.toString()));
    }

    /** Draws a divider line between messages (used in old versions). */
    public void showLine() {
        appendAndPrint("____________________________________________________________");
    }

    /** Clears the remembered last output (for GUI integration). */
    public void clearLastOutput() {
        lastOutputBuffer.setLength(0);
    }

    /** Returns accumulated output text for display in GUI. */
    public String getLastOutput() {
        return lastOutputBuffer.toString();
    }

    public void close() {
        try {
            scanner.close();
        } catch (Exception ignored) { }
    }

    // --- Internal helpers ---

    private void appendAndPrint(String msg) {
        String clean = sanitize(msg);
        lastOutputBuffer.append(clean).append(System.lineSeparator());
        out.println(clean);
    }

    private String sanitize(String msg) {
        if (msg == null) return "";
        return msg.replaceAll("[^\\x20-\\x7E\\n\\r\\t]", "");
    }
}

