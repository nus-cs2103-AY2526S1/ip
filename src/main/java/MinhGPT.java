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
    String cmd = "";
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> list = new ArrayList<>();

    while (true) {
      System.out.print("Your input: ");
      cmd = scanner.nextLine();
      printPadding(1);
      switch (cmd) {
        case "bye":
          scanner.close();
          return;
        case "list":
          for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, list.get(i)));
          }
          break;
        default:
          list.add(cmd);
          System.out.println("Added: " + cmd);
          break;
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
