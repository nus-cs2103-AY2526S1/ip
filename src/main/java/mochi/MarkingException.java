package mochi;

/**
 * Extends MochiException to handle errors related to marking and unmarking tasks.
 */
public class MarkingException extends MochiException {
    private final String inputProvided;
    private final int listRange;

    /**
     * Returns an exception that contains details regarding why the task marking
     * or unmarking went wrong.
     *
     * @param inputProvided what input parameters the user typed
     * @param listRange the size of the Task List
     */
    public MarkingException(String inputProvided, int listRange) {
        super();
        this.inputProvided = inputProvided;
        this.listRange = listRange;
    }

    @Override
    public String toString() {
        if (listRange == 0) {
            return String.format(super.toString() + "\n" + "There are no items in the list to mark or unmark.");
        }
        return String.format(super.toString() + "\n" + """
             Marking or unmarking tasks require a valid task number
             between the range of 1 and %d.
             Invalid input provided was: %s
             """, this.listRange, this.inputProvided);
    }
}
