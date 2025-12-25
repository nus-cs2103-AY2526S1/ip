package leo;

import java.util.Scanner;

public class Ui {
    private static String line = "----------------------------------------";
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String showWelcome() {
        System.out.println(line + "\n" + "Hello I'm leo.Leo\n"
                + "What can I do for you?\n" + line);
        return "Hello I'm leo.Leo, what can I do for you?";
    }

    public String goodBye() {
        String prompt = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(line);
        sc.close();
        return prompt;
    }

    public String showMarked(String str) {
        String prompt = "Nice! I've marked this task as done:";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(line);
        return (prompt + "\n" + str);
    }

    public String showUnmarked(String str) {
        String prompt = "Nice! I've marked this task as not done:";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(line);
        return (prompt + "\n" + str);
    }

    public String readCommand() {
        String input = sc.nextLine();
        String trimmed = input.trim();
        return trimmed;
    }

    public String taskAdded(String str, int size) {
        String prompt = "Got it. I've added this task:";
        String list = "Now you have " + size + " tasks in the list.";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(list);
        System.out.println(line);
        return (prompt + "\n" + str + "\n" + list);
    }

    public String taskDeleted(String str, int size) {
        String prompt = "Noted. I've removed this task:";
        String list = "Now you have " + size + " tasks in the list.";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(list);
        System.out.println(line);
        return (prompt + "\n" + str + "\n" + list);
    }

    public String iterate(String str) {
        String prompt = "Here are the tasks in your list";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(line);
        return (prompt + "\n" + str);
    }

    public String find(String str) {
        String prompt = "Here are the tasks in your list";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(line);
        return (prompt + "\n" + str);
    }

    public String showUpcoming(String str, int days) {
        String prompt = "Here are the tasks in the upcoming " + days + " days";
        System.out.println(line);
        System.out.println(prompt);
        System.out.println(str);
        System.out.println(line);
        return (prompt + "\n" + str);
    }

    public String showError(Exception e) {
        System.out.println(line);
        System.out.println(e.getMessage());
        System.out.println(line);
        return e.getMessage();
    }
}
