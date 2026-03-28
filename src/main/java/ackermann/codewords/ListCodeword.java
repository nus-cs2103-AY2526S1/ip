package ackermann.codewords;

import ackermann.functions.TaskList;

/**
 * Handles List Codeword
 */
public class ListCodeword extends Codeword {
    private TaskList tasks;

    /**
     * Constructor for ListCodeword
     * @param tasks task list to list
     */
    public ListCodeword(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * something
     * @return A displayable string
     */
    @Override
    public String execute() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list bro:").append("\n");

        for (int i = 0; i < this.tasks.size(); i++) {
            int curr = i + 1;

            str.append(curr).append(". ").append(tasks.get(i));
            if (i != this.tasks.size() - 1) {
                str.append("\n");
            }
        }

        return String.valueOf(str);
    }
}
