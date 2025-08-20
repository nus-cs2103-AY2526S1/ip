class Task {
  private String name;
  private boolean status;

  public Task(String name) {
    this.name = name;
    this.status = false;
  }

  public void markAsDone() {
    this.status = true;
  }

  public void markAsUndone() {
    this.status = false;
  }

  @Override
  public String toString() {
    return String.format("[%c] %s", this.status ? 'X' : 'O', this.name);
  }
}
