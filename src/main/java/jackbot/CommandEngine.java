package jackbot;

import java.util.ArrayList;
import java.util.List;

import jackbot.task.Deadline;
import jackbot.task.Event;
import jackbot.task.Task;
import jackbot.task.Todo;

/**
 * Central command engine shared by CLI and JavaFX UI.
 * Parses a line, executes it against the task list/storage, and returns messages + exit flag.
 */
public class CommandEngine {

    /** Result returned to UIs after processing one input line. */
    public static final class Response {
        /** Messages to display to the user, in order. */
        public final List<String> messages;
        /** Whether the app should exit after this command. */
        public final boolean exit;

        /**
         * Creates a new response object.
         *
         * @param messages messages to display to the user (never {@code null})
         * @param exit     whether the application should exit
         */
        public Response(List<String> messages, boolean exit) {
            this.messages = messages;
            this.exit = exit;
        }
    }

    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a command engine bound to the given core components.
     *
     * @param storage persistent storage used to save task lists
     * @param tasks   in-memory task list to be mutated by commands
     * @param parser  parser used to interpret raw user input
     */
    public CommandEngine(Storage storage, TaskList tasks, Parser parser) {
        assert storage != null : "storage";
        assert tasks != null : "tasks";
        assert parser != null : "parser";
        this.storage = storage;
        this.tasks = tasks;
        this.parser = parser;
    }

    /**
     * Processes a single input line and returns messages for the UI, plus an exit flag.
     *
     * @param rawInput user input line (may be blank)
     * @return response with messages and exit flag
     */
    public Response process(String rawInput) {
        List<String> out = new ArrayList<>();
        boolean exit = false;

        try {
            Parser.Result r = parser.parse(rawInput);

            assert r != null : "parser result must not be null";

            switch (r.type) {
            case BYE: {
                exit = true;
                out.add("Bye. Hope to see you again soon!");
                break;
            }

            case LIST: {
                StringBuilder sb = new StringBuilder("Your previous entries:");
                List<Task> list = tasks.asList();
                for (int i = 0; i < list.size(); i++) {
                    sb.append("\n").append(i + 1).append(". ").append(list.get(i));
                }
                out.add(sb.toString());
                break;
            }

            case MARK: {
                Task t = tasks.get(r.index);
                if (t.isDone()) {
                    throw new JackbotException("Task is already marked");
                }
                t.mark();
                storage.save(tasks.asList());
                out.add("Nice, I've marked this task as done:\n  " + t);
                break;
            }

            case UNMARK: {
                Task t = tasks.get(r.index);
                if (!t.isDone()) {
                    throw new JackbotException("Task is already unmarked");
                }
                t.unmark();
                storage.save(tasks.asList());
                out.add("OK, I've marked this task as not done:\n  " + t);
                break;
            }

            case DELETE: {
                Task removed = tasks.delete(r.index);
                storage.save(tasks.asList());
                out.add("Noted. I've removed this task:\n  " + removed
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            }

            case TODO: {
                ensureNotEmpty(r.text, "Task description cannot be empty");
                Task t = new Todo(r.text);
                tasks.add(t);
                storage.save(tasks.asList());
                out.add("Got it. I've added this task\n  " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            }

            case DEADLINE: {
                ensureNotEmpty(r.text, "Task description cannot be empty");
                Task t = new Deadline(r.text);
                tasks.add(t);
                storage.save(tasks.asList());
                out.add("Got it. I've added this task\n  " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            }

            case EVENT: {
                ensureNotEmpty(r.text, "Task description cannot be empty");
                Task t = new Event(r.text);
                tasks.add(t);
                storage.save(tasks.asList());
                out.add("Got it. I've added this task\n  " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            }

            case FIND: {
                ensureNotEmpty(r.text, "Search keyword cannot be empty");
                List<Task> found = tasks.find(r.text);
                StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
                for (int i = 0; i < found.size(); i++) {
                    sb.append("\n").append(i + 1).append(". ").append(found.get(i));
                }
                if (found.isEmpty()) {
                    sb.append("\n(none)");
                }
                out.add(sb.toString());
                break;
            }

            default:
                throw new JackbotException("Command doesn't exist");
            }

        } catch (JackbotException e) {
            out.add("ERROR: " + e.getMessage());
        } catch (Exception e) {
            out.add("Unexpected error: " + e.getMessage());
        }

        return new Response(out, exit);
    }

    /**
     * Ensures a string is not null/blank.
     *
     * @param s   the string to check
     * @param msg error message for the thrown exception
     * @throws JackbotException if {@code s} is null or blank
     */
    private void ensureNotEmpty(String s, String msg) throws JackbotException {
        if (s == null || s.trim().isEmpty()) {
            throw new JackbotException(msg);
        }
    }
}
