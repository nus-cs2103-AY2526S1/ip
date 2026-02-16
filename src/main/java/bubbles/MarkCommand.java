package bubbles;

import java.io.IOException;

/**
 * Represents a command to mark a task as completed
 */
public class MarkCommand extends Command {
    private String command;

    /**
     * Constructs a {@code MarkCommand}.
     *
     * @param command the user's input
     */
    public MarkCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the mark command which marks a task as completed based on
     * the index provided. The task is then displayed marked with an "X".
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

            if (tasks.size() < Integer.parseInt(words[1]) || Integer.parseInt(words[1]) < 1){
                throw new BubblesException("Eep! There's no such task number!");
            }

            Task task = tasks.get(Integer.parseInt(words[1]) - 1);

            if (task.getStatusIcon().equals("[X]")) {
                return "Ayyyy! This task is already marked! >.<";
            } else {
                try {
                    task.setDone();

                    Storage.write(tasks.get(0).toString() + "\n");

                    for (int i = 1; i < tasks.size(); i++) {
                        Task write_task = tasks.get(i);
                        Storage.append(write_task.toString() + "\n");
                    }

                    return ui.showMarked(task);
                } catch (IOException e) {
                    return "Error : Something went wrong!";
                }
            }
        } catch (BubblesException e){
            return e.getMessage();
        }
    }
}