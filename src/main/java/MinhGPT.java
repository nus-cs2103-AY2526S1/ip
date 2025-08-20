import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinhGPT {
  /**
   * Print the divider between each pair of input and response.
   */
  private static void printDivider() {
    System.out.println("------------------------------------------");
  }

  /**
   * Print empty lines.
   * 
   * @param size Number of empty lines to be printed.
   */
  private static void printPadding(int size) {
    String str = "";
    for (int i = 0; i < size; i++) {
      str += "\n";
    }
    System.out.print(str);
  }

  /**
   * Print the message when user first enter the program.
   */
  private static void printStartupMessage() {
    String logo = """
          ███    ███ ██ ███    ██ ██   ██  ██████  ██████  ████████
          ████  ████ ██ ████   ██ ██   ██ ██       ██   ██    ██
          ██ ████ ██ ██ ██ ██  ██ ███████ ██   ███ ██████     ██
          ██  ██  ██ ██ ██  ██ ██ ██   ██ ██    ██ ██         ██
          ██      ██ ██ ██   ████ ██   ██  ██████  ██         ██
        """;
    printPadding(2);
    System.out.print(logo);
    printPadding(2);
    System.out.print("Hello! I'm MinhGPT.\nWhat can I do for you?");
    printPadding(2);
  }

  /**
   * Print the message when user say 'bye'.
   */
  private static void printExitMessage() {
    System.out.println("(╥﹏╥) Bye. Hope to see you again soon!");
  }

  /**
   * Print the error message when the task with that index does not exist for
   * mark / unmark and delete operation.
   */
  private static void printIndexError() {
    System.out.println(
        "<( ⸝⸝•̀ - •́⸝⸝)> There is no tasks with that index. You could have caused an IndexOutOfBoundsException.");
  }

  /**
   * Main program's loop.
   * Keep polling inputs from users until they say 'bye'.
   */
  private static void program() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Task> list = new ArrayList<>();

    while (true) {
      printDivider();
      System.out.print("Your input: ");
      String input = scanner.nextLine().trim();
      printPadding(1);

      if (input.matches("^bye$")) {
        // User want to exit
        scanner.close();
        break;
      } else if (input.matches("^list$")) {
        // List all tasks
        System.out.println(String.format("(˶˃ ᵕ ˂˶) Here are the list of tasks. You have %d in total.", list.size()));
        for (int i = 0; i < list.size(); i++) {
          System.out.println(String.format("%d.%s", i + 1, list.get(i)));
        }
      } else if (input.matches("^mark \\d+$")) {
        // Mark a task as done
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        if (index < 0 || index >= list.size()) {
          printIndexError();
          continue;
        }
        list.get(index).markAsDone();
        System.out.println("(˵˃ ᗜ ˂˵) Congrats on finishing the task.");
        System.out.println(list.get(index));
      } else if (input.matches("^unmark \\d+$")) {
        // Mark a task as undone
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        if (index < 0 || index >= list.size()) {
          printIndexError();
          continue;
        }
        list.get(index).markAsUndone();
        System.out.println("(¬`‸´¬) Huh? Why did you lie?");
        System.out.println(list.get(index));
      } else if (input.matches("^delete \\d+$")) {
        // Delete a task
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        if (index < 0 || index >= list.size()) {
          printIndexError();
        }
        System.out.println(String.format("(˶ᵔ ᵕ ᵔ˶) Removed: %s", list.get(index)));
        list.remove(index);
      } else {
        // All other inputs are considered as trying to add a task
        try {
          list.add(Task.parseTask(input));
          System.out.println(String.format("(˶ᵔ ᵕ ᵔ˶) Added: %s", list.get(list.size() - 1)));
        } catch (ParseException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  /**
   * Main entry-point for the program.
   */
  public static void main(String[] args) {
    printStartupMessage();

    program();

    printExitMessage();
  }
}
