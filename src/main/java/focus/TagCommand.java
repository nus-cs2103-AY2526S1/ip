package focus;

/**
 * Adds a TagCommand for the specified task to the task list.
 */
public class TagCommand extends FocusCommand {

    private String description;
    private int tagIndex;

    /**
     * Constructs a TagCommand.
     *
     * @param description Description of the tag.
     * @param tagIndex The task index to tag.
     */
    public TagCommand(int tagIndex, String description) throws FocusException {
        this.description = description;
        this.tagIndex = tagIndex;
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by adding the deadline to the list and showing feedback.
     *
     * @param tasks Task list to update.
     */
    @Override
    public void execute(TaskList tasks) throws FocusException {
        if (!(tasks.get(this.tagIndex).isTagged())) {
            tasks.get(this.tagIndex).setTag(new Tag(this.tagIndex, this.description));
        } else {
            throw new FocusException("Task is already tagged! You cannot retag a task!");
        }
    }

}
