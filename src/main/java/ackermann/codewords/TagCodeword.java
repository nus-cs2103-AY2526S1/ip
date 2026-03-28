package ackermann.codewords;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidTagTargetException;
import ackermann.functions.TaskList;

/**
 * Handles Tag Codeword
 */
public class TagCodeword extends Codeword {
    private TaskList tasks;
    private String[] newWords;

    /**
     * Constructor for TagCodeword
     * @param tasks Task list that contain task to mark
     * @param newWords id and tag name
     */
    public TagCodeword(TaskList tasks, String[] newWords) {
        this.tasks = tasks;
        this.newWords = newWords;
    }

    @Override
    public String execute() throws CheckedException {
        int idx = tryParse(newWords[0]);
        String tag = newWords[1];
        String output = "Noted bro, I have successfully tagged:\n";
        try {
            this.tasks.tag(idx - 1, tag);
            return output + this.tasks.get(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTagTargetException();
        }
    }

    private int tryParse(String id) throws InvalidTagTargetException {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidTagTargetException();
        }
    }
}
