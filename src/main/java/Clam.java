public class Clam {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n");
        greet();
        bye();
    }

    public static void greet() {
        String greeting = "Hello! I'm Clam!\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(bye);
    }
}
