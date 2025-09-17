package piper;

import piper.parser.CommandType;
import piper.parser.Parser;
import piper.storage.Storage;
import piper.task.Deadline;
import piper.task.Event;
import piper.task.Task;
import piper.task.TaskList;
import piper.task.Todo;
import piper.ui.Ui;

/**
 * Entry point of the Piper application.
 * Initializes the UI, loads tasks from storage, and runs the REPL loop.
 * REPL loop accepts user commands until bye is entered.
 */
public class Piper {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "piper.txt";
    private static final String CHATBOT_NAME = "Piper";

    private final Ui ui = new Ui(CHATBOT_NAME);
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;

    /** Constructor for GUI */
    public Piper() {
        try {
            this.storage = new Storage(DATA_DIR, DATA_FILE);
            this.tasks = storage.load();
        } catch (PiperException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Processes a single line of user input and generates an appropriate response.
     * Acts as the central command handler for the Piper chatbot.
     * If the input is invalid or an error occurs, a descriptive error message is returned instead.
     *
     * @param userInput the raw user input string
     * @return a string containing Piper's reply, including task updates and error messages
     */
    public String run(String userInput) {
        String trimmed = userInput == null ? "" : userInput.trim();
        StringBuilder reply = new StringBuilder();
        try {
            Parser.ParsedString ps = Parser.parse(trimmed);
            CommandType cmd = ps.cmdType();
            assert cmd != null : "Parser must return a non-null command";
            String arg = ps.arg();

            int initSize = tasks.getSize();

            if (arg == null) {
                switch (cmd) {
                case BYE:
                    isExit = true;
                    reply.append(ui.farewellUser());
                    break;
                case LIST:
                    reply.append(ui.displayTasks(tasks));
                    break;
                default:
                    throw new PiperException(
                            "CHEEP CHEEP! I can't quite sing along with '"
                                    + trimmed + "'. Wanna try another command?");
                }
            } else {
                switch (cmd) {
                case MARK:
                case UNMARK: {
                    try {
                        int taskNumber = Parser.parseIndex(arg);
                        int index = taskNumber - 1;
                        Task task = tasks.getTask(index);
                        if (cmd == CommandType.MARK) {
                            task.markDone();
                            assert "X".equals(task.getStatusIcon()) : "Task should be marked done";
                        } else {
                            task.markUndone();
                            assert " ".equals(task.getStatusIcon()) : "Task should be marked undone";
                        }
                        saveToStorage();
                        reply.append(ui.showTaskStatus(task));
                    } catch (IndexOutOfBoundsException ex) {
                        throw new PiperException(
                                "PEEP! That task flew out of the nest. "
                                        + "Please check using 'list' to see which tasks are home!"
                        );
                    }
                    break;
                }
                case DELETE: {
                    try {
                        int taskNumber = Parser.parseIndex(arg);
                        int index = taskNumber - 1;
                        Task task = tasks.getTask(index);
                        tasks.deleteTask(index);
                        saveToStorage();
                        assert tasks.getSize() == initSize - 1 : "Task deletion should decrease number of tasks by 1";
                        saveToStorage();
                        reply.append(ui.showDeletedTask(task))
                                .append(System.lineSeparator())
                                .append(ui.showTasksSize(tasks));
                    } catch (IndexOutOfBoundsException ex) {
                        throw new PiperException(
                                "PEEP! Bad egg. Please check using 'list' to see which tasks are home!");
                    }
                    break;
                }
                case TODO: {
                    Task task = new Todo(arg);
                    tasks.addTask(task);
                    saveToStorage();
                    assert tasks.getSize() == initSize + 1 : "New task addition should increase number of tasks by 1";
                    saveToStorage();
                    reply.append(ui.showAddedTask(task))
                            .append(System.lineSeparator())
                            .append(ui.showTasksSize(tasks));
                    break;
                }
                case DEADLINE: {
                    Parser.DeadlineArgs da = Parser.parseDeadlineArgs(arg);
                    Task task = new Deadline(da.description(), da.by());
                    tasks.addTask(task);
                    saveToStorage();
                    assert tasks.getSize() == initSize + 1 : "New task addition should increase number of tasks by 1";
                    saveToStorage();
                    reply.append(ui.showAddedTask(task))
                            .append(System.lineSeparator())
                            .append(ui.showTasksSize(tasks));
                    break;
                }
                case EVENT: {
                    Parser.EventArgs ea = Parser.parseEventArgs(arg);
                    Task task = new Event(ea.description(), ea.from(), ea.to());
                    tasks.addTask(task);
                    saveToStorage();
                    assert tasks.getSize() == initSize + 1 : "New task addition should increase number of tasks by 1";
                    saveToStorage();
                    reply.append(ui.showAddedTask(task))
                            .append(System.lineSeparator())
                            .append(ui.showTasksSize(tasks));
                    break;
                }
                case FIND: {
                    TaskList matches = tasks.find(arg);
                    assert matches != null : "Function find(String kw) should not return null";
                    reply.append(ui.displayMatchingTasks(matches));
                    break;
                }
                case SNOOZE: {
                    Parser.SnoozeArgs sa = Parser.parseSnoozeArgs(arg);
                    int index = sa.index - 1;
                    try {
                        Task task = tasks.getTask(index);
                        if (task instanceof Deadline) {
                            Deadline d = (Deadline) task;
                            java.time.LocalDate date = java.time.LocalDate.parse(
                                    d.toSerializedLine().split(" \\| ", 4)[3]); // current /by
                            // substrings: D | doneField | description | byField
                            java.time.LocalDate updatedDate = date.plusDays(sa.days);
                            d.updateByDate(updatedDate.toString());
                        } else if (task instanceof Event) {
                            Event e = (Event) task;
                            String[] substrings = e.toSerializedLine().split(" \\| ", 6);
                            // substrings: E | doneField | description | fromField | toField
                            java.time.LocalDate from = java.time.LocalDate.parse(substrings[3]);
                            java.time.LocalDate to = java.time.LocalDate.parse(substrings[4]);
                            e.updateRange(from.plusDays(sa.days).toString(), to.plusDays(sa.days).toString());
                        } else {
                            throw new PiperException("PEEP! Only deadlines or events can be snoozed.");
                        }
                        saveToStorage();
                        reply.append(ui.showSnoozedTask(task));
                        break;
                    } catch (java.time.format.DateTimeParseException e) {
                        throw new PiperException("I only understand ISO dates...");
                    } catch (IndexOutOfBoundsException e) {
                        throw new PiperException(
                                "PEEP! That task flew out of the nest. "
                                        + "Please check using 'list' to see which tasks are home!"
                        );
                    }
                }
                default:
                    throw new PiperException(
                            "CHEEP CHEEP! I can't quite sing along with '"
                                    + trimmed + "'. Wanna try another command?");
                }
            }
        } catch (PiperException e) {
            reply.append(ui.showError(e.getMessage()));
        }
        return reply.toString();
    }

    public String getGreeting() {
        return ui.greetUser();
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Saves tasks to storage if storage has been set up.
     */
     private void saveToStorage() throws PiperException {
         if (storage != null) {
             storage.saveAll(tasks);
         }
     }
     
}
