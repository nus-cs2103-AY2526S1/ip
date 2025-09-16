package tinkerton.util;

import java.util.ArrayList;

public class StubUi extends Ui {
    private ArrayList<String> printedMessages = new ArrayList<>();

    @Override
    public void print(String message) {
        printedMessages.add(message);
    }

    public ArrayList<String> getPrintedMessages() {
        return printedMessages;
    }

    public String getLastPrintedMessage() {
        if (printedMessages.isEmpty()) {
            return null;
        }
        return printedMessages.get(printedMessages.size() - 1);
    }
}
