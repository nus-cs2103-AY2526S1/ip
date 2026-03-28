package ackermann.codewords;

import java.util.ArrayList;
import java.util.List;

import ackermann.exceptions.InvalidFindException;
import ackermann.functions.TaskList;
import ackermann.task.Task;

/**
 * Handles Find Codeword
 */
public class FindCodeword extends Codeword {
    private TaskList tasks;
    private String keyword;

    /**
     * Constructor for Find Codeword
     * @param tasks
     * @param keyword keyword to match the name of tasks
     */
    public FindCodeword(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    /**
     * Finds tasks of which substring of names is equals to keyword
     * @return A string of tasks
     */
    @Override
    public String execute() throws InvalidFindException {
        List<Task> results = new ArrayList<>();
        StringBuilder output = new StringBuilder();

        output.append("Ok bro, I've helped you to search and here are the matching tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.getName().toUpperCase().contains(keyword.toUpperCase())) {
                results.add(task);
            }
        }

        for (int i = 0; i < results.size(); i++) {
            output.append(results.get(i).toString());

            if (i != results.size() - 1) {
                output.append("\n");
            }
        }

        if (results.isEmpty()) {
            throw new InvalidFindException();
        } else {
            return String.valueOf(output);
        }
    }
}
