package sophia;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Main chatbot class
 */
//used ChatGpt for quality check of code
public class Sophia {
    //Keep a list of internal tasks
    private static final UI ui = new UI();
    private static final String NAME = "Sophia";
    private boolean isExit = false;
    private final TaskList taskList;
    private final Storage storage;
    /**
     * Returns a Sophia Object which interacts with users
     * <p>
     * @param filePath specifies a valid file path.
     */
    public Sophia(String filePath) {
        TaskList taskList1;
        storage = new Storage(filePath);
        try {
            taskList1 = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError(e);
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }

    private String printList(String input) throws SophiaException {
        if (!Parser.validateListInput(input)) {
            throw new SophiaException("Usage: list");
        }
        return ui.printList(taskList);
    }

    //public for testing
    private Task addTodo(String input, String description) throws SophiaException {
        if (!Parser.validateTodoInput(input)) {
            throw new SophiaException("todo <description>");
        }
        TodoTaskParser parser = new TodoTaskParser(description);
        return parser.parse();
    }

    //public for testing
    private Task addDeadline(String input, String description) throws SophiaException {
        if (!Parser.validateDeadlineInput(input)) {
            throw new SophiaException(
                    "deadline <description> /by <YYYY-MM-DD HH:MM> where HH:MM is optional");
        }
        DeadlineTaskParser parser = new DeadlineTaskParser(description);
        return parser.parse();
    }

    //public for testing
    private Task addEvent(String input, String description) throws SophiaException {
        if (!Parser.validateEventInput(input)) {
            throw new SophiaException("Usage: event <description> /from <YYYY-MM-DD HH:MM> "
                    + "/to <YYYY-MM-DD HH:MM> HH:MM are optional");
        }
        EventTaskParser parser = new EventTaskParser(description);
        return parser.parse();
    }

    private String addTask(String input, TaskType type) throws SophiaException {
        Task newTask;
        String[] segments = input.split(" ");
        String description = String.join(" ",
                Arrays.copyOfRange(input.split(" "), 1, segments.length));

        switch (type) {
        case TODO -> {
            newTask = addTodo(input, description);
            assert newTask != null;
            assert newTask instanceof TodoTask;
        }
        case DEADLINE -> {
            newTask = addDeadline(input, description);
            assert newTask != null;
            assert newTask instanceof DeadlineTask;
        }
        case EVENT -> {
            newTask = addEvent(input, description);
            assert newTask != null;
            assert newTask instanceof EventTask;
        }
        default -> throw new SophiaException("Invalid Type of command");
        }

        taskList.addTask(newTask);
        return ui.addTask(newTask, taskList);
    }

    private String handleTask(String userInputs, boolean done) throws SophiaException {
        if (!Parser.validateMarkInput(userInputs)
                && !Parser.validateUnmarkInput(userInputs) ) {
            if (done) {
                throw new SophiaException("Usage: mark <index>");
            } else {
                throw new SophiaException("Usage: unmark <index>");
            }
        }

        int index = Integer.parseInt(userInputs.split(" ")[1]) - 1;
        if (index < 0 || index >= taskList.taskListSize()) {
            throw new SophiaException("No such task exists!");
        }

        taskList.setDone(index, done);
        return ui.markTask(taskList, done, index);
    }

    private String handleBye(String input) throws SophiaException {
        if (!Parser.validateByeInput(input)) {
            throw new SophiaException("Usage: bye");
        }
        this.isExit = true;
        return ui.exit();
    }

    private String saveTasks(String input) throws SophiaException {
        if (!Parser.validateSaveInput(input)) {
            throw new SophiaException("Usage: save");
        }
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new SophiaException(e.getMessage());
        }

        return ui.saved();
    }

    public String showTasks() {
        return taskList.printList();
    }

    private String findTask(String input) throws SophiaException {
        if (!Parser.validateFindInput(input)) {
            throw new SophiaException("Usage: find <keyword>");
        }
        String keyword = input.split(" ")[1].trim();
        List<Task> xs = taskList.findTask(keyword);
        assert xs != null;
        return ui.printTasksFound(xs);
    }

    /**
     * deleteTask is responsible for deleting a task from taskList
     * @param input specifies an input string representing a command
     * @throws SophiaException specifies an exception that occurs when
     *          users type invalid commands
     * */
    public String deleteTask(String input) throws SophiaException {
        if (!Parser.validateDeleteInput(input)) {
            throw new SophiaException("Usage: delete <index>");
        }
        assert taskList.taskListSize() > 0;
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= taskList.taskListSize()) {
            throw new SophiaException("No such task exists!");
        }
        String str = ui.deleteTask(index, taskList);
        taskList.removeTask(index);
        return str;
    }

    /**
     * Returns a human-readable string representing the response to a command
     *          that a user types in
     * @param input which represents a command input from users
     * @return a string response that is human-readable
     * @throws SophiaException which is an exception that occurs when command are
     *          not formatted properly
     */
    public String run(String input) throws SophiaException {
        String[] userInputs = input.split("\\s+", 2);
        String cmd = userInputs[0];

        TaskType type = Arrays.stream(TaskType.values())
                .filter(t -> t.keyword.equals(cmd))
                .findFirst()
                .orElse(null);

        if (type == null) {
            throw new SophiaException("Invalid command: " + cmd);
        }

        try {
            switch (type) {
            case BYE -> {
                return handleBye(input);
            }
            case LIST -> {
                return printList(input);
            }
            case MARK -> {
                return handleTask(input, true);
            }
            case UNMARK -> {
                return handleTask(input, false);
            }
            case TODO -> {
                return addTask(input, TaskType.TODO);
            }
            case EVENT -> {
                return addTask(input, TaskType.EVENT);
            }
            case DEADLINE -> {
                return addTask(input, TaskType.DEADLINE);
            }
            case DELETE -> {
                return deleteTask(input);
            }
            case SAVE -> {
                return saveTasks(input);
            }
            case FIND -> {
                return findTask(input);
            }
            default -> throw new SophiaException("Invalid Command: " + cmd);
            }
        } catch (SophiaException e) {
            return ui.showError(e);
        }
    }

    public List<String> sendReminder() {
        return taskList.sendReminder();
    }

    public boolean isExit() {
        return isExit;
    }
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Sophia sophia = new Sophia("./data/test.txt");
            ui.introduction(NAME);
            while (true) {
                if (!scanner.hasNextLine()) {
                    System.out.println(ui.printEmptyMessage());
                }
                String input = scanner.nextLine().trim();
                System.out.println(sophia.run(input));
                if (sophia.isExit) {
                    java.lang.System.exit(0);
                }
            }
        } catch (SophiaException e) {
            System.out.println(e.getMessage());
        }
    }
}
