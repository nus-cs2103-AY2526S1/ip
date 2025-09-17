package haru.command;

import java.io.IOException;
import java.util.HashMap;

import haru.exception.EmptyArgumentException;
import haru.exception.HaruException;
import haru.exception.UnknownOptionException;
import haru.model.TaskList;
import haru.model.TaskTime;
import haru.ui.Ui;

/**
 * Base class for all commands.
 */
public abstract class Command {
    private final HashMap<String, String> options = new HashMap<>();
    private final HashMap<String, String> aliases;
    private final CommandContext ctx;

    /**
     * Constructs a Command with option aliases and context.
     *
     * @param aliases map of option aliases
     * @param ctx     command context for execution
     */
    public Command(HashMap<String, String> aliases, CommandContext ctx) {
        this.aliases = new HashMap<>(aliases);
        for (String name : aliases.keySet()) {
            this.options.put(name, "");
        }
        if (!aliases.containsKey("primary")) {
            this.aliases.put("primary", "main value");
            this.options.put("primary", "");
        }
        this.ctx = ctx;
    }

    /**
     * Returns the task list from the context.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return this.ctx.getTaskList();
    }

    /**
     * Returns the Ui from the context.
     *
     * @return the Ui
     */
    public Ui getUi() {
        return this.ctx.getUI();
    }

    /**
     * Gets a required option value or throws if missing.
     *
     * @param name the option name
     * @return the option value
     * @throws HaruException if option is missing
     */
    public String getRequiredOption(String name) throws HaruException {
        String value = this.options.get(name);
        if (value.isEmpty()) {
            String alias = this.aliases.get(name);
            throw new EmptyArgumentException(alias);
        }
        return value;
    }

    /**
     * Gets a required time option as TaskTime.
     *
     * @param name the option name
     * @return the parsed TaskTime
     * @throws HaruException if option is missing or invalid
     */
    public TaskTime getRequiredTime(String name) throws HaruException {
        String strTime = getRequiredOption(name);
        String alias = this.aliases.get(name);
        return new TaskTime(alias, strTime);
    }

    /**
     * Parses tokens into command options.
     *
     * @param tokens the token array
     * @throws HaruException if unknown option is found
     */
    public void parse(String[] tokens) throws HaruException {
        StringBuilder sb = new StringBuilder();
        String name = "primary";
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.charAt(0) == '/') {
                this.options.put(name, sb.toString());
                name = token.substring(1);
                if (!this.options.containsKey(name)) {
                    throw new UnknownOptionException(name);
                }
                sb = new StringBuilder();
                continue;
            }
            if (!sb.isEmpty()) {
                sb.append(' ');
            }
            sb.append(token);
        }
        this.options.put(name, sb.toString());
    }

    /**
     * Executes the command.
     *
     * @throws HaruException if command fails
     * @throws IOException   if IO error occurs
     */
    public abstract void execute() throws HaruException, IOException;
}
