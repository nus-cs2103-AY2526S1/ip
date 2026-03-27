package fish.ui;

public class BufferingUi extends Ui {

    private static final String DEFAULT_GREETING = "__><(((º>\n I am Fish AKA your capable task manager "
            + "\n "
            + "\n You can add your tasks with these commands"
            + "\n 1. todo <desc>"
            + "\n 2. deadline <desc> /by <YYYY-MM-DD>"
            + "\n 3. event <desc> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>";

    private final StringBuilder sb = new StringBuilder();

    @Override
    public void printIn(String s) {
        sb.append(s).append("\n");
    }
    @Override
    public void showError(String msg) {
        sb.append("OOPS!!! ").append(msg).append("\n");
    }
    @Override
    public void showExit() {
        sb.append("See you next timeee\n");
    }
    @Override public void showLine() { }

    @Override
    public void showWelcome() {
        sb.append(DEFAULT_GREETING).append("\n");
    }

    public String flush() {
        String s = sb.toString();
        sb.setLength(0);
        return s.isEmpty() ? "" : s.stripTrailing();
    }
}
