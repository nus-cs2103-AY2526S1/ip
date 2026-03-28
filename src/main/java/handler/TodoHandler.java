package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.ToDo;
import hachi.Ui;

/**
 * Handles the {@link CommandCode#TODO} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     todo &lt;description&gt;
 * </pre>
 * Example:
 * <pre>
 *     todo buy groceries
 * </pre>
 *
 * <p>If a description is provided, a new {@link ToDo} task is created and added
 * to the {@link TaskList}. Otherwise, a failure message is returned.</p>
 */
public class TodoHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code TodoHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public TodoHandler(String input, Ui ui){
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the todo command by creating a {@link ToDo} task
     * from the provided description and adding it to the given task list.
     *
     * @param tasks the task list to operate on
     * @return a success message if the task was added successfully,
     *         or a failure message if no description was provided
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ", 2);
        if (parts.length > 1) {
            tasks.getTasks().add(new ToDo(parts[1]));
            return ui.success(CommandCode.TODO);
        } else {
            return Ui.failure(CommandCode.TODO);
        }
    }
}

