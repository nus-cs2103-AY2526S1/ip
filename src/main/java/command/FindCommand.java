package command;

import amogus.FileStorage;
import amogus.UI;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents finding a task through an input keyword.
 */
public class FindCommand implements Command {

    private String wordToFind;

    /**
     * Creates the find command to search for tasks
     * with given word.
     * @param word word to be searched in tasks.
     */
    public FindCommand(String word) {
        this.wordToFind = word;
        assert word != null : "unable to search for empty word";
    }

    /**
     * Searches for keyword from list of tasks and outputs
     * new list with tasks that match the keyword.
     * @param tasks list of tasks
     * @param ui ui
     * @param f list of task txt file
     * @return message of completed task
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) {
        TaskList tasksWithWord = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(wordToFind.toLowerCase())) {
                tasksWithWord.add(task);
            }
        }

        if (tasksWithWord.isEmpty()) {
            return ui.showMsg("Empty List. \n");
        } else {
            return ui.showTaskList(tasksWithWord);
        }
    }

    /**
     * For the program to know when to exit the conversation with user.
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
