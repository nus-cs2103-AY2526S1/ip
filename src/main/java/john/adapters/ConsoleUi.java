package john.adapters;

import john.ports.Ui;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUi implements Ui {
    private final Scanner in;
    private final PrintStream out;

    public ConsoleUi(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String nextCommand() {
        while (true) {
            if (!in.hasNextLine()) {
                return null;
            }
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            return line;
        }
    }

    @Override
    public void showMessage(String msg) {
        out.println(msg);
        out.println("_________________________________");
    }

    @Override
    public void close() {
        in.close();
    }

    @Override
    public void showWelcome(int taskCount) {
        out.println("Loaded " + taskCount + " tasks.");
        showMessage("Hello! I'm John. What can I do for you?");
    }
}