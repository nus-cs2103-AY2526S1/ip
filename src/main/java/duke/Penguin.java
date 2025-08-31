package duke;
/**
 * Penguin chatbot that manages a list of user tasks.
 * Uses OOP design with separate classes for UI, Storage, Parser, and TaskList.
 */

public class Penguin {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Penguin instance with the specified file path.
     * @param filePath The path to the data file
     */
    public Penguin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PenguinException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot.
     */
    public void run() {
        ui.showWelcome();
        
        while (ui.hasNextCommand()) {
            try {
                String input = ui.readCommand();
                Parser.Command command = Parser.parse(input);
                
                switch (command.type) {
                    case BYE:
                        ui.showGoodbye();
                        ui.close();
                        return;
                        
                    case LIST:
                        ui.showTaskList(tasks);
                        break;
                        
                    case MARK:
                        handleMarkCommand(command.taskNumber);
                        break;
                        
                    case UNMARK:
                        handleUnmarkCommand(command.taskNumber);
                        break;
                        
                    case TODO:
                        handleTodoCommand(command.description);
                        break;
                        
                    case DEADLINE:
                        handleDeadlineCommand(command.description, command.deadline);
                        break;
                        
                    case EVENT:
                        handleEventCommand(command.description, command.from, command.to);
                        break;
                        
                    case INVALID:
                        ui.showInvalidTask();
                        break;
                }
            } catch (PenguinException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Handles the mark command.
     * @param num The task number as string
     * @throws PenguinException if there's an error
     */
    private void handleMarkCommand(String num) throws PenguinException {
        try {
            int idx = Integer.parseInt(num) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.markTask(idx);
                storage.save(tasks.getTasks());
                ui.showTaskMarked(task);
            } else {
                ui.showInvalidTask();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTask();
        }
    }

    /**
     * Handles the unmark command.
     * @param num The task number as string
     * @throws PenguinException if there's an error
     */
    private void handleUnmarkCommand(String num) throws PenguinException {
        try {
            int idx = Integer.parseInt(num) - 1;
            if (tasks.isValidIndex(idx)) {
                Task task = tasks.unmarkTask(idx);
                storage.save(tasks.getTasks());
                ui.showTaskUnmarked(task);
            } else {
                ui.showInvalidTask();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTask();
        }
    }

    /**
     * Handles the todo command.
     * @param desc The task description
     * @throws PenguinException if there's an error
     */
    private void handleTodoCommand(String desc) throws PenguinException {
        if (desc.isEmpty()) {
            throw new PenguinException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(desc);
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Handles the deadline command.
     * @param description The task description
     * @param deadline The deadline string
     * @throws PenguinException if there's an error
     */
    private void handleDeadlineCommand(String description, String deadline) throws PenguinException {
        if (description.isEmpty()) {
            throw new PenguinException("The description of a deadline cannot be empty.");
        }
        try {
            Task task = new Deadline(description, deadline);
            tasks.add(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task, tasks.size());
        } catch (Exception e) {
            throw new PenguinException("Please use the date format yyyy-mm-dd (e.g., 2019-12-02)");
        }
    }

    /**
     * Handles the event command.
     * @param description The task description
     * @param from The start time
     * @param to The end time
     * @throws PenguinException if there's an error
     */
    private void handleEventCommand(String description, String from, String to) throws PenguinException {
        if (description.isEmpty()) {
            throw new PenguinException("The description of an event cannot be empty.");
        }
        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Entry point of the Penguin chatbot application.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Penguin("data/tasks.txt").run();
    }
}