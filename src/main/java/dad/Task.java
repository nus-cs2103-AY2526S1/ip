package dad;

public abstract class Task {

    private String task;
    private char done = ' ';

    public Task(String task) {
        this.task = task;
    }

    /** 
     * Marks this task as done and returns the appropriate response
     */
    public String mark() {
        String out = Ui.printLine() + "\n";
        this.done = 'X';
        out += Ui.print("  So you say huh?");
        out += Ui.print("  " + this);
        return out + Ui.printLine();
    }

    /**
     * Marks this task as not-done and returns the appropriate response
     */
    public String unmark() {
        String out = Ui.printLine() + "\n";
        this.done = ' ';
        out += Ui.print("  If you say so...");
        out += Ui.print("  " + this);
        return out + Ui.printLine();
    }

    /**
     * Returns the main body of the task
     */
    public String taskName() {
        return task;
    }

    /**
     * Returns the serialized version of this task for storage purposes
     */
    public String toRecord() {
        return this.task;
    }

    @Override
    public String toString() {
        return "[" + this.done + "] " + this.task;
    }
}
