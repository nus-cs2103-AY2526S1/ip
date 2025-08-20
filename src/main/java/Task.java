abstract class Task {
  private String name;
  private boolean status;

  public static Task parseTask(String input) {
    if (input.matches("^todo .+")) {
      return new TodoTask(input.substring(5));
    } else if (input.matches("^deadline .+ /by .+")) {
      String[] split = input.substring(9).split(" /by ");
      return new DeadlineTask(split[0], split[1]);
    } else {
      String[] split1 = input.substring(6).split(" /from ");
      String[] split2 = split1[1].split(" /to ");
      return new EventTask(split1[0], split2[0], split2[1]);
    }
  }

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
    return String.format("[%c] %s", this.status ? 'X' : ' ', this.name);
  }
}
