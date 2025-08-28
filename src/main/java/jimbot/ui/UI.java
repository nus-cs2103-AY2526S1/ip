package jimbot.ui;

//class containing all jimbot responses
import jimbot.tasktypes.Task;
import jimbot.tasktypes.TaskList;

import java.util.List;

public class UI {

    public void markRes(TaskList userList, int index) {
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
        String header = "            │ Nice♪ I've marked this task as done:";

        for (int i = 0; i < spaceCount; i++) padding += " ";

        int headerPadding = maxLength - 37 + 6;
        for (int i = 0; i < headerPadding; i++) header += " ";
        header += " │\n";

        System.out.println(topBorder +
                header +
                "            │     " + userList.getTask(index) + padding + "│\n" +
                bottomBorder +
                "♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ ╯");
    }

    public void unmarkRes(TaskList userList, int index) {
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

    public void addTask(Task task, int taskCount) {
        int taskLength = task.toString().length();
        int maxLength = 36;
        String box = "─";

        if (taskLength > 36) {
            maxLength = taskLength;
        }

        for (int i = 0; i < maxLength + 6 ; i++) box += "─";
        String topBorder = "            ┌" + box + "┐\n";
        String bottomBorder = " ─┴" + box + "┘\n";
        String header = "            │ Got it. I've added this task:";
        String closer = "            │ Now you have " + taskCount + " tasks in the list!";

        String padding1 = "  ";
        for (int i = 0; i < maxLength - 25; i++) padding1 += " ";
        String padding2 = "  ";
        for (int i = 0; i < maxLength - taskLength; i++) padding2 += " ";
        String padding3 = "  ";
        if (taskCount >= 10) {
            for (int i = 0; i < maxLength - 30; i++) padding3 += " ";
        } else {
            for (int i = 0; i < maxLength - 29; i++) padding3 += " ";
        }

        System.out.println(topBorder +
                header + padding1 + "│\n" +
                "            │     " + task + padding2 + "│\n" +
                closer + padding3 + "│\n" +
                " (￣^￣)ゞ" + bottomBorder
        );

    }

    public void printList(List<Task> list) {
        int maxLength = 32;
        int taskCount = list.size();
        String box = "─";
        for (Task task : list) {
            if (task.toString().length() > maxLength) {
                maxLength = task.toString().length();
            }
        }

        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = list.get(i);
            int padding = maxLength - task.toString().length();
            String spaces = "";
            for (int j = 0; j < padding + 3; j++) spaces += " ";
            if (i <= 8) {
                listContent += "\n │ " + (i + 1) + ". " + task + spaces + " │";
            } else {
                listContent += "\n │ " + (i + 1) + ". " + task + spaces + "│";
            }
        }

        for (int i = 0; i < maxLength + 7; i++) box += "─";

        String header = " │ Here are the tasks in your list:";
        int headerPadding = maxLength - 26;
        for (int i = 0; i < headerPadding; i++) header += " ";

        String topBorder = " ┌" + box + "┐\n";
        String bottomBorder = " └" + box + "┴ ノ( ゜-゜ノ)";
        header += " │";
        System.out.println(topBorder +
                header + listContent + "\n" +
                bottomBorder);
    }

    public void printListAtDate(List<Task> list, boolean isToday) {
        int maxLength = 34;
        int taskCount = list.size();
        String box = "─";
        for (Task task : list) {
            if (task.toString().length() > maxLength) {
                maxLength = task.toString().length();
            }
        }

        String listContent = getString(list, taskCount, maxLength);

        String header;
        if (isToday) {
            for (int i = 0; i < maxLength + 8; i++) box += "─";

            header = "          │ Here are your tasks for today:";
            int headerPadding = maxLength - 23;
            for (int i = 0; i < headerPadding; i++) header += " ";

        } else {
            for (int i = 0; i < maxLength + 8; i++) box += "─";

            header = "          │ Here are your tasks for that date:";
            int headerPadding = maxLength - 27;
            for (int i = 0; i < headerPadding; i++) header += " ";

        }

        String topBorder = "          ┌" + box + "┐\n";
        String bottomBorder = " (・ω・)ノ└" + box + "┘";
        header += " │";
        System.out.println(topBorder +
                header + listContent + "\n" +
                bottomBorder);
    }

    private static String getString(List<Task> list, int taskCount, int maxLength) {
        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = list.get(i);
            int padding = maxLength - task.toString().length();
            String spaces = "";
            for (int j = 0; j < padding + 4; j++) spaces += " ";
            if (i <= 8) {
                listContent += "\n          │ " + (i + 1) + ". " + task + spaces + " │";
            } else {
                listContent += "\n          │ " + (i + 1) + ". " + task + spaces + "│";
            }
        }
        return listContent;
    }

    public void deleteTask(Task task, int taskCount) {
        int taskLength = task.toString().length();
        int maxLength = 36;
        String box = "─";

        if (taskLength > 36) {
            maxLength = taskLength;
        }

        for (int i = 0; i < maxLength + 6 ; i++) box += "─";
        String topBorder = "            ┌" + box + "┐\n";
        String bottomBorder = " ─┴" + box + "┘\n";
        String header = "            │ Noted. I've removed this task:";
        String closer = "            │ Now you have " + taskCount + " tasks in the list!";

        String padding1 = "  ";
        for (int i = 0; i < maxLength - 26; i++) padding1 += " ";
        String padding2 = "  ";
        for (int i = 0; i < maxLength - taskLength; i++) padding2 += " ";
        String padding3 = "  ";
        if (taskCount >= 10) {
            for (int i = 0; i < maxLength - 30; i++) padding3 += " ";
        } else {
            for (int i = 0; i < maxLength - 29; i++) padding3 += " ";
        }

        System.out.println(topBorder +
                header + padding1 + "│\n" +
                "            │     " + task + padding2 + "│\n" +
                closer + padding3 + "│\n" +
                " (￣^￣)ゞ" + bottomBorder
        );
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
                        " (^з^)-☆ ─┴" + textBox + "┘");
    }

    public void goodBye() {
        System.out.println("""
                            ┌────────────────────────────────────┐
                            │ Bye! Hope to see you again soon!   │
                    \\(^O^) ─┴────────────────────────────────────┘
                    """);
    }

    public void echo(String userInput) {
        String textBox =  "─";
        for (int i = 0; i < userInput.length() + 6; i++) {
            textBox += "─";
        }
        System.out.println("                   ┌" + textBox + "┐\n" +
                "                   │ " + userInput + "      │\n" +
                "ʕ •ᴥ•ʔ     ʕ•ᴥ• ʔ ─┴" + textBox + "┘");
    }
}
