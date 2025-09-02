package buddy;

/**
 * Main class for the Buddy task management application.
 * Provides a command-line interface for managing personal tasks including
 * todos, deadlines, and events with persistent storage.
 */
public class Buddy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    /**
     * Constructs a new Buddy application with default data file path.
     * Used for GUI mode.
     */
    public Buddy() {
        this("./data/buddy.txt");
    }

    /**
     * Constructs a new Buddy application with the specified data file path.
     * Initializes UI, storage, and task list components.
     * 
     * @param filePath the path to the data file for persistent storage
     */
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
    
    /**
     * Starts the main application loop.
     * Displays welcome message and processes user commands until exit.
     */
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
            case "find":
                handleFind(input);
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
    
    private void handleFind(String input) throws BuddyException {
        String keyword = Parser.parseDescription(input, "find");
        Task[] matchingTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(matchingTasks);
    }

    private void saveTasksToFile() throws BuddyException {
        storage.save(tasks.getTasks());
    }

    /**
     * Initializes the application for GUI mode.
     */
    public void initializeWithGui() {
        // GUI mode initialization - no Scanner needed
    }

    /**
     * Gets the welcome message for GUI display.
     * 
     * @return the welcome message as a string
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Buddy\nWhat can I do for you?";
    }

    /**
     * Processes user input and returns the appropriate response.
     * 
     * @param input the user's input command
     * @return the response message
     */
    public String getResponse(String input) {
        try {
            String command = Parser.parseCommand(input);
            
            if (command.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (command.equals("list")) {
                return getTaskListResponse();
            } else if (command.equals("mark")) {
                return handleMarkResponse(input);
            } else if (command.equals("unmark")) {
                return handleUnmarkResponse(input);
            } else if (command.equals("delete")) {
                return handleDeleteResponse(input);
            } else if (command.equals("todo")) {
                return handleTodoResponse(input);
            } else if (command.equals("deadline")) {
                return handleDeadlineResponse(input);
            } else if (command.equals("event")) {
                return handleEventResponse(input);
            } else if (command.equals("find")) {
                return handleFindResponse(input);
            } else {
                return "I'm sorry, but I don't know what that means :-(";
            }
        } catch (BuddyException e) {
            return e.getMessage();
        }
    }

    private String getTaskListResponse() {
        if (tasks.size() == 0) {
            return "You have no tasks in your list.";
        }
        
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        Task[] taskArray = tasks.getTaskArray();
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("%d.%s\n", i + 1, taskArray[i]));
        }
        return response.toString().trim();
    }

    private String handleMarkResponse(String input) throws BuddyException {
        int index = Parser.parseTaskNumber(input, "mark");
        Task task = tasks.getTask(index);
        tasks.markTask(index);
        saveTasksToFile();
        return "Nice! I've marked this task as done:\n  " + task;
    }

    private String handleUnmarkResponse(String input) throws BuddyException {
        int index = Parser.parseTaskNumber(input, "unmark");
        Task task = tasks.getTask(index);
        tasks.unmarkTask(index);
        saveTasksToFile();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    private String handleDeleteResponse(String input) throws BuddyException {
        int index = Parser.parseTaskNumber(input, "delete");
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        saveTasksToFile();
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", 
                            task, tasks.size());
    }

    private String handleTodoResponse(String input) throws BuddyException {
        String description = Parser.parseDescription(input, "todo");
        Task task = new Todo(description);
        tasks.addTask(task);
        saveTasksToFile();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", 
                            task, tasks.size());
    }

    private String handleDeadlineResponse(String input) throws BuddyException {
        String[] parts = Parser.parseDeadline(input);
        Task task = new Deadline(parts[0], parts[1]);
        tasks.addTask(task);
        saveTasksToFile();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", 
                            task, tasks.size());
    }

    private String handleEventResponse(String input) throws BuddyException {
        String[] parts = Parser.parseEvent(input);
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.addTask(task);
        saveTasksToFile();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", 
                            task, tasks.size());
    }

    private String handleFindResponse(String input) throws BuddyException {
        String keyword = Parser.parseDescription(input, "find");
        Task[] matchingTasks = tasks.findTasks(keyword);
        
        if (matchingTasks.length == 0) {
            return "No matching tasks found.";
        }
        
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.length; i++) {
            response.append(String.format("%d.%s\n", i + 1, matchingTasks[i]));
        }
        return response.toString().trim();
    }
    
    public static void main(String[] args) {
        new Buddy("./data/buddy.txt").run();
    }
}