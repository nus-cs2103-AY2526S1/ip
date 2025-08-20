import java.util.ArrayList;
import java.util.Scanner;

public class MinhGPT {
  private static void printDivider() {
    System.out.println("------------------------------------------");
  }

  private static void printPadding(int size) {
    String str = "";
    for (int i = 0; i < size; i++) {
      str += "\n";
    }
    System.out.print(str);
  }

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
    printDivider();
  }

  private static void printExitMessage() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  private static void program() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Task> list = new ArrayList<>();

    while (true) {
      System.out.print("Your input: ");
      String input = scanner.nextLine().trim();
      printPadding(1);

      if (input.matches("^bye$")) {
        scanner.close();
        break;
      } else if (input.matches("^list$")) {
        for (int i = 0; i < list.size(); i++) {
          System.out.println(String.format("%d.%s", i + 1, list.get(i)));
        }
      } else if (input.matches("^mark \\d+$")) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        list.get(index).markAsDone();
        System.out.println(list.get(index));
      } else if (input.matches("^unmark \\d+$")) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        list.get(index).markAsUndone();
        System.out.println(list.get(index));
      } else {
        list.add(new Task(input));
        System.out.println(String.format("Added: %s", list.get(list.size() - 1)));
      }
      printDivider();
    }
  }

  public static void main(String[] args) {
    printStartupMessage();

    program();

    printExitMessage();
  }
}
