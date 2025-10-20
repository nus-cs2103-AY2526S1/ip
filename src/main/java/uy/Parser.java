package uy;

/**
 * Parser processes textual commands and runs the requested actions
 * against the provided TaskList / UI / Storage.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Parse a command message and perform the corresponding action.
     *
     * @param message raw command string
     * @param tasks task list to operate on
     * @param ui ui helper used to format responses
     * @param storage storage used for persistence (may be unused for some commands)
     * @return response string to show to the user
     * @throws Exception for command or parsing errors
     */
    public String parseAndRun(String message, TaskList tasks, UI ui, Storage storage) throws Exception {
        String type = message.split(" ")[0];

        String remaining = message.substring(type.length()).trim();

        if (type.equals("list")) {
            return ui.showTasks(tasks);

        } else if (type.equals("bye")) {
            return ui.showGoodbye();

        } else if (type.equals("mark")) {
            if (remaining.length() == 0) {
                throw new MissingInputError("mark requires an index");
            }
            int x = Integer.parseInt(remaining);
            if (x <= 0 || x > tasks.getTaskCount()) {
                throw new IndexOutOfBoundsException("Index out of range for mark");
            }
            tasks.markTask(x - 1);
            return ui.showMarkedTask(tasks.getTask(x - 1));

        } else if (type.equals("unmark")) {
            if (remaining.length() == 0) {
                throw new MissingInputError("unmark requires an index");
            }
            int x = Integer.parseInt(remaining);
            if (x <= 0 || x > tasks.getTaskCount()) {
                throw new IndexOutOfBoundsException("Index out of range for unmark");
            }
            tasks.unmarkTask(x - 1);
            return ui.showUnmarkedTask(tasks.getTask(x - 1));

        } else if (type.equals("todo")) {
            if (remaining.length() == 0) {
                throw new MissingInputError("todo requires a description");
            }
            ToDos newTodo = new ToDos(remaining);
            tasks.addTask(newTodo);
            return ui.showAddTask(newTodo, tasks);

        } else if (type.equals("deadline")) {
            tasks.addTask(new Deadlines(remaining));
            return ui.showAddTask(new Deadlines(remaining), tasks);

        } else if (type.equals("event")) {
            tasks.addTask(new Events(remaining));
            return ui.showAddTask(new Events(remaining), tasks);

        } else if (type.equals("delete")) {
            if (remaining.length() == 0) {
                throw new MissingInputError("delete requires an index");
            }
            int index = Integer.parseInt(remaining);
            if (index <= 0 || index > tasks.getTaskCount()) {
                throw new IndexOutOfBoundsException("Index out of range for delete");
            }
            String res = ui.showDeleteTask(tasks.getTask(index - 1), tasks);
            tasks.deleteTask(tasks.getTask(index - 1));
            return res;

        } else if (type.equals("find")) {
            String keyword = remaining;
            TaskList results = tasks.findTasks(keyword);
            return ui.showMatchingTasks(results);

        } else {
            return ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
