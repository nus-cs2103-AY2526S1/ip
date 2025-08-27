package buddy;

public class Buddy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Buddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BuddyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                String command = Parser.parseCommand(input);
                
                if (command.equals("bye")) {
                    isExit = true;
                    ui.showGoodbye();
                } else {
                    handleCommand(input, command);
                }
            } catch (BuddyException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }
    
    private void handleCommand(String input, String command) throws BuddyException {
        switch (command) {
            case "list":
                ui.showTaskList(tasks.getTaskArray(), tasks.size());
                break;
            case "mark":
                handleMark(input);
                break;
            case "unmark":
                handleUnmark(input);
                break;
            case "delete":
                handleDelete(input);
                break;
            case "todo":
                handleTodo(input);
                break;
            case "deadline":
                handleDeadline(input);
                break;
            case "event":
                handleEvent(input);
                break;
            default:
                throw new BuddyException("I'm sorry, but I don't know what that means :-(");
        }
    }
    
    private void handleMark(String input) throws BuddyException {
        int index = Parser.parseTaskNumber(input, "mark");
        Task task = tasks.getTask(index);
        tasks.markTask(index);
        saveTasksToFile();
        ui.showTaskMarkedDone(task);
    }
    
    private void handleUnmark(String input) throws BuddyException {
        int index = Parser.parseTaskNumber(input, "unmark");
        Task task = tasks.getTask(index);
        tasks.unmarkTask(index);
        saveTasksToFile();
        ui.showTaskMarkedNotDone(task);
    }
    
    private void handleDelete(String input) throws BuddyException {
        int index = Parser.parseTaskNumber(input, "delete");
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        saveTasksToFile();
        ui.showTaskDeleted(task, tasks.size());
    }
    
    private void handleTodo(String input) throws BuddyException {
        String description = Parser.parseDescription(input, "todo");
        Task task = new Todo(description);
        tasks.addTask(task);
        saveTasksToFile();
        ui.showTaskAdded(task, tasks.size());
    }
    
    private void handleDeadline(String input) throws BuddyException {
        String[] parts = Parser.parseDeadline(input);
        Task task = new Deadline(parts[0], parts[1]);
        tasks.addTask(task);
        saveTasksToFile();
        ui.showTaskAdded(task, tasks.size());
    }
    
    private void handleEvent(String input) throws BuddyException {
        String[] parts = Parser.parseEvent(input);
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.addTask(task);
        saveTasksToFile();
        ui.showTaskAdded(task, tasks.size());
    }
    
    private void saveTasksToFile() throws BuddyException {
        storage.save(tasks.getTasks());
    }
    
    public static void main(String[] args) {
        new Buddy("./data/buddy.txt").run();
    }
}