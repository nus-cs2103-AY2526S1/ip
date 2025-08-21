import taskTypes.Task;
import taskTypes.taskList;

public class Response {

    public void markRes(taskList userList, int index) {
        String padding = "  ";
        int inputLength = userList.getTask(index).toString().length();
        int maxLength = 37;
        String box = "─";

        if (inputLength > 37) {
            maxLength = inputLength;
        }

        for (int i = 0; i < maxLength + 6 ; i++) box += "─";
        int spaceCount = maxLength - inputLength;
        String topBorder = "            ┌" + box + "┐\n";
        String bottomBorder = "            └" + box + "┘\n";
        String header = "            │ Nice! I've marked this task as done:";

        for (int i = 0; i < spaceCount; i++) padding += " ";

        int headerPadding = maxLength - 37 + 6;
        for (int i = 0; i < headerPadding; i++) header += " ";
        header += " │\n";

        System.out.println(topBorder +
                header +
                "            │     " + userList.getTask(index) + padding + "│\n" +
                bottomBorder +
                "♪ ｖ（＾＿＾ｖ）♪ ~~ ♪（ｖ＾＿＾）ｖ ノ");
    }

    public void unmarkRes(taskList userList, int index) {
        String padding = "  ";
        int inputLength = userList.getTask(index).toString().length();
        int maxLength = 43;
        String box = "─";

        if (inputLength > 43) {
            maxLength = inputLength;
        }

        for (int i = 0; i < maxLength + 6 ; i++) box += "─";
        int spaceCount = maxLength - inputLength;
        String topBorder = "     ┌" + box + "┐\n";
        String bottomBorder = "     └" + box + "┘\n";
        String header = "     │ OK, I've marked this task as not done yet:";

        for (int i = 0; i < spaceCount; i++) padding += " ";

        int headerPadding = maxLength - 43 + 6;
        for (int i = 0; i < headerPadding; i++) header += " ";
        header += " │\n";

        System.out.println(topBorder +
                header +
                "     │     " + userList.getTask(index) + padding + "│\n" +
                bottomBorder + " (｀_´)ゞ ノ"
        );
    }

    public void addTaskRes(Task task, int taskCount) {
        int taskLength = task.toString().length();
        int maxLength = 35;
        String box = "─";

        if (taskLength > 35) {
            maxLength = taskLength;
        }

        for (int i = 0; i < maxLength + 6 ; i++) box += "─";
        String topBorder = "     ┌" + box + "┐\n";
        String bottomBorder = "     └" + box + "┘\n";
        String header = "     │ Got it. I've added this task:";
        String closer = "     │ Now you have " + taskCount + " tasks in the list.";

        String padding1 = "  ";
        for (int i = 0; i < maxLength - 25; i++) padding1 += " ";
        String padding2 = "  ";
        for (int i = 0; i < maxLength - taskLength; i++) padding2 += " ";
        String padding3 = "  ";
        for (int i = 0; i < maxLength - 29; i++) padding3 += " ";

        System.out.println(topBorder +
                header + padding1 + "│\n" +
                "     │     " + task + padding2 + "│\n" +
                closer + padding3 + "│\n" +
                bottomBorder
        );

    }

    public void printList(Task[] list) {
        int maxLength = 0;
        int taskCount = list.length;
        String box = "─";
        for (Task task : list) {
            if (task != null && task.toString().length() > maxLength) {
                maxLength = task.toString().length();
            }
        }
        if (maxLength < 32) maxLength = 32;

        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = list[i];
            if (task != null) {
                int padding = maxLength - task.toString().length();
                String spaces = "";
                for (int j = 0; j < padding + 3; j++) spaces += " ";
                listContent += "\n │ " + i + ". " + task + spaces + "│";
            }
        }

        for (int i = 0; i < maxLength + 6; i++) {
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
    }

    public void hello(String name) {
        String textBox =  "─";
        int length = name.length();
        int maxLength = 26;
        if  (length > 12) maxLength = length + 13;
        for (int i = 0; i < maxLength; i++) {
            textBox += "─";
        }
        String padding1 = "";
        for (int i = 0; i < maxLength - length - 12; i++) padding1 += " ";
        String padding2 = "";
        for (int i = 0; i < maxLength - 22; i++) padding2 += " ";

        System.out.println(
                "          ┌" + textBox + "┐\n" +
                        "          │ Hello! I'm " + name +"!" + padding1 + "│\n" +
                        "          │ What can I do for you?"  + padding2 + "│\n" +
                        " (^з^)-☆ ╱└" + textBox + "┘");
    }

    public void goodBye() {
        System.out.println("""
                             ┌────────────────────────────────────┐
                             │ Bye! Hope to see you again soon!   │
                    \\(^O^)  ╱└────────────────────────────────────┘
                    """);
    }

    public void echo(String userInput) {
        String textBox =  "─";
        for (int i = 0; i < userInput.length() + 6; i++) {
            textBox += "─";
        }
        System.out.println("                   ┌" + textBox + "┐\n" +
                "                   │ " + userInput + "      │\n" +
                "ʕ •ᴥ•ʔ     ʕ•ᴥ• ʔ ╱└" + textBox + "┘");
    }
}
