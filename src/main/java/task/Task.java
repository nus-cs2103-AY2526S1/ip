package task;

import java.util.ArrayList;

import exceptions.InvalidElementInList;

/**
 * Creates a task object for the user.
 * There are 3 classes that child classes for this class.
 */
public class Task {
    protected String descript;
    protected Boolean isFinished;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Creates a task in the constuctor.
     * @param d Description of the task given by the user.
     * @param p Progress of the task.
     * @param tag The tags that the task is associated with.
     * @throws InvalidElementInList Acting on an invalid element in the list.
     */
    public Task(String d, Boolean p, ArrayList<String> tag) throws InvalidElementInList {
        for (int i = 0; i < tag.size(); i++) {
            tags.add(tag.get(i));
        }
        if (d.equals("")) {
            throw new InvalidElementInList();
        }
        this.descript = d;
        this.isFinished = p;
    }


    /**
     * Creates a task in the constuctor.
     * @param d Description of the task given by the user.
     * @param p Progress of the task.
     * @throws InvalidElementInList Acting on an invalid element in the list.
     */
    public Task(String d, Boolean p) throws InvalidElementInList {
        if (descript.equals("")) {
            throw new InvalidElementInList();
        }
        this.descript = d;
        this.isFinished = p;
    }

    /**
     * Creates a task in the constuctor.
     * @param d Description of the task given by the user.
     * @throws InvalidElementInList Acting on an invalid element in the list.
     */
    public Task(String d) throws InvalidElementInList {
        if (descript.equals("")) {
            throw new InvalidElementInList();
        }
        descript = d;
        isFinished = false;
    }

    /**
     * Returns the status of the task at the moment.
     */
    public String getProgressStatus() {
        assert isFinished != null : "progress status is null.";
        if (isFinished.equals(false)) {
            return "O";
        }
        return "X";
    }

    /**
     * Marks the status of the task as being completed.
     */
    public void mark() {
        assert isFinished != null : "progress status is null";
        this.isFinished = true;
    }

    /**
     * Unmarks the status of the task as being completed.
     */
    public void unMark() {
        assert isFinished != null : "progress status is null";
        this.isFinished = false;
    }

    public String toString() {
        return " [" + this.getProgressStatus() + "] " + this.descript + " ";
    }

    public String store() {
        return "[" + this.getProgressStatus() + "]\"\"" + this.descript + "\"\"";
    }

    /**
     * Returns the tags as strings nicely formatted.
     */
    public String taggedStrings() {
        String s = "";
        for (int i = 0; i < tags.size(); i++) {
            s += tags.get(i) + "\"\"";
        }
        return s;
    }

    /**
     * Returns the tags as strings nicely formatted.
     */
    public String taggedToPrint() {
        String s = "";
        for (int i = 0; i < tags.size(); i++) {
            s += tags.get(i) + " ";
        }
        return s;
    }

    /**
     * Add tags to an element to the user's TaskList.
     * @param theTags Any tags the user wants to add to the task.
     */
    public void addTags(String[] theTags) {
        for (int i = 0; i < theTags.length; i++) {
            tags.add(theTags[i]);
        }
    }

    /**
     * Delete/remove tags to elements in the TaskList.
     * @param theTags Any tags the user wants to remove to the task.
     */
    public void removeTags(String[] theTags) {
        for (int i = 0; i < theTags.length; i++) {
            tags.remove(theTags[i]);
        }
        if (theTags.length == 0) {
            tags.clear();
        }
    }

}


