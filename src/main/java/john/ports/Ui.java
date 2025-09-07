package john.ports;

public interface Ui extends AutoCloseable {
    String nextCommand();        // read input, split
    void showMessage(String msg);  // print output
    void showWelcome(int taskCount);
}