import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class John {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        command(new String[] {"start"});
        try (Scanner listen = new Scanner(System.in)) {
            while (true) {
                String line = listen.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] cmd = line.split("\\s+");
                command(cmd);
                if (cmd[0].equals("bye")) break;
            }
        }
    }

    private static Integer parseIndex(String token) {
        try {
            int idx = Integer.parseInt(token) - 1; // user-facing 1-based
            if (idx < 0 || idx >= tasks.size()) return null;
            return idx;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void command(String[] cmd) {
        try {
            switch (cmd[0]) {
                case "start":
                    System.out.println("Hello! I'm John");
                    System.out.println("What can I do for you?");
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("Your list is empty.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    break;

                case "mark": {
                    if (cmd.length < 2) { System.out.println("Usage: mark <task-number>"); break; }
                    Integer idx = parseIndex(cmd[1]);
                    if (idx == null) { System.out.println("Please provide a valid task number between 1 and " + tasks.size() + "."); break; }
                    tasks.get(idx).markAsComplete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(idx));
                    break;
                }

                case "unmark": {
                    if (cmd.length < 2) { System.out.println("Usage: unmark <task-number>"); break; }
                    Integer idx = parseIndex(cmd[1]);
                    if (idx == null) { System.out.println("Please provide a valid task number between 1 and " + tasks.size() + "."); break; }
                    tasks.get(idx).markAsIncomplete();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(idx));
                    break;
                }

                case "delete": {
                    if (cmd.length < 2) { System.out.println("Usage: delete <task-number>"); break; }
                    Integer idx = parseIndex(cmd[1]);
                    if (idx == null) { System.out.println("Please provide a valid task number between 1 and " + tasks.size() + "."); break; }
                    Task removed = tasks.remove((int) idx);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                case "todo": {
                    if (cmd.length < 2) { System.out.println("Usage: todo <description>"); break; }
                    String todoDesc = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)).trim();
                    tasks.add(new Todo(todoDesc));
                    System.out.println("added: " + todoDesc);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                case "deadline": {
                    if (cmd.length < 2) { System.out.println("Usage: deadline <description> /by <when>"); break; }
                    String dFull = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)).trim();
                    int byPos = dFull.lastIndexOf(" /by ");
                    if (byPos == -1) { System.out.println("Usage: deadline <description> /by <when>"); break; }
                    String dDesc = dFull.substring(0, byPos).trim();
                    String dWhen = dFull.substring(byPos + 5).trim();
                    if (dDesc.isEmpty() || dWhen.isEmpty()) { System.out.println("Usage: deadline <description> /by <when>"); break; }
                    tasks.add(new Deadline(dDesc, dWhen));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                case "event": {
                    if (cmd.length < 2) { System.out.println("Usage: event <description> /from <start> /to <end>"); break; }
                    String eFull = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)).trim();
                    int fromPos = eFull.lastIndexOf(" /from ");
                    int toPos = eFull.lastIndexOf(" /to ");
                    if (fromPos == -1 || toPos == -1 || toPos <= fromPos) { System.out.println("Usage: event <description> /from <start> /to <end>"); break; }
                    String eDesc = eFull.substring(0, fromPos).trim();
                    String eFrom = eFull.substring(fromPos + 7, toPos).trim();
                    String eTo = eFull.substring(toPos + 5).trim();
                    if (eDesc.isEmpty() || eFrom.isEmpty() || eTo.isEmpty()) { System.out.println("Usage: event <description> /from <start> /to <end>"); break; }
                    tasks.add(new Event(eDesc, eFrom, eTo));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }

                default: {
                    System.out.println("No such command, try: 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', or 'delete'");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Hmm, that command was missing arguments. Try 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', or 'delete'.");
        } catch (NullPointerException e) {
            System.out.println("That task number doesn't exist yet.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("____________________________________________________________");
        }
    }
}