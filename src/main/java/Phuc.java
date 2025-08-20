import java.util.Scanner;

public class Phuc {
    private final Userinterface Ui;

    public Phuc() {
        this.Ui = new Userinterface();
    }

    public void run() {
        Ui.sayHello();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Ui.echo(input);
            input = sc.nextLine();
        }

        Ui.sayGoodbye();
        sc.close();


    }
}
