public class CommandList extends Command {
    static {
        registry.put("^list$", CommandList::new);
    }

    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
