package candy;

import tasks.TaskList;

/**
 * Represents a Candy chatbox.
 */
public class Candy {
    private TaskList taskList;

    /**
     * Constructs a Candy.
     *
     * @param filePath The path of the storage file.
     */
    public Candy(String filePath) {
        this.taskList = new TaskList(filePath);
    }

    /**
     * Returns string dialog by candy bot
     */
    public String getResponse(String input) {
        return Parser.parse(input.trim(), taskList);
    }
}
