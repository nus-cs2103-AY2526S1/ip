public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        if (description == null || description.trim().isEmpty()) {
            throw new ByteException("The description of an event cant be empty");
        }
        if (from == null || from.trim().isEmpty()) {
            throw new ByteException("The /from time of an event cant be empty");
        }
        if (to == null || to.trim().isEmpty()) {
            throw new ByteException("The /to time of an event cant be empty");
        }
        Task task = new Event(description, from, to);
        storage.addTask(task);
        ui.showAddedTask(task, storage.getSize());
    }
}


