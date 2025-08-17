import java.util.Scanner;

public class TaskLynx {

    private static final String NAME = "TaskLynx";
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        hello();
        scanForCommands();
        bye();
    }

    public static void hello() {
        System.out.println(LINE);
        System.out.println("Hello! I'm TaskLynx.");
        System.out.println("Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
        System.out.println(LINE);
        System.out.println("How can I assist you with your tasks today?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println("Goodbye. I’ll be here whenever you need to stay on track.");
        System.out.println(LINE);
    }

    }

}

