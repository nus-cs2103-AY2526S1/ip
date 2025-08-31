package piper;

import piper.parser.Parser;
import piper.storage.Storage;
import piper.ui.Ui;
import piper.task.TaskList;
import piper.task.Task;
import piper.task.Todo;
import piper.task.Deadline;
import piper.task.Event;

/**
 * Entry point of the Piper application.
 * Initializes the UI, loads tasks from storage, and runs the REPL loop.
 * REPL loop accepts user commands until bye is entered.
 */
public class Piper {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "piper.txt";

    /**
     * Launches the application.
     *
     * @param args command-line arguments.
     * @throws PiperException if a recoverable application error occurs.
     */
    public static void main(String[] args) throws PiperException {
        final String CHATBOT_NAME = "Piper";
        Ui ui = new Ui(CHATBOT_NAME);
        TaskList tasks;
        boolean exit = false;

        Storage storage = null;
        try {
            storage = new Storage(DATA_DIR, DATA_FILE);
            tasks = storage.load();
        } catch (PiperException e) {
            ui = new Ui(CHATBOT_NAME);
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }

        ui.greetUser();

        while (!exit) {
            // user is active
            String userInput = ui.read();
            userInput = userInput.trim();

            try {
                Parser.ParsedString ps = Parser.parse(userInput);
                String cmd = ps.cmd;
                String arg = ps.arg;

                if (arg == null) {
                    if (cmd.equals("bye")) {
                        // user is inactive
                        ui.farewellUser();
                        exit = true;
                    } else if (cmd.equals("list")) {
                        // list all tasks
                        ui.displayTasks(tasks);
                    }
                } else {
                    if (cmd.equals("mark") || cmd.equals("unmark")) {
                        // mark task as done or undone

                        try {
                            int taskNumber = Parser.parseIndex(arg);
                            int index = taskNumber - 1; // task list starts from index 1 but array list starts from index 0
                            Task task = tasks.getTask(index);

                            switch (cmd) {
                            case "mark":
                                task.markDone();
                                break;
                            case "unmark":
                                task.markUndone();
                                break;
                            }

                            storage.saveAll(tasks);
                            ui.showTaskStatus(task);
                        } catch (IndexOutOfBoundsException e) {
                            // task index is outside of array range
                            throw new PiperException("PEEP! That task flew out of the nest. Please check using 'list' to see which tasks are home!");
                        }
                    } else if (cmd.equals("delete")) {
                        // delete task
                        try {
                            int taskNumber = Parser.parseIndex(arg);
                            int index = taskNumber - 1;
                            Task task = tasks.getTask(index);

                            tasks.deleteTask(index);
                            storage.saveAll(tasks);
                            ui.showDeletedTask(task);
                            ui.showTasksSize(tasks);
                        } catch (IndexOutOfBoundsException e) {
                            throw new PiperException("PEEP! Bad egg. Please check using 'list' to see which tasks are home!");
                        }
                    } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                        // add new task
                        Task task = null;

                        switch (cmd) {
                        case "todo":
                            task = new Todo(arg);
                            break;
                        case "deadline":
                            Parser.DeadlineArgs da = Parser.parseDeadlineArgs(arg);
                            task = new Deadline(da.description, da.by);
                            break;
                        case "event":
                            Parser.EventArgs ea = Parser.parseEventArgs(arg);
                            task = new Event(ea.description, ea.from, ea.to);
                            break;
                        }

                        tasks.addTask(task);
                        storage.saveAll(tasks);
                        ui.showAddedTask(task);
                        ui.showTasksSize(tasks);
                    } else if (cmd.equals("find")) {
                        // find tasks that contain keyword
                        String keyword = arg;
                        TaskList matches = tasks.find(keyword);
                        ui.displayMatchingTasks(matches);
                    } else {
                        // user input is an unrecognisable string
                        throw new PiperException("CHEEP CHEEP! I can't quite sing along with '" + userInput + "'. Wanna try another command?");
                    }
                }
            } catch (PiperException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

}