package benn.tasks;

/**
 * Represents a simple to-do task in Benn the Chatbot.
 *
 * <p>A {@code Todo} has only a description and a completion status.
 * It does not have any associated date or time information.</p>
 */
public class Todo extends Task {

   /**
    * Constructs a new {@code Todo} with the specified description
    * and completion status.
    *
    * @param description the description of the task
    * @param isDone whether the task is marked as completed
    */
   public Todo(String description, boolean isDone) {
      super(description);
      this.isDone = isDone;
   }

   /**
    * Returns a string representation of this to-do task, suitable for
    * display to the user.
    *
    * @return a formatted string of the form {@code [T][ ] description}
    *         or {@code [T][X] description} depending on completion status
    */
   @Override
   public String toString() {
      return String.format("[T]%s", super.toString());
   }

   /**
    * Returns the storage representation of this to-do task, suitable for
    * writing to disk.
    *
    * @return a pipe-delimited string in the format:
    *         {@code T | <done> | <description>}
    */
   @Override
   public String toStorageFormat() {
      return String.format("T | %d | %s", this.getIsDone() ? 1 : 0, this.getDescription());
   }
}
