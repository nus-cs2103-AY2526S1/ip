/**
 * Encapsulate a todo task.
 */
class TodoTask extends Task {
  /**
   * Construct a basic task.
   * 
   * @param name Name of task to be created.
   */
  public TodoTask(String name) {
    super(name);
  }

  /**
   * @return String representation of a todo task.
   */
  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }
}
