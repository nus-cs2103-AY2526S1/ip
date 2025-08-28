import java.util.List;
import java.util.ArrayList;

/// The class processes user inputs
///
/// @author Ravichandran Gokul
public class InputProcessor {
    // Declare fields
    private List<Task> listOfTasks;
    private Ui ui;

    /**
     * Constructs a new {@code InputProcessor} object with the task list and the UI object.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param ui          The UI object.
     */
    public InputProcessor(Ui ui) {
        this.listOfTasks = new ArrayList<>();
        this.ui = ui;
    }

    public Command processInput(String input) throws InvalidPromptException, TodoException {
        String[] words = input.split(" ");
        int firstSpaceIndex = input.indexOf(" ");
        String restOfinput = input.substring(firstSpaceIndex + 1);

        if (input.equals("list")) {
            return new DisplayCommand(this.listOfTasks, this.ui);
        } else if (words[0].equals("mark")) {
            int index = Integer.parseInt(words[1]);
            return new MarkCommand(index, this.listOfTasks, this.ui);
        } else if (words[0].equals("unmark")) {
            int index = Integer.parseInt(words[1]);
            return new UnmarkCommand(index, this.listOfTasks, this.ui);
        } else if (words[0].equals("delete")) {
            int index = Integer.parseInt(words[1]);
            return new DeleteCommand(index, this.listOfTasks, this.ui);
        } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")){
            if (words[0].equals("todo") && words.length == 1) {
                throw new TodoException("      YIKES!!! You need to enter a description for a task!!!");
            }
            return new AddCommand(this.listOfTasks, this.ui, restOfinput, words[0]);
        } else {
            throw new InvalidPromptException("     YIKES!!! I do not quite understand what you just said :(");
        }
    }
}
