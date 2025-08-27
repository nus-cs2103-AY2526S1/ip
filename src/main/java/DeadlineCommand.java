public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        if (description == null || description.trim().isEmpty()) {
            throw new ByteException("The description of a deadline cant be empty");
        }
        if (by == null || by.trim().isEmpty()) {
            throw new ByteException("The /by time of a deadline cant be empty");
        }
        Task task = new Deadline(description, by);
        storage.addTask(task);
        ui.showAddedTask(task, storage.getSize());
    }
}


