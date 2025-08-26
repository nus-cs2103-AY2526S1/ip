public class Ui {
    String LINE = "____________________________________________________________";
    String START_MSG = LINE + "\n" + " Hello! I'm Rafayel ^-^\n" + " What can I do for you?\n" + LINE;
    String END_MSG = " Bye. Hope to see you again soon!\n" + LINE;

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println(START_MSG);
    }

    public void showExit() {
        System.out.println(END_MSG);
    }

    public void showLoadingError() {
        // Rafayel Error
        System.out.println("Loading error!");

    }

    // printNewTaskString

    public void showError(String e) throws RafayelException {
        // System.out.println("Error!");
        throw new RafayelException("Error!\n" + e);
    }

}
