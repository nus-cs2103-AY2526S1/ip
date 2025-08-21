public class Processinput {
    Userinterface Ui = new Userinterface();

    public Processinput() {

    }

    public void start() {
        Ui.sayHello();
    }

    public void end() {
        Ui.sayGoodbye();
    }

    public void Process(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String arg = words.length > 1 ? words[1] : "";

        switch (command) {
            case "list":
                Ui.list();
                break;
            case "unmark":
                Ui.unmark(arg);
                break;
            case "mark":
                Ui.mark(arg);
                break;
            default:
                Ui.add(input);
                break;
        }
    }
}
