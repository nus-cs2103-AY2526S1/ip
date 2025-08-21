import java.util.Scanner;

public class Jimbot {
    public static void main(String[] args) {
        String greet = """
                        ┌──────────────────────────┐
                        │ Hello! I'm Jimbot!       │
                        │ What can I do for you?   │
               (^з^)-☆ ╱└──────────────────────────┘
               """;
        System.out.println(greet);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] userList = new Task[100];
        int count = 0;

        while (true) {
            userInput = scanner.nextLine().trim();
            String box = "─";
            int maxLength = 0;

            if (userInput.toLowerCase().contains("bye")) {
                System.out.println("""
                             ┌────────────────────────────────────┐
                             │ Bye! Hope to see you again soon!   │
                    \\(^O^)  ╱└────────────────────────────────────┘
                    """);
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                for (Task task : userList) {
                    if (task != null && task.toString().length() > maxLength) {
                        maxLength = task.toString().length();
                    }
                }
                if (maxLength < 32) maxLength = 32;

                String listContent = "";
                for (int i = 0; i < count; i++) {
                    Task task = userList[i];
                    if (task != null) {
                        int padding = maxLength - task.toString().length();
                        String spaces = "";
                        for (int j = 0; j < padding + 3; j++) spaces += " ";
                        listContent += "\n │ " + (i + 1) + ". " + task + spaces + "│";
                    }
                }

                for (int i = 0; i < maxLength + 6 ; i++) {
                    box += "─";
                }

                String header = " │ Here are the tasks in your list:";
                int headerPadding = maxLength - 32 + 6;
                for (int i = 0; i < headerPadding; i++) header += " ";
                header += "│";

                String topBorder = " ┌" + box + "┐\n";
                String bottomBorder = " └" + box + "┘ ノ( ゜-゜ノ)";
                System.out.println(topBorder +
                        header + listContent + "\n" +
                        bottomBorder);

            } else if (userInput.startsWith("mark")) {
                String padding = "  ";
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                int inputLength = userList[index].toString().length();

                if (inputLength < 37) {
                    maxLength = 37;
                } else {
                    maxLength = inputLength;
                }

                for (int i = 0; i < maxLength + 6 ; i++) box += "─";
                int spaceCount = maxLength - userList[index].toString().length();
                String topBorder = "            ┌" + box + "┐\n";
                String bottomBorder = "            └" + box + "┘\n";
                String header = "            │ Nice! I've marked this task as done:";

                if (index < count) {
                    for (int i = 0; i < spaceCount; i++) padding += " ";

                    int headerPadding = maxLength - 37 + 6;
                    for (int i = 0; i < headerPadding; i++) header += " ";
                    header += " │\n";

                    userList[index].markAsDone();
                    System.out.println(topBorder +
                            header +
                            "            │     " + userList[index] + padding + "│\n" +
                            bottomBorder +
                            "♪ ｖ（＾＿＾ｖ）♪ ~~ ♪（ｖ＾＿＾）ｖ ノ");
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
                String textBox =  "─";
                for (int i = 0; i < userInput.length() + 6; i++) {
                    textBox += "─";
                }
                System.out.println("                   ┌" + textBox + "┐\n" +
                        "                   │ " + userInput + "      │\n" +
                        "ʕ •ᴥ•ʔ     ʕ•ᴥ• ʔ ╱└" + textBox + "┘");
            }
        }
        scanner.close();
    }
}
