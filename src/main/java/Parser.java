import exceptions.DukeyException;
import tasklist.TaskList;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/* Parser class to decide response to user input. */
class Parser {

    /**  Dukey chatbot */
    private Dukey dukey;
    /**  list of current tasks */
    private TaskList taskList;
    /**  storage to handle interactions with .txt file */
    private Storage storage;

    /**
     * Initialise parser.
     *
     * @param dukey Dukey chatbot.
     * @param taskList list of current tasks.
     * @param storage to handle interactions with .txt file.
     */
    public Parser(Dukey dukey, TaskList taskList, Storage storage) {
        this.dukey = dukey;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Get user input and process it.
     * Decide the correct response for a given command,
     * and use description if necessary.
     *
     * @param command instruction to be parsed.
     * @param description further description of command.
     * @throws DukeyException if command is unrecognised.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public String parse(String command, String description) throws DukeyException {

        if (command.equals("bye")) { // Exit condition
            return dukey.end();
        }
        Task task = null; // Initialize task object to store created tasks
        boolean isRewritten = false;
        String output = "";

        // Handle commands
        switch (command) {
        case "list":
            output = taskList.lst(); // Display the task list
            break;
        case "mark":
            output = taskList.markTask(description);
            isRewritten = true; // Mark task as done
            break;
        case "unmark":
            output = taskList.unmarkTask(description); // Mark task as undone
            isRewritten = true;
            break;
        case "delete":
            output = taskList.deleteTask(Integer.parseInt(description.trim()) - 1); // Delete a task
            isRewritten = true;
            break;
        case "todo":
            task = new ToDo(description.trim(), false); // Create ToDo task
            isRewritten = true;
            break;
        case "deadline":
            task = new DeadLine(description.trim(), false); // Create deadline task
            isRewritten = true;
            break;
        case "event":
            task = new Event(description, false); // Create event task
            isRewritten = true;
            break;
        case "find":
            return taskList.findTask(description);
        default:
            assert(task == null);
            throw new DukeyException("Command not found"); // Handle invalid commands
        }
        // If task is not null, add it to the list
        if (task != null) {
            output += taskList.addTask(task);
        }
        //rewrite the txt file
        if (isRewritten) {
            output += "\n" + storage.rewriteFile();
        }
        return output;
    }
}

