package eve.ui;

import java.util.Scanner;
import java.util.List;

import eve.tasks.Task;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public String renderWelcome() {
        return String.join("\n",
                "Hiii, Eve here~ (≧▽≦)✧",
                "What shall we do today? ♡");
    }

    public String readCommand() {
        if (!sc.hasNextLine()) {
            return null; // EOF
        }
        return sc.nextLine();
    }

    public String renderGoodbye() {
        return String.join("\n",
                "Bye bye~! Take care and come back soon, okay? (｡•́︿•̀｡)ﾉﾞ");
    }

    public String renderUnknown() {
        return String.join("\n",
                "Ehh? I don’t quite get that… (・・;) Maybe try 'help' so I can guide you?");
    }

    public String renderError(String msg) {
        return String.join("\n", " " + msg);
    }

    public String renderAdded(Task t, int count) {
        return String.join("\n",
                "Yay~! I've added this to your list:",
                "   " + t.toString(),
                " Now you have " + count + (count == 1
                        ? " little task to look after. ♡"
                        : " tasks to keep track of! ✨"));
    }

    public String renderMarked(Task t, boolean done) {
        return String.join("\n",
                done
                        ? "Ooo nice~! Task completed, good job! (๑˃ᴗ˂)و"
                        : "Got it! I've set this task back to 'not done'~ (｡•̀ᴗ-)✧",
                "   " + t.toString());
    }

    public String renderDeleted(Task t, int size) {
        return String.join("\n",
                "Poof~! Task deleted. (╯✧▽✧)╯",
                "   " + t.toString(),
                " Now you’ve got " + size + (size == 1
                        ? " task left, easy peasy~ ♡"
                        : " tasks left to handle!"));
    }

    public String renderList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return String.join("\n",
                    "Hehe, your list is totally empty right now~ (⁀ᗢ⁀) Want to add something?");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here’s everything I’m keeping track of for you:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public String renderMatches(List<Task> matches) {
        if (matches.isEmpty()) {
            return String.join("\n", "Hmm… no matching tasks found (｡•́︿•̀｡)");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nHere are the matching tasks I found for you:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String renderHelp() {
        return String.join("\n",
                "Here are the commands you can ask me to do~ (｡•ᴗ-)✧:",
                "   help      - Show this help message.",
                "   list      - Show all tasks and status.",
                "   find <kw> - Search tasks by keyword.",
                "   todo <d>  - Add a cute little ToDo.",
                "   deadline <d> /by <t> - Add a Deadline.",
                "   event <d> /from <s> /to <e> - Add an Event.",
                "   mark N    - Mark task N as done ✓",
                "   unmark N  - Mark task N as not done ✗",
                "   delete N  - Delete task N (poof~!)",
                "   bye       - Exit the program.");
    }

    public void showHelp() {
        System.out.println(renderHelp());
    }

    public void showGoodbye() {
        System.out.println(renderGoodbye());
    }

    public void showFindResults(List<Task> matches) {
        System.out.println(renderMatches(matches));
    }

    public void showList(List<Task> tasks) {
        System.out.println(renderList(tasks));
    }

    public void showDeleted(Task removed, int newCount) {
        System.out.println(renderDeleted(removed, newCount));
    }

    public void showAdded(Task t, int count) {
        System.out.println(renderAdded(t, count));
    }

    public void showMarked(Task t, boolean toDone) {
        System.out.println(renderMarked(t, toDone));
    }

    public void showError(String msg) {
        System.out.println(renderError(msg));
    }

    public void showUnknown() {
        System.out.println(renderUnknown());
    }

    public void showWelcome() {
        System.out.println(renderWelcome());
    }

}
