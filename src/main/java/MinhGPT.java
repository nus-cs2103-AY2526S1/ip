import java.util.Scanner;

public class MinhGPT {
  private static void printStartupMessage() {
    String logo = """
          ███    ███ ██ ███    ██ ██   ██  ██████  ██████  ████████
          ████  ████ ██ ████   ██ ██   ██ ██       ██   ██    ██
          ██ ████ ██ ██ ██ ██  ██ ███████ ██   ███ ██████     ██
          ██  ██  ██ ██ ██  ██ ██ ██   ██ ██    ██ ██         ██
          ██      ██ ██ ██   ████ ██   ██  ██████  ██         ██
        """;

    System.out.println("\n\n" + logo + "\n");
    System.out.println("Hello! I'm MinhGPT.\nWhat can I do for you?\n");
  }

  private static void printExitMessage() {
    System.out.println("\nBye. Hope to see you again soon!");
  }

  public static void main(String[] args) {
    printStartupMessage();

    String cmd = "";
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("Your input: ");
      cmd = scanner.next();
      if (cmd.equals("bye")) {
        break;
      } else {
        System.out.println("\n" + cmd + "\n\n");
      }
    }
    scanner.close();

    printExitMessage();
  }
}
