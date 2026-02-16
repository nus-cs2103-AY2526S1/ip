package bubbles;

import java.io.IOException;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private String command;

    /**
     * Constructs a {@code UnmarkCommand}.
     *
     * @param command the user's input
     */
    public UnmarkCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the unmark command which unmarks a task which was marked completed
     * based on the index provided. The task is then displayed marked as [ ].
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] words = command.split(" ");

            if (words.length == 1) {
                throw new BubblesException("Oopsie! Add in the index number >u<");
            }

            if (tasks.size() < Integer.parseInt(words[1]) || Integer.parseInt(words[1]) < 1) {
                throw new BubblesException("Eep! There's no such task number!");
            }

            Task task = tasks.get(Integer.parseInt(words[1]) - 1);

            if (task.getStatusIcon().equals("[ ]")) {
                return "Ayyyy! This task is already unmarked! >.<";
            } else {
                try {
                    task.setNotDone();

                    Storage.write(tasks.get(0).toString() + "\n");

                    for (int i = 1; i < tasks.size(); i++) {
                        Task write_task = tasks.get(i);
                        Storage.append(write_task.toString() + "\n");
                    }

                    return ui.showUnmarked(task);

                } catch (IOException e) {
                    return "Error : Something went wrong!";
                }
            }
        } catch (BubblesException e){
            return e.getMessage();
        }
    }
}
