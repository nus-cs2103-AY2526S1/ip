/**
 * Represents a deadline task, which has a description
 * and a specific due date/time.
 */

package salah;

import java.time.LocalDateTime;

public class Deadlines extends Task {
   private final LocalDateTime by;

   /**
    * Constructs a new deadline task.
    *
    * @param description description of the task
    * @param by date/time by which the task is due
    */
   public Deadlines(String description, LocalDateTime by) {
      super(description);
      this.by = by;
   }

   /**
    * Returns the due date/time of this deadline task.
    *
    * @return the {@code LocalDateTime} by which this task is due
    */
   public LocalDateTime getBy() {
      return this.by;
   }

   /**
    * Parses a user input string into a {@code Deadlines} task.
    *
    * @param input the raw input string (must contain "/by")
    * @return a new {@code Deadlines} task
    * @throws EmptyDescriptionException if the description or deadline part is empty
    * @throws TaskFormattingException if the input does not contain "/by"
    */
   public static Deadlines parser(String input) throws EmptyDescriptionException, TaskFormattingException {
      if (!input.contains("/by")) {
         throw new TaskFormattingException("Invalid deadline format. Make sure to include /by");
      } else {
         // Assumption: input starts with "deadline"
         assert input.toLowerCase().startsWith("deadline") : "Input must start with 'deadline' keyword";
         
         String[] parts = input.substring(8).split("/by", 2);

         // Assumption: parts must have exactly 2 elements: {description, byString}
         assert parts.length == 2 : "Deadline input should split into exactly 2 parts";

         String description = parts[0].trim();
         String byString = parts[1].trim();

         if (description.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
         } else if (byString.isEmpty()) {
            throw new EmptyDescriptionException("Deadline cannot be empty.");
         } else {
            return new Deadlines(description, DateTimeParser.parseFlexible(byString));
         }
      }
   }

   /**
    * Returns the string representation of this deadline task.
    *
    * @return string representation of the task
    */
   @Override
   public String toString() {
      String var10000 = this.getIsComplete() ? "[X] " : "[ ] ";
      return "[D]" + var10000 + super.toString() + " (by: " + DateTimeParser.formatPretty(this.by) + ")";
   }

   /**
    * Serializes this deadline task into a format suitable for saving to disk.
    *
    * @return serialized string representation of the task
    */
   @Override
   public String serialize() {
      String var10000 = this.getIsComplete() ? "1" : "0";
      return "D | " + var10000 + " | " + this.getDescription() + " | " + String.valueOf(this.by);
   }

   /**
    * Reconstructs a deadline task from serialized data parts.
    *
    * @param parts string array containing serialized task data
    * @return a reconstructed {@code Deadlines} task
    */
   public static Deadlines fromData(String[] parts) {
      boolean done = parts[1].trim().equals("1");
      String description = parts[2].trim();
      LocalDateTime by = LocalDateTime.parse(parts[3].trim());
      Deadlines deadline = new Deadlines(description, by);
      if (done) {
         deadline.markAsComplete();
      }
      return deadline;
   }
}