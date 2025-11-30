package dyke.ui;

/**
 * FakeUi created for testing
 */
public class FakeUi extends Ui {
    private String lastMessage = "";
    @Override
    public void printMessage(String msg) {
        lastMessage = msg;
    }

    /**
     * Gets LastMessage
     * @return {@code String} lastMessage
     */
    public String getLastMessage() {
        return lastMessage;
    }
}
