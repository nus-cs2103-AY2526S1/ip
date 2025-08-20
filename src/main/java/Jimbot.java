import java.util.Scanner;

public class Jimbot {
    public static void main(String[] args) {
        String greet = """
                   ____________________________________________________________
                    Hello! I'm Jimbot!
                     (^з^)-☆
                    What can I do for you?
                   ____________________________________________________________
                """;
        System.out.println(greet);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] userList = new Task[100];
        int count = 0;

        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.toLowerCase().contains("bye")) {
                System.out.println("""
                           ____________________________________________________________
                            Bye! Hope to see you again soon!
                            (^O^)／
                           ____________________________________________________________
                        """);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                String list = "    Here are the tasks in your list:";
                for (int i = 0; i < count; i++) {
                    list += "\n    " + (i + 1) + ". " + userList[i];
                }
                System.out.println("   ____________________________________________________________\n" +
                        list + "    ノ( ゜-゜ノ)\n" +
                        "   ____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    userList[index].markAsDone();
                    System.out.println("   ____________________________________________________________\n" +
                            "    Nice! I've marked this task as done:\n" +
                            "       " + userList[index] + "\n" +
                            "     ♪ ｖ（＾＿＾ｖ）♪ ♪（ｖ＾＿＾）ｖ ♪\n" +
                            "   ____________________________________________________________");
                } else {
                    System.out.println("Invalid task number!\n (╥﹏╥)");
                }
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    userList[index].markAsUndone();
                    System.out.println("   ____________________________________________________________\n" +
                            "    (｀_´)ゞ -OK, I've marked this task as not done yet:\n" +
                            "       " + userList[index] + "\n" +
                            "   ____________________________________________________________");
                } else {
                    System.out.println("Invalid task number!\n (╥﹏╥)");
                }
            } else if (userInput.startsWith("deadline")) {

                if (!userInput.contains("/by")) {
                    System.out.println("Invalid deadline format!\n (╯°□°）╯︵ ┻━┻");
                } else {
                    String[] deadline = userInput.substring(9)
                            .trim()
                            .split("/by", 2);
                    String description =  deadline[0].trim();
                    String by = deadline[1].trim();

                    if (count >= 100) {
                        System.out.println("Too many tasks!\n ＞_＜");
                    } else {
                        userList[count] = new Deadline(description, by);
                        System.out.println("   ____________________________________________________________\n" +
                                "    Got it. I've added this task:\n" +
                                "       " + userList[count++] + "\n" +
                                "    Now you have " + count + " tasks in the list.\n" +
                                "   ____________________________________________________________");
                    }
                }
            } else if (userInput.startsWith("event")) {

                if (!userInput.contains("/from") || !userInput.contains("/to")) {
                    System.out.println("Invalid event format...\n ಥ_ಥ");
                } else {
                    String[] event = userInput.substring(6)
                            .trim()
                            .split("/from", 2);

                    String description =  event[0].trim();
                    String[] timings = event[1]
                            .trim()
                            .split("/to", 2);

                    String from = timings[0].trim();
                    String to = timings[1].trim();

                    if (count >= 100) {
                        System.out.println("Too many tasks!\n ＞_＜");
                    } else {
                        userList[count] = new Event(description, from, to);
                        System.out.println("   ____________________________________________________________\n" +
                                "    Got it. I've added this task:\n" +
                                "       " + userList[count++] + "\n" +
                                "    Now you have " + count + " tasks in the list.\n" +
                                "   ____________________________________________________________");
                    }
                }
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5).trim();

                if (count >= 100) {
                    System.out.println("Too many tasks!\n ＞_＜");
                } else {
                    userList[count] = new ToDo(description);
                    System.out.println("   ____________________________________________________________\n" +
                            "    Got it. I've added this task:\n" +
                            "       " + userList[count++] + "\n" +
                            "    Now you have " + count + " tasks in the list.\n" +
                            "   ____________________________________________________________");
                }
            } else {
                System.out.println("   ____________________________________________________________\n" +
                        "    " + userInput + "\n    ʕ •ᴥ•ʔ     ʕ•ᴥ• ʔ\n" +
                        "   ____________________________________________________________");
            }
        }
        scanner.close();
    }
}
