public class BeeBong {
    private static final String newLine = "____________________________________________________________";

    public static void BotMessage(String message) {
        System.out.println(newLine);
        System.out.println(message);
    }

    public static void ExitMessage() {
        System.out.println(newLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(newLine);
    }

    public static void main(String[] args) {
        BotMessage("Hello! I'm B. Bong\nWhat can I do for you?");
        ExitMessage();
    }
}
