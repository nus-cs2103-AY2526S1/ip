/**
 * Task class
 */

public class Task {
    // Fields declared
    private String completionStatus;
    private String nameOfTask;

    // Constructor
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
        this.completionStatus = " ";
    }

    // Mark task as done
    public void markAsDone() {
        completionStatus = "X";
        System.out.println("    ____________________________________________________________");
        System.out.println("    Amazing! Insane productivity la keep it up. Marked it as done:");
        System.out.println("        " + this);
        System.out.println("    ____________________________________________________________");
    }

    // Unmark task completion
    public void unmarkTask() {
        completionStatus = " ";
        System.out.println("    ____________________________________________________________");
        System.out.println("    Boooo... Do better next time bro. Marked this as not done yet:");
        System.out.println("        " + this);
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + completionStatus + "] " + nameOfTask;
    }
}
