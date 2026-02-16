package yang;

import javafx.application.Platform;
import yang.parser.Parser;
import yang.storage.Storage;
import yang.task.TaskList;
import yang.ui.Ui;

/**
 * Represents the main entry point for the task manager bot.
 * It processes user input, manages tasks, and handles command execution.
 */
public class Yang {

    private static final String COMMAND_EXIT = "bye";

    private final Storage storage = Storage.defaultStorage();
    private final Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    private boolean isExit(String input) {
        return input != null && COMMAND_EXIT.equalsIgnoreCase(input.trim());
    }
    /**
     * Returns Yang's response to the given user input for the GUI.
     *
     * @param input the user command
     * @return the response message to display
     */
    // Improved on my existing getResponse() about handling errors by consulting ChatGPT
    public String getResponse(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        if (COMMAND_EXIT.equalsIgnoreCase(input.trim())) {
            Platform.exit();
        }

        try {
            var res = Parser.apply(input, tasks);
            switch (res.type) {
            case ADDED, DELETED, MARKED, UNMARKED -> storage.save(tasks.asList());
            default -> { /* no-op */ }
            }
            return render(res);
        } catch (YangException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "☹ OOPS!!! " + e.getMessage();
        }
    }

    private String render(yang.parser.CommandResult r) {
        switch (r.type) {
        case LIST: {
            if (tasks.size() == 0) {
                return "No tasks at the moment!";
            }
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
            }
            return sb.toString().trim();
        }
        case ADDED:
            return "Perfect! I've added this task:\n  " + r.task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        case DELETED:
            return "Understood. I've removed this task:\n  " + r.task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        case MARKED:
            return "YAY! Task completed. You are doing a wonderful job!!\n  " + r.task;
        case UNMARKED:
            return "Understood, this task is not done yet:\n  " + r.task;
        case FOUND: {
            String kw = (r.keyword == null) ? "" : r.keyword.trim().toLowerCase();
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            int n = 0;
            for (int i = 0; i < tasks.size(); i++) {
                var t = tasks.get(i);
                if (t.toString().toLowerCase().contains(kw)) {
                    sb.append(++n).append(". ").append(t).append('\n');
                }
            }
            if (n == 0) {
                sb.append("(no matches)");
            }
            return sb.toString().trim();
        }
        default:
            return "I am sorry, not sure what that means.";
        }
    }
}
