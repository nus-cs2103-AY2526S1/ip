package buddy.gui;

import buddy.ui.Ui;

/**
 * Similar to Ui but captures the input instead of printing it
 */

public class UiLogic extends Ui {
    private final StringBuilder out = new StringBuilder();

    @Override
    public void showMessage(String msg) {
        out.append(msg).append("\n");
    }

    @Override
    public void showError(String msg) {
        out.append("Error: ").append(msg).append("\n");
    }

    @Override
    public void showGoodbye() {
        out.append("Bye. Hope to see you again soon!").append("\n");
    }

    public String getMessage() {
        return out.toString().trim();
    }

}
