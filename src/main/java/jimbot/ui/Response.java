package jimbot.ui;

import java.util.List;

import jimbot.tasktypes.Task;
import jimbot.tasktypes.TaskList;

/**
 * Old class that handles the UI of programs' responses to user.
 *
 * @author limjimin-nus
 * */
public class Response {

    public void markRes(TaskList userList, int index) {
        int inputLength = userList.getTask(index)
                .toString()
                .length();
        int maxLength = 37;
        String box = "─";
        String padding = "  ";

        if (inputLength > 37) {
            maxLength = inputLength;
        }

        for (int i = 0; i < maxLength + 6; i++) {
            box += "─";
        }

        int spaceCount = maxLength - inputLength;
        String topBorder = "            ┌" + box + "┐\n";
        String bottomBorder = "            └" + box + "┘\n";
        String header = "            │ Nice♪ I've marked this task as done:";

        for (int i = 0; i < spaceCount; i++) {
            padding += " ";
        }

        int headerPadding = maxLength - 37 + 6;
        for (int i = 0; i < headerPadding; i++) {
            header += " ";
        }
        header += " │\n";

        System.out.println(topBorder + header
                + "            │     " + userList.getTask(index) + padding + "│\n"
                + bottomBorder
                + "♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ ╯");
    }

    public void unmarkRes(TaskList userList, int index) {
        int inputLength = userList.getTask(index).toString().length();
        int maxLength = 43;
        String box = "─";
        String padding = "  ";

        if (inputLength > 43) {
            maxLength = inputLength;
        }

        for (int i = 0; i < maxLength + 6; i++) {
            box += "─";
        }

        int spaceCount = maxLength - inputLength;
        String topBorder = "     ┌" + box + "┐\n";
        String bottomBorder = "     └" + box + "┘\n";
        String header = "     │ OK, I've marked this task as not done yet:";

        for (int i = 0; i < spaceCount; i++) {
            padding += " ";
        }

        int headerPadding = maxLength - 43 + 6;
        for (int i = 0; i < headerPadding; i++) {
            header += " ";
        }
        header += " │\n";

        System.out.println(topBorder + header
                + "     │     " + userList.getTask(index) + padding + "│\n"
                + bottomBorder + " (｀_´)ゞ ╯");
    }

    public void addTask(Task task, int taskCount) {
        int taskLength = task.toString().length();
        int maxLength = 36;
        String box = "─";

        if (taskLength > 36) {
            maxLength = taskLength;
        }

        for (int i = 0; i < maxLength + 6; i++) {
            box += "─";
        }

        String topBorder = "     ┌" + box + "┐\n";
        String bottomBorder = "     └" + box + "┘\n";
        String header = "     │ Got it. I've added this task:";
        String closer = "     │ Now you have " + taskCount + " tasks in the list!";
        String padding1 = "  ";
        String padding2 = "  ";
        String padding3 = "  ";

        for (int i = 0; i < maxLength - 25; i++) {
            padding1 += " ";
        }

        for (int i = 0; i < maxLength - taskLength; i++) {
            padding2 += " ";
        }

        if (taskCount >= 10) {
            for (int i = 0; i < maxLength - 30; i++) {
                padding3 += " ";
            }
        } else {
            for (int i = 0; i < maxLength - 29; i++) {
                padding3 += " ";
            }
        }

        System.out.println(topBorder + header + padding1 + "│\n"
                + "     │     " + task + padding2 + "│\n"
                + closer + padding3 + "│\n"
                + bottomBorder + " (￣^￣)ゞ ╯");
    }

    public void deleteTask(Task task, int taskCount) {
        int taskLength = task.toString().length();
        int maxLength = 36;
        String box = "─";

        if (taskLength > 36) {
            maxLength = taskLength;
        }

        for (int i = 0; i < maxLength + 6; i++) {
            box += "─";
        }
        String topBorder = "     ┌" + box + "┐\n";
        String bottomBorder = "     └" + box + "┘\n";
        String header = "     │ Noted. I've removed this task:";
        String closer = "     │ Now you have " + taskCount + " tasks in the list!";
        String padding1 = "  ";
        String padding2 = "  ";
        String padding3 = "  ";

        for (int i = 0; i < maxLength - 26; i++) {
            padding1 += " ";
        }

        for (int i = 0; i < maxLength - taskLength; i++) {
            padding2 += " ";
        }

        if (taskCount >= 10) {
            for (int i = 0; i < maxLength - 30; i++) {
                padding3 += " ";
            }
        } else {
            for (int i = 0; i < maxLength - 29; i++) {
                padding3 += " ";
            }
        }

        System.out.println(topBorder + header + padding1 + "│\n"
                + "     │     " + task + padding2 + "│\n"
                + closer + padding3 + "│\n"
                + bottomBorder + " (─.─)ゞ ╯");
    }

    public void printList(List<Task> list) {
        int maxLength = 32;
        int taskCount = list.size();
        String box = "─";

        for (Task task : list) { // compares maxLength with every task in the list
            if (task.toString().length() > maxLength) {
                maxLength = task.toString().length();
            }
        }

        String listContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = list.get(i);
            int padding = maxLength - task.toString().length();
            String spaces = "";

            for (int j = 0; j < padding + 3; j++) {
                spaces += " ";
            }

            if (i <= 8) { //adjusts for the extra digit when count reaches 10
                listContent += "\n │ " + (i + 1) + ". " + task + spaces + " │";
            } else {
                listContent += "\n │ " + (i + 1) + ". " + task + spaces + "│";
            }
        }

        for (int i = 0; i < maxLength + 7; i++) {
            box += "─";
        }

        String topBorder = " ┌" + box + "┐\n";
        String bottomBorder = " └" + box + "┴ ノ( ゜-゜ノ)";

        String header = " │ Here are the tasks in your list:";
        int headerPadding = maxLength - 26;
        for (int i = 0; i < headerPadding; i++) {
            header += " ";
        }
        header += " │";

        System.out.println(topBorder + header + listContent + "\n" + bottomBorder);
    }

    /**
     * Prints a the lsit of tasks at a specific date.
     *
     * @param list List of tasks with the same dates.
     * @param isToday If specific date matches LocalDate.now(), string response changes to mention that.
     */
    public void printListAtDate(List<Task> list, boolean isToday) {
        int maxLength = 34;
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

            for (int j = 0; j < padding + 4; j++) {
                spaces += " ";
            }

            if (i <= 8) {
                listContent += "\n         │ " + (i + 1) + ". " + task + spaces + " │";
            } else {
                listContent += "\n         │ " + (i + 1) + ". " + task + spaces + "│";
            }
        }

        String header;
        if (isToday) {
            header = "         │ Here are your tasks for today:";

            for (int i = 0; i < maxLength + 8; i++) {
                box += "─";
            }

            int headerPadding = maxLength - 23;
            for (int i = 0; i < headerPadding; i++) {
                header += " ";
            }
        } else {
            header = "         │ Here are your tasks for that date:";

            for (int i = 0; i < maxLength + 8; i++) {
                box += "─";
            }

            int headerPadding = maxLength - 27;
            for (int i = 0; i < headerPadding; i++) {
                header += " ";
            }
        }

        String topBorder = "         ┌" + box + "┐\n";
        String bottomBorder = " (・ω・)ノ└" + box + "┘";
        header += " │";

        System.out.println(topBorder + header + listContent + "\n" + bottomBorder);
    }

    public void hello(String name) {
        int length = name.length();
        int maxLength = 26;
        String textBox = "─";
        String padding1 = "";
        String padding2 = "";

        if (length > 12) {
            maxLength = length + 13;
        }

        for (int i = 0; i < maxLength; i++) {
            textBox += "─";
        }

        for (int i = 0; i < maxLength - length - 12; i++) {
            padding1 += " ";
        }

        for (int i = 0; i < maxLength - 22; i++) {
            padding2 += " ";
        }

        System.out.println("          ┌" + textBox + "┐\n"
                + "          │ Hello! I'm " + name + "!" + padding1 + "│\n"
                + "          │ What can I do for you?" + padding2 + "│\n"
                + " (^з^)-☆ ─┴" + textBox + "┘");
    }

    public void goodBye() {
        System.out.println("""
                            ┌────────────────────────────────────┐
                            │ Bye! Hope to see you again soon!   │
                    \\(^O^) ─┴────────────────────────────────────┘
                    """);
    }

    /**
     * Response for unrecognized commands.
     */
    public void respond(String userInput) {
        int maxLength = 46;
        int length = userInput.length();
        String textBox = "─";
        String padding1 = "";
        String padding2 = "";
        String padding3 = "";

        if (length > 40) {
            maxLength = length + 2;
        }

        for (int i = 0; i < maxLength + 2; i++) {
            textBox += "─";
        }

        for (int i = 0; i < maxLength - 36; i++) {
            padding1 += " ";
        }

        for (int i = 0; i < maxLength - length - 2; i++) {
            padding2 += " ";
        }

        for (int i = 0; i < maxLength - 41; i++) {
            padding3 += " ";
        }

        System.out.println("                     ┌" + textBox + "┐\n"
                + "                     │ Sorry I don't recognize this command: " + padding1 + "│ \n"
                + "                     │   \"" + userInput + "\"" + padding2 + "│\n"
                + "                     │ Type \"help\" for the list possible commands." + padding3 + "│ \n "
                + " ʕ •ᴥ•ʔ     ʕ•ᴥ• ʔ ─┴" + textBox + "┘");
    }

    /**
     * Prints a list of all possible commands.
     */
    public void commandList() {
        System.out.print("""
                             ┌─────────────────────────────────────────┐
                             │ Here are the commands you can use:      │
                             │   (input date in dd/mm/yyyy)            │
                             │   bye/goodbye                           │
                             │   deadline                              │
                             │   delete                                │
                             │   event                                 │
                             │   find                                  │
                             │   list                                  │
                             │   mark                                  │
                             │   today                                 │
                             │   todo                                  │
                             │   unmark                                │
                             └─────────────────────────────────────────┴─  _(•̀ω•́ 」∠)_
                            """);
    }
}