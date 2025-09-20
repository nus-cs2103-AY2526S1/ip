package command;

import taskmodule.Task;

/**
 * Represents the {@code note} command which adds a note to a specified task in the task list.
 *
 * <p>When executed, this command attaches additional note content to the
 * specified {@link Task} in the global {@link Command#taskList}, and returns
 * a confirmation message to the user.</p>
 */
public class NoteCommand extends Command {
    public static final String COMMAND_WORD = "note";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a note to the specified task.\n"
            + "Parameters: TASK_INDEX /content NOTE_CONTENT\n"
            + "(TASK_INDEX must be a positive integer less than or equal to the number of tasks)\n"
            + "Example: " + COMMAND_WORD + " 2 /content This is an important task.";

    private final int taskIndex;
    private final String noteContent;

    /**
     * Constructs a {@code NoteCommand} with the given task index and note content.
     *
     * @param taskIndex the zero-based index of the task to add a note to
     * @param noteContent the content of the note; must not be {@code null} or empty
     * @throws AssertionError if {@code taskIndex} is negative or {@code noteContent} is invalid
     */
    public NoteCommand(int taskIndex, String noteContent) {
        assert taskIndex >= 0 : "Task index should be non-negative";
        assert noteContent != null && !noteContent.isEmpty() : "Note content should not be null or empty";

        this.taskIndex = taskIndex;
        this.noteContent = noteContent;
    }

    /**
     * Adds the note to the specified task in the global {@link Command#taskList}.
     *
     * @return the updated {@link Task} with the note added
     * @throws IndexOutOfBoundsException if the task index is invalid
     */
    public Task addNoteToTask() {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).addNote(noteContent);
    }

    /**
     * Executes this command by adding the note to the specified task,
     * and returns Penny's confirmation message.
     *
     * @return the message confirming that the note has been added
     */
    @Override
    public String respond() {
        try {
            return "Got it! I've added your note to the task:\n" + this.addNoteToTask();
        } catch (IndexOutOfBoundsException e) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    "The task index provided is invalid.\n" + NoteCommand.MESSAGE_USAGE);
            return incorrectCommand.respond();
        }
    }
}
