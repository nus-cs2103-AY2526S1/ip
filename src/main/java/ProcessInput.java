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

    public void process(String input) throws PhucException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String arg = words.length > 1 ? words[1] : "";

        switch (command) {
            case "event":
                if (arg.isEmpty()) {
                    throw new PhucException("Hold on — an event needs a description. Please type something after 'todo'（>﹏<）");
                }

                String[] tempwords = arg.split(" /from ", 2);

                if (tempwords.length != 2) {
                    throw new PhucException("Oops! An event must include both a start and an end time — don’t forget to add it (ಥ_ಥ)");
                }

                String[] day = tempwords[1].split(" /to ", 2);

                if(day.length != 2) {
                    throw new PhucException("Oops! An event must include both a start and an end time — don’t forget to add it (ಥ_ಥ)");
                }

                Ui.event(tempwords[0], day[0], day[1]);
                break;

            case "deadline":
                if (arg.isEmpty()) {
                    throw new PhucException("Hold on — a deadline needs a description. Please type something after 'todo'（>﹏<）");
                }

                String[] tempword = arg.split(" /by ", 2);

                if(tempword.length < 2) {
                    throw new PhucException("Oops! A deadline must include a date and time — don’t forget to add it (ಥ_ಥ)");
                }

                Ui.deadline(tempword[0], tempword[1]);
                break;

            case "todo":
                if (arg.isEmpty()) {
                    throw new PhucException("Hold on — a todo needs a description. Please type something after 'todo'（>﹏<）");
                }

                Ui.toDo(arg);
                break;

            case "list":
                Ui.list();
                break;

            case "unmark":
                if (arg.isEmpty() || Integer.parseInt(arg) <= 0 || Integer.parseInt(arg) > Ui.numTasks()) {
                    throw new PhucException("Please enter a number between 1 and " + (Ui.numTasks() - 1) +  " (T_T)");
                }

                Ui.unMark(arg);
                break;

            case "mark":
                if (arg.isEmpty() || Integer.parseInt(arg) <= 0 || Integer.parseInt(arg) > Ui.numTasks()) {
                    throw new PhucException("Please enter a number between 1 and " + (Ui.numTasks() - 1) +  " (T_T)");
                }

                Ui.mark(arg);
                break;

            default:
                throw new PhucException("I don’t understand that command. Please try another command! ༼☯﹏☯༽");
        }
    }
}
