package yap.core;

import yap.io.Storage;
import yap.io.Ui;
import yap.parser.Parser;
import yap.task.Deadlines;
import yap.task.Events;
import yap.task.Task;
import yap.task.TaskList;
import yap.task.ToDos;

/**
 * Entry point of the Yap application.
 *
 * <p>Responsibilities: initialize UI, Parser, and TaskList; start the main interaction loop with
 * the user. Collaborators: interacts with Parser, TaskList, and UI.
 */
public class Yap {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    private boolean isInAddMode = false;
    private String userName = "friend";

    /**
     * Creates a new Yap instance with the specified file path.
     *
     * @param filePath the path to the storage file
     */
    public Yap(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (YapException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        initializeUserSession();
        runMainLoop();
        ui.showGoodbye(userName);
    }

    /**
     * Initializes the user session by showing welcome and getting user name.
     */
    private void initializeUserSession() {
        ui.showWelcome();
        userName = ui.askName();
        ui.showMessage("For a list of available commands, type 'help'.");
    }

    /**
     * Runs the main command processing loop.
     */
    private void runMainLoop() {
        boolean isExit = false;
        while (!isExit) {
            ui.showLine();
            String input = ui.readCommand();
            Parser.Parsed cmd = parser.parse(input);

            try {
                isExit = processCommand(cmd);
            } catch (YapException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }

    /**
     * Processes a single command and returns whether to exit.
     *
     * @param cmd the parsed command
     * @return true if the application should exit, false otherwise
     * @throws YapException if command processing fails
     */
    private boolean processCommand(Parser.Parsed cmd) throws YapException {
        switch (cmd.kind) {
        case HELP:
            handleHelpCommand();
            return false;

        case SHOW:
            handleShowCommand();
            return false;

        case ADD:
            handleAddCommand(cmd);
            return false;

        case DELETE:
            handleDeleteCommand(cmd);
            return false;

        case COMPLETE:
            handleCompleteCommand(cmd);
            return false;

        case EXIT:
            return true;

        case FIND:
            handleFind(cmd.rest);
            return false;

        case EDIT:
            handleEditCommand(cmd);
            return false;

        case UNKNOWN:
        default:
            handleUnknownCommand(cmd);
            return false;
        }
    }

    /**
     * Handles the HELP command.
     */
    private void handleHelpCommand() {
        ui.showMessage(helpText());
    }

    /**
     * Handles the SHOW command.
     */
    private void handleShowCommand() {
        if (tasks.size() == 0) {
            ui.showMessage("No tasks yet.");
        } else {
            ui.showMessage(tasks.render());
        }
    }

    /**
     * Handles the ADD command including add mode management.
     *
     * @param cmd the parsed command
     * @throws YapException if command processing fails
     */
    private void handleAddCommand(Parser.Parsed cmd) throws YapException {
        if (!isInAddMode && !"done".equalsIgnoreCase(cmd.rest)) {
            enterAddMode();
        } else if (isInAddMode) {
            handleAddModeCommand(cmd);
        } else {
            ui.showError("Say 'add' first to enter Add mode.");
        }
    }

    /**
     * Enters add mode and shows instructions.
     */
    private void enterAddMode() {
        isInAddMode = true;
        ui.showMessage(
                "Entered Add mode. Use:\n"
                        + "  t <name>\n"
                        + "  d <name>/<yyyy-MM-dd>\n"
                        + "  e <name>/<yyyy-MM-dd>/<HHmm>/<HHmm>\n"
                        + "Type 'done' to exit Add mode.");
    }

    /**
     * Handles commands while in add mode.
     *
     * @param cmd the parsed command
     * @throws YapException if command processing fails
     */
    private void handleAddModeCommand(Parser.Parsed cmd) throws YapException {
        if ("done".equalsIgnoreCase(cmd.rest)) {
            exitAddMode();
        } else {
            handleAddLine(cmd.rest);
            storage.save(tasks.all());
        }
    }

    /**
     * Exits add mode.
     */
    private void exitAddMode() {
        isInAddMode = false;
        ui.showMessage("Exited Add mode.");
    }

    /**
     * Handles the DELETE command.
     *
     * @param cmd the parsed command
     * @throws YapException if command processing fails
     */
    private void handleDeleteCommand(Parser.Parsed cmd) throws YapException {
        handleDelete(cmd.rest);
        storage.save(tasks.all());
    }

    /**
     * Handles the COMPLETE command.
     *
     * @param cmd the parsed command
     * @throws YapException if command processing fails
     */
    private void handleCompleteCommand(Parser.Parsed cmd) throws YapException {
        handleComplete(cmd.rest);
        storage.save(tasks.all());
    }

    /**
     * Handles the EDIT command.
     *
     * @param cmd the parsed command
     * @throws YapException if command processing fails
     */
    private void handleEditCommand(Parser.Parsed cmd) throws YapException {
        handleEdit(cmd.rest);
        storage.save(tasks.all());
    }

    /**
     * Handles unknown commands.
     *
     * @param cmd the parsed command
     * @throws YapException if command processing fails
     */
    private void handleUnknownCommand(Parser.Parsed cmd) throws YapException {
        if (isInAddMode) {
            handleAddLine(cmd.rest);
            storage.save(tasks.all());
        } else {
            ui.showError("I don't understand. Type 'help' for commands.");
        }
    }

    private String helpText() {
        return String.join(
                "\n",
                "Commands:",
                "  show / list                  - list tasks",
                "  add                          - enter Add mode; then use t/d/e lines",
                "  delete <number|exact name>   - delete a task",
                "  complete <number|exact name> - mark a task done",
                "  edit <number|exact name> [n/NAME] [d/YYYY-MM-DD] [t/HHmm-HHmm]",
                "      Rules:",
                "        - Todo: only n/ allowed",
                "        - Deadline: n/, d/ allowed",
                "        - Event: n/, d/, and t/ allowed (t/ must be HHmm-HHmm)",
                "  find <keyword>               - list tasks whose description contains the keyword",
                "  help                         - show this help",
                "  exit / quit                  - exit the program");
    }

    private void handleAddLine(String line) throws YapException {
        validateAddLineInput(line);

        AddLineInfo info = parseAddLine(line);
        Task task = createTaskFromInfo(info);
        addTaskAndNotify(task);
    }

    /**
     * Validates that the add line input is not empty.
     *
     * @param line the input line to validate
     * @throws YapException if the input is empty
     */
    private void validateAddLineInput(String line) throws YapException {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            ui.showError("Empty add line.");
            throw new YapException("Empty add line.");
        }
    }

    /**
     * Information extracted from an add line.
     */
    private static class AddLineInfo {
        private final char kind;
        private final String payload;

        AddLineInfo(char kind, String payload) {
            this.kind = kind;
            this.payload = payload;
        }

        char getKind() {
            return kind;
        }

        String getPayload() {
            return payload;
        }
    }

    /**
     * Parses an add line into its components.
     *
     * @param line the add line to parse
     * @return the parsed add line information
     */
    private AddLineInfo parseAddLine(String line) {
        String trimmed = line.trim();
        char kind = Character.toLowerCase(trimmed.charAt(0));
        String payload = trimmed.length() > 1 ? trimmed.substring(1).trim() : "";
        return new AddLineInfo(kind, payload);
    }

    /**
     * Creates a task from the parsed add line information.
     *
     * @param info the parsed add line information
     * @return the created task
     * @throws YapException if the task creation fails
     */
    private Task createTaskFromInfo(AddLineInfo info) throws YapException {
        switch (info.getKind()) {
        case 't':
            return createTodoTask(info.getPayload());
        case 'd':
            return createDeadlineTask(info.getPayload());
        case 'e':
            return createEventTask(info.getPayload());
        default:
            throw new YapException("Unknown add-line type. Use t/d/e.");
        }
    }

    /**
     * Creates a todo task from the payload.
     *
     * @param payload the task payload
     * @return the created todo task
     * @throws YapException if the payload is invalid
     */
    private Task createTodoTask(String payload) throws YapException {
        if (payload.isEmpty()) {
            throw new YapException("ToDo name is empty.");
        }
        return new ToDos(payload);
    }

    /**
     * Creates a deadline task from the payload.
     *
     * @param payload the task payload
     * @return the created deadline task
     * @throws YapException if the payload is invalid
     */
    private Task createDeadlineTask(String payload) throws YapException {
        String[] parts = payload.split("/", 2);
        if (parts.length != 2) {
            throw new YapException("Deadline needs: d <name>/<yyyy-MM-dd>");
        }
        return new Deadlines(parts[0].trim(), parts[1].trim());
    }

    /**
     * Creates an event task from the payload.
     *
     * @param payload the task payload
     * @return the created event task
     * @throws YapException if the payload is invalid
     */
    private Task createEventTask(String payload) throws YapException {
        String[] eParts = payload.split("/", 4);
        if (eParts.length != 4) {
            throw new YapException("Event needs: e <name>/<yyyy-MM-dd>/<HHmm>/<HHmm>");
        }
        return new Events(eParts[0].trim(), eParts[1].trim(), eParts[2].trim(), eParts[3].trim());
    }

    /**
     * Adds the task to the list and shows a confirmation message.
     *
     * @param task the task to add
     */
    private void addTaskAndNotify(Task task) {
        tasks.add(task);
        ui.showMessage("Added: " + task);
    }

    private void handleFind(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            ui.showLine();
            ui.showMessage("Please provide a keyword. Usage: find <keyword>");
            ui.showLine();
            return;
        }
        java.util.List<Integer> hits = tasks.findIndices(keyword);
        ui.showLine();
        if (hits.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            ui.showMessage(tasks.renderByIndices(hits));
        }
        ui.showLine();
    }

    private void handleDelete(String arg) throws YapException {
        if (arg.isBlank()) {
            throw new YapException("Delete needs a number or exact task name.");
        }
        Task removed;
        if (isInteger(arg)) {
            removed = tasks.remove(Integer.parseInt(arg));
        } else {
            int idx = indexOfExactName(arg);
            if (idx < 0) {
                throw new YapException("Task not found: " + arg);
            }
            removed = tasks.remove(idx + 1);
        }
        ui.showMessage("Removed: " + removed);
    }

    private void handleComplete(String arg) throws YapException {
        if (arg.isBlank()) {
            throw new YapException("Complete needs a number or exact task name.");
        }
        Task t;
        if (isInteger(arg)) {
            t = tasks.get(Integer.parseInt(arg));
        } else {
            int idx = indexOfExactName(arg);
            if (idx < 0) {
                throw new YapException("Task not found: " + arg);
            }
            t = tasks.get(idx + 1);
        }
        t.markDone();
        ui.showMessage("Marked as done: " + t);
    }

    private int indexOfExactName(String name) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.all().get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void handleEdit(String rest) throws YapException {
        validateEditInput(rest);

        String[] parts = parseEditInput(rest);
        String target = parts[0];
        String opts = parts[1];

        int idx0 = findTaskIndex(target);
        Task original = tasks.all().get(idx0);
        EditArgs args = parseEditArgs(opts);

        applyEditChanges(original, args);
        tasks.all().set(idx0, original);

        ui.showMessage("Edited: " + original.toString());
    }

    /**
     * Validates that the edit input is properly formatted.
     *
     * @param rest the edit command input
     * @throws YapException if input is invalid
     */
    private void validateEditInput(String rest) throws YapException {
        if (rest == null || rest.trim().isEmpty()) {
            throw new YapException(
                    "Usage: edit <index|exact name> [n/NAME] [d/YYYY-MM-DD] [t/HHmm-HHmm]");
        }

        String s = rest.trim();
        int sp = s.indexOf(' ');
        if (sp < 0) {
            throw new YapException("No fields to change. Provide n/, d/, or t/.");
        }
    }

    /**
     * Parses the edit input into target and options.
     *
     * @param rest the edit command input
     * @return array with target at index 0 and options at index 1
     */
    private String[] parseEditInput(String rest) {
        String s = rest.trim();
        int sp = s.indexOf(' ');
        return new String[]{
            s.substring(0, sp).trim(),
            s.substring(sp + 1).trim()
        };
    }

    /**
     * Finds the task index from either a numeric index or task name.
     *
     * @param target the target specified in the edit command
     * @return the 0-based index of the task
     * @throws YapException if task cannot be found
     */
    private int findTaskIndex(String target) throws YapException {
        if (isInteger(target)) {
            return findTaskIndexByNumber(target);
        } else {
            return findTaskIndexByName(target);
        }
    }

    /**
     * Finds task index by numeric reference.
     *
     * @param target the numeric target
     * @return the 0-based index
     * @throws YapException if index is invalid
     */
    private int findTaskIndexByNumber(String target) throws YapException {
        int oneBased = Integer.parseInt(target);
        if (oneBased < 1 || oneBased > tasks.size()) {
            throw new YapException("Invalid index.");
        }
        return oneBased - 1;
    }

    /**
     * Finds task index by exact name match.
     *
     * @param target the task name
     * @return the 0-based index
     * @throws YapException if task is not found
     */
    private int findTaskIndexByName(String target) throws YapException {
        int byName = indexOfExactName(target);
        if (byName < 0) {
            throw new YapException("Task not found: " + target);
        }
        return byName;
    }

    /**
     * Applies edit changes based on the task type and edit arguments.
     *
     * @param original the original task
     * @param args the parsed edit arguments
     * @throws YapException if the edit is invalid for the task type
     */
    private void applyEditChanges(Task original, EditArgs args) throws YapException {
        if (original instanceof ToDos) {
            applyEditToTodo((ToDos) original, args);
        } else if (original instanceof Deadlines) {
            applyEditToDeadline((Deadlines) original, args);
        } else if (original instanceof Events) {
            applyEditToEvent((Events) original, args);
        } else {
            throw new YapException("Unsupported task type for edit.");
        }
    }

    /**
     * Applies edit changes to a Todo task.
     *
     * @param todo the todo task to edit
     * @param args the edit arguments
     * @throws YapException if edit is invalid
     */
    private void applyEditToTodo(ToDos todo, EditArgs args) throws YapException {
        if (args.getDate() != null || args.getTimeStart() != null || args.getTimeEnd() != null) {
            throw new YapException("Todo can only change name (use n/).");
        }
        String newName = coalesce(args.getName(), todo.getName());
        validateTaskName(newName);
        todo.setName(newName);
    }

    /**
     * Applies edit changes to a Deadline task.
     *
     * @param deadline the deadline task to edit
     * @param args the edit arguments
     * @throws YapException if edit is invalid
     */
    private void applyEditToDeadline(Deadlines deadline, EditArgs args) throws YapException {
        if (args.getTimeStart() != null || args.getTimeEnd() != null) {
            throw new YapException("Deadline can change name and date only (n/, d/).");
        }
        String newName = coalesce(args.getName(), deadline.getName());
        String isoDate = coalesce(args.getDate(), deadline.getBy().toString());
        validateTaskName(newName);
        deadline.setName(newName);
        deadline.setBy(isoDate);
    }

    /**
     * Applies edit changes to an Event task.
     *
     * @param event the event task to edit
     * @param args the edit arguments
     * @throws YapException if edit is invalid
     */
    private void applyEditToEvent(Events event, EditArgs args) throws YapException {
        String newName = coalesce(args.getName(), event.getName());
        String isoDate = coalesce(args.getDate(), event.getDate().toString());

        String startHHmm = (args.getTimeStart() != null)
                ? args.getTimeStart()
                : event.getStart().format(java.time.format.DateTimeFormatter.ofPattern("HHmm"));
        String endHHmm = (args.getTimeEnd() != null)
                ? args.getTimeEnd()
                : event.getEnd().format(java.time.format.DateTimeFormatter.ofPattern("HHmm"));

        validateTaskName(newName);
        validateEventTimes(startHHmm, endHHmm);

        event.setName(newName);
        event.setDate(isoDate);
        event.setStart(startHHmm);
        event.setEnd(endHHmm);
    }

    /**
     * Validates that a task name is not null or blank.
     *
     * @param name the task name to validate
     * @throws YapException if name is invalid
     */
    private void validateTaskName(String name) throws YapException {
        if (name == null || name.isBlank()) {
            throw new YapException("Name cannot be empty.");
        }
    }

    /**
     * Validates that event start time is before end time.
     *
     * @param startHHmm the start time in HHmm format
     * @param endHHmm the end time in HHmm format
     * @throws YapException if times are invalid
     */
    private void validateEventTimes(String startHHmm, String endHHmm) throws YapException {
        if (startHHmm.compareTo(endHHmm) >= 0) {
            throw new YapException("Start must be before end.");
        }
    }

    // Small holder for parsed args
    private static final class EditArgs {
        private String name; // from n/
        private String date; // ISO yyyy-MM-dd from d/
        private String timeStart; // HHmm from t/ start
        private String timeEnd; // HHmm from t/ end

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }
    }

    // Mini parser for n/, d/, t/HHmm-HHmm (values may contain spaces for n/)
    private static EditArgs parseEditArgs(String s) throws YapException {
        EditArgs a = new EditArgs();
        if (s == null || s.isBlank()) {
            return a;
        }

        // Find prefix positions
        int nPos = indexOfWordPref(s, "n/");
        int dPos = indexOfWordPref(s, "d/");
        int tPos = indexOfWordPref(s, "t/");

        // Extract substring for each prefix up to the next prefix
        a.setName(sliceVal(s, nPos, dPos, tPos));
        a.setDate(sliceVal(s, dPos, nPos, tPos));

        String tVal = sliceVal(s, tPos, nPos, dPos);
        if (tVal != null && !tVal.isBlank()) {
            String v = tVal.trim();
            // accept "HHmm-HHmm" or a single "HHmm" (will be treated as both start & end invalid; better
            // to require dash)
            String[] parts = v.split("-");
            if (parts.length != 2) {
                throw new YapException("Time uses t/HHmm-HHmm (e.g., t/0900-1030).");
            }
            a.setTimeStart(parts[0].trim());
            a.setTimeEnd(parts[1].trim());
            if (!a.getTimeStart().matches("\\d{4}") || !a.getTimeEnd().matches("\\d{4}")) {
                throw new YapException("Time must be 4 digits (HHmm).");
            }
        }

        return a;
    }

    // helpers for prefix parsing
    private static int indexOfWordPref(String s, String pref) {
        // match at start or after space
        int i = s.indexOf(" " + pref);
        int j = (s.startsWith(pref) ? 0 : -1);
        if (j == 0) {
            return 0;
        }
        return i < 0 ? -1 : i + 1;
    }

    private static String sliceVal(String s, int selfPos, int other1Pos, int other2Pos) {
        if (selfPos < 0) {
            return null;
        }
        int end = s.length();
        if (other1Pos >= 0 && other1Pos > selfPos) {
            end = Math.min(end, other1Pos);
        }
        if (other2Pos >= 0 && other2Pos > selfPos) {
            end = Math.min(end, other2Pos);
        }
        String raw = s.substring(selfPos + 2, end).trim(); // skip "x/"
        return raw.isEmpty() ? null : raw;
    }

    private static <T> T coalesce(T a, T b) {
        return (a != null) ? a : b;
    }

    /**
     * Launches the Yap application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Yap("data/tasks.txt").run();
    }
}
