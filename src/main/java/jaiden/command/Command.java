package jaiden.command;

import java.util.Arrays;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for command.
 */
public abstract class Command {
    protected String[] inputs;
    protected String string;
    protected CommandType commandType;

    /**
     * Constructor for command.
     *
     * @param inputs User input.
     */
    public Command(String[] inputs) {
        assert inputs.length > 0;
        this.inputs = inputs;
    }

    /**
     * Executes command.
     *
     * @param taskList Task list.
     * @param storage Storage to save current data.
     */
    public abstract void execute(TaskList taskList, Storage storage) throws JaidenException;

    /**
     * Gets string.
     */
    public String getString() {
        assert this.string != null;
        return this.string;
    }

    /**
     * Gets command type.
     *
     * @return Command type.
     */
    public CommandType getCommandType() {
        assert this.commandType != null;
        return this.commandType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Command other)) {
            return false;
        }
        return Arrays.equals(this.inputs, other.inputs);
    }
}
