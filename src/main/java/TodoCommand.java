public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        if (description == null || description.trim().isEmpty()) {
            throw new ByteException("Note that the description of a todo cannot be empty");
        }
        Task task = new Todo(description);
        storage.addTask(task);
        ui.showAddedTask(task, storage.getSize());
    }
}


