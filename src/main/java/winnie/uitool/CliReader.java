package winnie.uitool;

import winnie.chatmessage.Readable;
import winnie.chatmessage.ReceivedMessage;

public class CliReader implements UiReader {
    private java.util.Scanner scanner = new java.util.Scanner(System.in);

    public Readable read() {
        try {
            return new ReceivedMessage(scanner.nextLine());
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
            return new ReceivedMessage("Error");
        }
    }
}
