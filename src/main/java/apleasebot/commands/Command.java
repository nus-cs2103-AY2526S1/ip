package apleasebot.commands;

import apleasebot.tasks.TaskList;

/**
 * Interface that provides the skeleton of what each Command class should implement
 * Also provides a function to make parsing of inputs easier
 */
public interface Command {
    String execute(TaskList tasks);

    /**
     * Returns a boolean that confirms if the argument can be parsed into an Integer
     * @param s The string argument to be checked
     * @param bIdx The beginning index of the supposed integer
     * @return Boolean value
     */
    default boolean isNotInt(String s, int bIdx) {
        try {
            Integer.parseInt(s.substring(bIdx));
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    default boolean isOutOfBounds(int num, TaskList tasks) {
        return num < 1 || num > tasks.getItemCount();
    }
}
