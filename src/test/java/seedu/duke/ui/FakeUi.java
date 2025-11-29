package seedu.duke.ui;

/**
 * Test stub of {@link Ui} that captures the last shown message
 * instead of printing it. Used for unit tests.
 */
public class FakeUi extends Ui {
    private String lastShown = "";

    /**
     * Records the last message instead of displaying it.
     *
     * @param s the message to "show"
     */
    @Override
    public void show(String s) {
        lastShown = s;
    }

    /**
     * Returns the last message that was recorded.
     *
     * @return the last shown string
     */
    public String getLastShown() {
        return lastShown;
    }
}
