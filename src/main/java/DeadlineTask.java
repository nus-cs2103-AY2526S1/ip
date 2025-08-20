/**
 * Encapsulate a deadline task.
 */
class DeadlineTask extends Task {
  /** Time that task need to be done before. */
  private String deadline;

  /**
   * Construct a basic task.
   * 
   * @param name     Name of task to be created.
   * @param deadline Deadline of task to be created.
   */
  public DeadlineTask(String name, String deadline) {
    super(name);
    this.deadline = deadline;
  }

  /**
   * @return String representation of a deadline task.
   */
  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
  }
}
