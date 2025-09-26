package winnie.uitool;

import winnie.chatmessage.Sendable;

public class CliWriter implements UiWriter {
    public void write(Sendable message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + message.getMessageContent());
        System.out.println("    ____________________________________________________________");
    }
}
