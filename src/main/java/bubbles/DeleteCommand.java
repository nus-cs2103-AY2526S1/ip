package bubbles;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command{
    private String command;

    /**
     * Constructs a {@code DeleteCommand} based on the index given in the command.
     *
     * @param command the user input for the deletion
     */
    public DeleteCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the deadline command which splits the descriptions and deadline.
     * It also adds the task to the hard disk.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            String[] words = command.split(" ");

            if (words.length == 1) {
                throw new BubblesException("Oopsie! Add in the index number >u<");
            }

            if (tasks.size() < Integer.parseInt(words[1]) || Integer.parseInt(words[1]) < 1){
                throw new BubblesException("Eep! There's no such task number!");
            }

            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            tasks.remove(Integer.parseInt(words[1]) - 1);

            if(!tasks.isEmpty()) {
                Storage.write(tasks.get(0).toString() + "\n");

                for (int i = 1; i < tasks.size(); i++) {
                    Task write_task = tasks.get(i);
                    Storage.append(write_task.toString() + "\n");
                }
            }

            return ui.showDelete(task, tasks.size());

        } catch (IOException e){
                return "Error : Something went wrong!";
        } catch (BubblesException e){
            return e.getMessage();
        }
    }
}
