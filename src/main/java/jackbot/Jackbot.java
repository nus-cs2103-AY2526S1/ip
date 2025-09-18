package jackbot;

import jackbot.task.Deadline;
import jackbot.task.Event;
import jackbot.task.Task;
import jackbot.task.Todo;

import java.util.List;

public class Jackbot {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    public Jackbot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();

        try {
            List<Task> loaded = storage.load();
            this.tasks = new TaskList(loaded);
            if (!loaded.isEmpty()) {
                ui.showInfo("Previously saved task file detected. Loading task list from file...");
            }
        } catch (JackbotException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(); // start empty if load failed
        }
    }

    public void run() {
        ui.showWelcome();

        boolean exit = false;
        while (!exit && ui.hasNextLine()) {
            String input = ui.readLine();

            try {
                Parser.Result r = parser.parse(input);

                switch (r.type) {
                    case BYE:
                        exit = true;
                        break;

                    case LIST:
                        ui.showList(tasks.asList());
                        break;

                    case MARK: {
                        Task t = tasks.get(r.index);
                        if (t.isDone()) {
                            throw new JackbotException("Task is already marked");
                        }
                        t.mark();
                        storage.save(tasks.asList());
                        ui.showMarked(t);
                        break;
                    }

                    case UNMARK: {
                        Task t = tasks.get(r.index);
                        if (!t.isDone()) {
                            throw new JackbotException("Task is already unmarked");
                        }
                        t.unmark();
                        storage.save(tasks.asList());
                        ui.showUnmarked(t);
                        break;
                    }

                    case DELETE: {
                        Task removed = tasks.delete(r.index);
                        storage.save(tasks.asList());
                        ui.showDeleted(removed, tasks.size());
                        break;
                    }

                    case TODO: {
                        ensureNotEmpty(r.text, "Task description cannot be empty");
                        Task t = new Todo(r.text);
                        tasks.add(t);
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }

                    case DEADLINE: {
                        ensureNotEmpty(r.text, "Task description cannot be empty");
                        Task t = new Deadline(r.text);
                        tasks.add(t);
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }

                    case EVENT: {
                        ensureNotEmpty(r.text, "Task description cannot be empty");
                        Task t = new Event(r.text);
                        tasks.add(t);
                        storage.save(tasks.asList());
                        ui.showAdded(t, tasks.size());
                        break;
                    }

                    default:
                        throw new JackbotException("Command doesn't exist");
                }

            } catch (JackbotException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }

        ui.showGoodbye();
    }

    private void ensureNotEmpty(String s, String msg) throws JackbotException {
        if (s == null || s.trim().isEmpty()) throw new JackbotException(msg);
    }

    public static void main(String[] args) {
        new Jackbot("./tasks.txt").run();
    }
}
