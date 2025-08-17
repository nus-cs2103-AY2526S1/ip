import java.util.Scanner;

public class ChatLoop {
    public ChatLoop() {

    }

    public void printFormattedMessage(String message) {
        String formattedMessage =
                "____________________________________________________________\n " +
                        message +
                "\n____________________________________________________________\n\n";

        System.out.println(formattedMessage);
    }
}
