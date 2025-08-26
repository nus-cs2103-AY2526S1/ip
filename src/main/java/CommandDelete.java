public class CommandDelete extends Command {
    static {
        registry.put("^delete \\d+$", CommandDelete::new);
    }

    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        try {
            ui.printDelete(taskList.delete(index));
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexError();
        }
    }
}
