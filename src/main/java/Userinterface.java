public class Userinterface {
    private final String line = "____________________________________________________________";
    private final String userinfo[];
    private Integer count = 0;

    public Userinterface() {
        userinfo = new String[100];
    }

    public void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void sayHello() {
        print(" Hello! I'm Phuc\n" +
                " What can I do for you?");
    }

    public void sayGoodbye() {
        print(" Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        print(input);
    }

    public void addlist(String input) {
        if (input.equals("list")) {
            printlist();
        }
        else {
            userinfo[count] = input;
            print("added: " + input);
            count++;
        }
    }

    public void printlist() {
        String output = "";
        for (int i = 0; i < count; i++) {
            String num = Integer.toString(i+1);
            output += num + ". " + userinfo[i] + "\n";
        }
        System.out.println(line);
        System.out.print(output);
        System.out.println(line);
    }
}
