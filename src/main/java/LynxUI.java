public class LynxUI {

    private static final String LINE = "____________________________________________________________";

    public static void line() {
        System.out.println(LINE);
    }

    public static void printBox(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void hello() {
        LynxUI.printBox("Hello! I'm Tasklynx. \n" +
                "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
    }

    public static void bye() {
        LynxUI.printBox("Goodbye. I'll be here whenever you need to stay on track.");
    }

}
