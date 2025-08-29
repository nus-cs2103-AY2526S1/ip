package phuc;

import phuc.parser.ProcessInput;
import phuc.exception.PhucException;
import phuc.ui.UserInterface;

import java.util.Scanner;

public class Phuc {
    private final ProcessInput pi;

    public Phuc() {
        this.pi = new ProcessInput();
    }

    public void run() throws PhucException {
        pi.start();
        pi.load();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    pi.end();
                    break;
                }
                pi.process(input);
            } catch (PhucException e) {
                UserInterface.showError(e.getMessage());
            }
        }
    }
}
