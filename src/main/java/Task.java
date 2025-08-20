import java.text.ParseException;

/**
 * Base class for a task.
 */
abstract class Task {
  /** Name of task. */
  private String name;
  /** If status is true, task is done. Otherwise, task is not done. */
  private boolean status;

  /**
   * Factory method for creating a task.
   * 
   * @param input Input from user to create a task.
   * @throws ParseException When the input string does not match any known input
   *                        patterns.
   */
  public static Task parseTask(String input) throws ParseException {
    if (input.matches("^todo .+")) {
      // Todo task
      return new TodoTask(input.substring(5));
    } else if (input.matches("^deadline .+ /by .+")) {
      // Deadline task
      String[] split = input.substring(9).split(" /by ");
      return new DeadlineTask(split[0], split[1]);
    } else if (input.matches("^event .+ /from .+ /to .+")) {
      // Event task
      String[] split1 = input.substring(6).split(" /from ");
      String[] split2 = split1[1].split(" /to ");
      return new EventTask(split1[0], split2[0], split2[1]);
    } else {
      // None of the above
      throw new ParseException("( ˶°ㅁ°) That is not a valid way to input a task!", 0);
    }
  }

  /**
   * Construct a basic task.
   * 
   * @param name Name of task to be created.
   */
  public Task(String name) {
    this.name = name;
    this.status = false;
  }

  /**
   * Mark the task as done.
   */
  public void markAsDone() {
    this.status = true;
  }

  /**
   * Mark the task as undone.
   */
  public void markAsUndone() {
    this.status = false;
  }

  /**
   * @return Simple String representation of a task.
   */
  @Override
  public String toString() {
    return String.format("[%c] %s", this.status ? 'X' : ' ', this.name);
  }
}
