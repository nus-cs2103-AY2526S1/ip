public class CommandMark extends Command {
    static {
        registry.put("^mark \\d+$", CommandMark::new);
    }

    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        try {
            ui.printMark(taskList.mark(index));
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexError();
        }
    }
}
