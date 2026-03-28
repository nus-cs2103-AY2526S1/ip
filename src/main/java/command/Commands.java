package command;

import task.TaskList;

/**
 * Represents all possible commands that can be performed
 * by the program. Each <code>Enum</code> holds the constructor to their
 * respective command.
 */
public enum Commands {
    MARK(MarkCommand::new),
    UNMARK(UnmarkCommand::new),
    TODO(TodoCommand::new),
    DEADLINE(DeadlineCommand::new),
    EVENT(EventCommand::new),
    LIST(ListCommand::new),
    DELETE(DeleteCommand::new),
    BYE(ByeCommand::new),
    FIND(FindCommand::new),
    REMIND(RemindCommand::new);

    private final CommandBuilder cmdBuilder;

    Commands(CommandBuilder cmdBuilder) {
        this.cmdBuilder = cmdBuilder;
    }

    /**
     * Creates a <code>Command</code> instance for this command type with a
     * specified <code>argument</code> and <code>taskList</code>
     *
     * @param arg      The arguments provided by the user
     * @param taskList The TaskList the command will operate on
     * @return A new Command instance
     */
    public Command create(String arg, TaskList taskList) {
        return this.cmdBuilder.build(arg, taskList);
    }
}
