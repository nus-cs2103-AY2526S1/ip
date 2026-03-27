package mochi;

import java.util.ArrayList;

/**
 * Manages the 1-indexed list of tasks created by the user.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private final FileHandler fh;

    /**
     * Returns a TaskList object used to manage Tasks.
     *
     * @param arr an ArrayList containing 0 or more Tasks
     * @param fh the pre-initialized FileHandler object
     */
    public TaskList(ArrayList<Task> arr, FileHandler fh) {
        this.list = arr;
        this.fh = fh;
    }

    /**
     * Adds a task to the list and saves the updated list to the save file.
     *
     * @return Completion output message
     */
    public String add(Task t) {
        list.add(t);
        fh.save(list);
        return String.format("""
            Got it. I've added this task:
                %s
            Now you have %d tasks in  the list.
            """, t.toString(), list.size());
    }

    /**
     * Removes a task from the list by its 1-indexed position and saves the updated list to the save file.
     *
     * @return Completion output message
     */
    public String remove(int taskNumber) {
        assert taskNumber > 0 : "Task numbers should be at least 1.";
        list.remove(taskNumber - 1);
        fh.save(list);
        return "Delete operation successful.";
    }

    /**
     * Adds a tag to a particular task from the list.
     *
     * @return Completion output message
     */
    public String tag(int taskNumber, String tag) {
        String res = list.get(taskNumber - 1).tag(tag);
        fh.save(list);
        return res;
    }

    /**
     * Removes a tag from a particular task from the list.
     *
     * @return Completion output message
     */
    public String untag(int taskNumber, String tag) {
        String res = list.get(taskNumber - 1).untag(tag);
        fh.save(list);
        return res;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return int number of tasks in the list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Marks a task as completed by its 1-indexed position and saves the updated list to the save file.
     *
     * @return Completion output message
     */
    public String complete(int taskNumber) {
        assert taskNumber > 0 : "Task numbers should be at least 1.";
        String res = list.get(taskNumber - 1).mark();
        fh.save(list);
        return res;
    }

    /**
     * Marks a task as uncompleted by its 1-indexed position and saves the updated list to the save file.
     *
     * @return Undo output message
     */
    public String undo(int taskNumber) {
        String res = list.get(taskNumber - 1).unmark();
        fh.save(list);
        return res;
    }

    /**
     * Get a list of tasks that contain the provided word to the user in formatted string format.
     *
     * @param word a word or phrase of alphanumeric characters to be compared with
     */
    public String getTasksWithWord(String word) {
        boolean found = false;
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (!t.descriptionContains(word)) {
                continue;
            }
            if (!found) {
                found = true;
                res = res.concat("Here are the matching tasks in your list: \n");
            }
            res = res.concat(String.format("%d.%s \n", i + 1, t));
        }
        if (found) {
            return res;
        }
        return String.format("There are no tasks that contain the word(s) '%s'", word);
    }

    @Override
    public String toString() {
        String s = """
            Here are the tasks in your list: \n
            """;
        for (int i = 0; i < list.size(); i++) {
            s = s.concat(String.format("%d.%s\n", i + 1, list.get(i).toString()));
        }
        return s;
    }
}
