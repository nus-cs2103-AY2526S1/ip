public class ProcessInput {
    UserInterface Ui = new UserInterface();

    public ProcessInput() {

    }

    public void start() {
        Ui.sayHello();
    }

    public void end() {
        Ui.sayGoodbye();
    }

    public void process(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String arg = words.length > 1 ? words[1] : "";

        switch (command) {
            case "event":
                String[] tempwords = arg.split(" /from ", 2);
                String[] day = tempwords[1].split(" /to ", 2);
                Ui.event(tempwords[0], day[0], day[1]);
                break;
            case "deadline":
                String[] tempword = arg.split(" /by ", 2);
                Ui.deadline(tempword[0], tempword[1]);
                break;
            case "todo":
                Ui.toDo(arg);
                break;
            case "list":
                Ui.list();
                break;
            case "unmark":
                Ui.unMark(arg);
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
