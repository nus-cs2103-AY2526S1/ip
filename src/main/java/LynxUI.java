import java.util.ArrayList;

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

}
