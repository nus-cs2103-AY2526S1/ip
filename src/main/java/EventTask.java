/**
 * Encapsulate an event task.
 */
class EventTask extends Task {
  /** Start time of event. */
  private String from;
  /** End time of event. */
  private String to;

  /**
   * Construct a basic task.
   * 
   * @param name Name of task to be created.
   * @param from Start time of event.
   * @param to   End time of event.
   */
  public EventTask(String name, String from, String to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  /**
   * @return String representation of an event task.
   */
  @Override
  public String toString() {
    return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
  }
}
