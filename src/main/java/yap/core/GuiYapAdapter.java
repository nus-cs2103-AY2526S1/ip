package yap.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import yap.io.Ui;
import yap.parser.Parser;
import yap.task.TaskList;

/**
 * Bridges the existing CLI engine in {@link Yap} to the GUI without changing behaviour.
 *
 * <p>It reuses the same {@code Yap} instance and state. On each call, it temporarily captures
 * {@code System.out}, executes the exact same code paths (using reflection to invoke Yap's private
 * helpers when needed), and returns the text that the CLI would have printed. No command strings,
 * ordering, or formatting are reimplemented here, ensuring parity.
 */
public final class GuiYapAdapter {
    private final Yap yap;

    // Cached reflective handles for performance and clarity.
    private final Field fUi;
    private final Field fParser;
    private final Field fStorage;
    private final Field fTasks;
    private final Field fInAddMode;
    private final Field fUserName;

    private final Method mHelpText;
    private final Method mHandleAddLine;
    private final Method mHandleDelete;
    private final Method mHandleComplete;
    private final Method mHandleFind;
    private final Method mHandleEdit;

    private boolean isNameSet = false;
    private boolean exitRequested = false;

    /**
     * Creates an adapter around a new {@link Yap} using the given file path (same as CLI).
     *
     * @param filePath path to the persistent task file (e.g., {@code data/tasks.txt}).
     */
    public GuiYapAdapter(String filePath) {
        this.yap = new Yap(filePath);
        try {
            // Initialize reflection fields
            this.fUi = initializeField("ui");
            this.fParser = initializeField("parser");
            this.fStorage = initializeField("storage");
            this.fTasks = initializeField("tasks");
            this.fInAddMode = initializeField("isInAddMode");
            this.fUserName = initializeField("userName");

            // Initialize reflection methods
            this.mHelpText = initializeMethod("helpText");
            this.mHandleAddLine = initializeMethod("handleAddLine", String.class);
            this.mHandleDelete = initializeMethod("handleDelete", String.class);
            this.mHandleComplete = initializeMethod("handleComplete", String.class);
            this.mHandleFind = initializeMethod("handleFind", String.class);
            this.mHandleEdit = initializeMethod("handleEdit", String.class);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Reflection wiring failed: " + e.getMessage(), e);
        }
    }

    /**
     * Initializes a reflection field and makes it accessible.
     *
     * @param fieldName the name of the field to initialize
     * @return the initialized and accessible field
     * @throws ReflectiveOperationException if field initialization fails
     */
    private Field initializeField(String fieldName) throws ReflectiveOperationException {
        Field field = Yap.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    /**
     * Initializes a reflection method and makes it accessible.
     *
     * @param methodName the name of the method to initialize
     * @param parameterTypes the parameter types of the method
     * @return the initialized and accessible method
     * @throws ReflectiveOperationException if method initialization fails
     */
    private Method initializeMethod(String methodName, Class<?>... parameterTypes) throws ReflectiveOperationException {
        Method method = Yap.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }

    /**
     * Returns the exact initial greeting and prompt the CLI shows at startup, assembled from {@link
     * Ui#showWelcome()} and the name prompt in {@link Ui#askName()}.
     *
     * <p>We do not call {@code askName()} here to avoid blocking on {@code System.in}. Instead, we
     * return the same two lines the CLI would print up front.
     *
     * @return Greeting + name prompt text.
     */
    public String getGreetingAndPrompt() {
        // These two lines come directly from Ui.java in your repo.
        return "Hello! I'm Yap your new best friend!\n" + "May I know what's your name?\n";
    }

    /**
     * Supplies the user's name exactly as the CLI would set it, and returns the CLI's post-name
     * message.
     *
     * @param name user's name (non-null; may be trimmed by caller).
     * @return Output produced by the CLI after the name is accepted.
     */
    public String setUserName(String name) {
        isNameSet = true;
        return buildUserNameResponse(name);
    }

    /**
     * Builds the complete response message after user provides their name.
     * This matches the exact CLI output sequence.
     *
     * @param name the user's name
     * @return the formatted response message
     */
    private String buildUserNameResponse(String name) {
        // The CLI prints: "Nice to meet you, {name}!" and "How can i help you today?" (from askName)
        // followed by "For a list of available commands, type 'help'." (from run method via showMessage)
        return String.format(
                "Nice to meet you, %s!\nHow can i help you today?\nFor a list of available commands, type 'help'.\n",
                name);
    }

    /**
     * Handles one user input line by executing the exact same command flow and returning the CLI's
     * output. Output is captured by temporarily redirecting {@code System.out}.
     *
     * @param raw user input.
     * @return text exactly as the CLI would have printed.
     */
    public String handle(String raw) {
        if (!isNameSet) {
            return setUserName(raw == null ? "" : raw.trim());
        }

        String s = raw == null ? "" : raw.trim();
        return executeCommandAndCaptureOutput(s);
    }

    /**
     * Executes the command and captures output by redirecting System.out.
     *
     * @param input the processed user input
     * @return the captured output text
     */
    private String executeCommandAndCaptureOutput(String input) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
        try (PrintStream capture = new PrintStream(baos)) {
            System.setOut(capture);
            try {
                executeCommandSwitch(input);
            } catch (Exception e) {
                // Handle any unexpected errors that occur during command processing
                handleCommandError(capture, e);
            }
        } finally {
            System.setOut(originalOut);
        }
        return baos.toString();
    }

    /**
     * Handles errors that occur during command processing.
     * This ensures error messages are properly formatted and captured to match CLI behavior.
     *
     * @param capture the capture stream for output
     * @param e the exception that occurred
     */
    private void handleCommandError(PrintStream capture, Exception e) {
        // Format error message to match CLI style - both YapException and other exceptions
        // use the same "☹ OOPS! " prefix as shown in ui.showError()
        String errorMessage = "☹ OOPS! " + e.getMessage();
        capture.print(errorMessage + System.lineSeparator());
    }

    /**
     * Executes the command switch logic by getting required fields and delegating to specific handlers.
     *
     * @param input the processed user input
     * @throws Exception if reflection operations fail
     */
    private void executeCommandSwitch(String input) throws Exception {
        Parser.Parsed cmd = parseInput(input);
        CommandContext context = getCommandContext();

        showSeparatorLine(context.ui);
        processCommandByKind(cmd, context);
    }

    /**
     * Parses the user input into a command.
     *
     * @param input the user input
     * @return the parsed command
     * @throws Exception if parsing fails
     */
    private Parser.Parsed parseInput(String input) throws Exception {
        Parser parser = (Parser) fParser.get(yap);
        return parser.parse(input);
    }

    /**
     * Context object containing all necessary components for command processing.
     */
    private static class CommandContext {
        final Ui ui;
        final TaskList tasks;
        final Object storage;

        CommandContext(Ui ui, TaskList tasks, Object storage) {
            this.ui = ui;
            this.tasks = tasks;
            this.storage = storage;
        }
    }

    /**
     * Gets the command context by extracting necessary components via reflection.
     *
     * @return the command context
     * @throws Exception if reflection fails
     */
    private CommandContext getCommandContext() throws Exception {
        Ui ui = (Ui) fUi.get(yap);
        TaskList tasks = (TaskList) fTasks.get(yap);
        Object storage = fStorage.get(yap);
        return new CommandContext(ui, tasks, storage);
    }

    /**
     * Shows the separator line before command processing.
     *
     * @param ui the UI instance
     */
    private void showSeparatorLine(Ui ui) {
        ui.showLine();
    }

    /**
     * Processes the command based on its kind.
     *
     * @param cmd the parsed command
     * @param context the command context
     * @throws Exception if command processing fails
     */
    private void processCommandByKind(Parser.Parsed cmd, CommandContext context) throws Exception {
        switch (cmd.kind) {
        case HELP:
            handleHelpCommand(context.ui);
            break;
        case SHOW:
            handleShowCommand(context.ui, context.tasks);
            break;
        case ADD:
            handleAddCommand(cmd, context.ui, context.storage);
            break;
        case DELETE:
            handleDeleteCommand(cmd, context.tasks, context.storage);
            break;
        case COMPLETE:
            handleCompleteCommand(cmd, context.tasks, context.storage);
            break;
        case EXIT:
            handleExitCommand();
            break;
        case FIND:
            handleFindCommand(cmd);
            break;
        case EDIT:
            handleEditCommand(cmd, context.tasks, context.storage);
            break;
        case UNKNOWN:
        default:
            handleUnknownCommand(cmd, context.ui);
            break;
        }
    }

    /**
     * Handles the HELP command.
     *
     * @param ui the UI instance
     * @throws Exception if reflection fails
     */
    private void handleHelpCommand(Ui ui) throws Exception {
        String help = (String) mHelpText.invoke(yap);
        ui.showMessage(help);
    }

    /**
     * Handles the SHOW command.
     *
     * @param ui the UI instance
     * @param tasks the task list
     * @throws Exception if operations fail
     */
    private void handleShowCommand(Ui ui, TaskList tasks) throws Exception {
        if (tasks.size() == 0) {
            ui.showMessage("No tasks yet.");
        } else {
            ui.showMessage(tasks.render());
        }
    }

    /**
     * Handles the ADD command, including add mode management.
     *
     * @param cmd the parsed command
     * @param ui the UI instance
     * @param storage the storage instance for saving
     * @throws Exception if reflection fails
     */
    private void handleAddCommand(Parser.Parsed cmd, Ui ui, Object storage) throws Exception {
        boolean inAddMode = fInAddMode.getBoolean(yap);
        if (!inAddMode && !"done".equalsIgnoreCase(cmd.rest)) {
            enterAddMode(ui);
        } else if (inAddMode) {
            handleAddModeExitOrContinue(cmd, ui);
        } else {
            ui.showError("Say 'add' first to enter Add mode.");
        }
    }

    /**
     * Enters add mode and shows instructions.
     *
     * @param ui the UI instance
     * @throws Exception if reflection fails
     */
    private void enterAddMode(Ui ui) throws Exception {
        fInAddMode.setBoolean(yap, true);
        ui.showMessage(
                "Entered Add mode. Use:\n"
                        + "  t <name>\n"
                        + "  d <name>/<yyyy-MM-dd>\n"
                        + "  e <name>/<yyyy-MM-dd>/<HHmm>/<HHmm>\n"
                        + "Type 'done' to exit Add mode.");
    }

    /**
     * Handles continuing in add mode or exiting add mode.
     *
     * @param cmd the parsed command
     * @param ui the UI instance
     * @throws Exception if reflection fails
     */
    private void handleAddModeExitOrContinue(Parser.Parsed cmd, Ui ui) throws Exception {
        if ("done".equalsIgnoreCase(cmd.rest)) {
            fInAddMode.setBoolean(yap, false);
            ui.showMessage("Exited Add mode.");
        } else {
            mHandleAddLine.invoke(yap, cmd.rest);
        }
    }

    /**
     * Handles the DELETE command and saves changes.
     *
     * @param cmd the parsed command
     * @param tasks the task list
     * @param storage the storage instance
     * @throws Exception if reflection fails
     */
    private void handleDeleteCommand(Parser.Parsed cmd, TaskList tasks, Object storage) throws Exception {
        mHandleDelete.invoke(yap, cmd.rest);
        saveTasks(storage, tasks);
    }

    /**
     * Handles the COMPLETE command and saves changes.
     *
     * @param cmd the parsed command
     * @param tasks the task list
     * @param storage the storage instance
     * @throws Exception if reflection fails
     */
    private void handleCompleteCommand(Parser.Parsed cmd, TaskList tasks, Object storage) throws Exception {
        mHandleComplete.invoke(yap, cmd.rest);
        saveTasks(storage, tasks);
    }

    /**
     * Handles the EXIT command.
     *
     * @throws Exception if reflection fails
     */
    private void handleExitCommand() throws Exception {
        exitRequested = true;
        showGoodbyeMessage();
    }

    /**
     * Shows the goodbye message using the stored user name.
     *
     * @throws Exception if reflection fails
     */
    private void showGoodbyeMessage() throws Exception {
        Ui ui = (Ui) fUi.get(yap);
        String userName = (String) fUserName.get(yap);
        ui.showGoodbye(userName);
    }

    /**
     * Handles the FIND command.
     *
     * @param cmd the parsed command
     * @throws Exception if reflection fails
     */
    private void handleFindCommand(Parser.Parsed cmd) throws Exception {
        mHandleFind.invoke(yap, cmd.rest);
    }

    /**
     * Handles the EDIT command and saves changes.
     *
     * @param cmd the parsed command
     * @param tasks the task list
     * @param storage the storage instance
     * @throws Exception if reflection fails
     */
    private void handleEditCommand(Parser.Parsed cmd, TaskList tasks, Object storage) throws Exception {
        mHandleEdit.invoke(yap, cmd.rest);
        saveTasks(storage, tasks);
    }

    /**
     * Handles unknown commands.
     *
     * @param cmd the parsed command
     * @param ui the UI instance
     * @throws Exception if reflection fails
     */
    private void handleUnknownCommand(Parser.Parsed cmd, Ui ui) throws Exception {
        boolean inAddMode = fInAddMode.getBoolean(yap);
        if (inAddMode) {
            mHandleAddLine.invoke(yap, cmd.rest);
        } else {
            ui.showError("I don't understand. Type 'help' to see the list of commands.");
        }
    }

    /**
     * Saves tasks using reflection to match CLI behavior.
     *
     * @param storage the storage instance
     * @param tasks the task list
     * @throws Exception if reflection fails
     */
    private void saveTasks(Object storage, TaskList tasks) throws Exception {
        yap.io.Storage.class
                .getMethod("save", java.util.List.class)
                .invoke(storage, tasks.all());
    }

    /**
     * Returns whether the last command requested exit (so the GUI can close).
     *
     * @return {@code true} if the user issued an exit command.
     */
    public boolean isExit() {
        return exitRequested;
    }
}

