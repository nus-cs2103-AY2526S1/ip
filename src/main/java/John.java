import java.util.Scanner;
import java.util.Arrays;

public class John {
    private static final Task[] list = new Task[100];
    private static int counter = 0;

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

    private static boolean ensureCapacity() {
        if (counter >= list.length) {
            System.out.println("Sorry, the task list is full (" + list.length + ").");
            System.out.println("____________________________________________________________");
            return false;
        }
        return true;
    }

    private static Integer parseIndex(String token) {
        try {
            int idx = Integer.parseInt(token) - 1; // user-facing 1-based
            if (idx < 0 || idx >= counter) return null;
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
                    if (counter == 0) {
                        System.out.println("Your list is empty.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < counter; i++) {
                            System.out.println((i + 1) + ". " + list[i]);
                        }
                    }
                    break;

                case "mark": {
                    if (cmd.length < 2) {
                        System.out.println("Usage: mark <task-number>");
                        break;
                    }
                    Integer idx = parseIndex(cmd[1]);
                    if (idx == null) {
                        System.out.println("Please provide a valid task number between 1 and " + counter + ".");
                        break;
                    }
                    list[idx].markAsComplete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[idx]);
                    break;
                }

                case "unmark": {
                    if (cmd.length < 2) {
                        System.out.println("Usage: unmark <task-number>");
                        break;
                    }
                    Integer idx = parseIndex(cmd[1]);
                    if (idx == null) {
                        System.out.println("Please provide a valid task number between 1 and " + counter + ".");
                        break;
                    }
                    list[idx].markAsIncomplete();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list[idx]);
                    break;
                }

                case "todo": {
                    if (!ensureCapacity()) break;
                    if (cmd.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("Usage: todo <description>");
                        break;
                    }
                    String todoDesc = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)).trim();
                    if (todoDesc.isEmpty()) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        break;
                    }
                    list[counter++] = new Todo(todoDesc);
                    System.out.println("added: " + todoDesc);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    break;
                }

                case "deadline": {
                    if (!ensureCapacity()) break;
                    if (cmd.length < 2) {
                        System.out.println("Usage: deadline <description> /by <when>");
                        break;
                    }
                    String dFull = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)).trim();
                    int byPos = dFull.lastIndexOf(" /by ");
                    if (byPos == -1) {
                        System.out.println("Usage: deadline <description> /by <when>");
                        break;
                    }
                    String dDesc = dFull.substring(0, byPos).trim();
                    String dWhen = dFull.substring(byPos + 5).trim();
                    if (dDesc.isEmpty() || dWhen.isEmpty()) {
                        System.out.println("Usage: deadline <description> /by <when>");
                        break;
                    }
                    list[counter++] = new Deadline(dDesc, dWhen);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[counter - 1]);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    break;
                }

                case "event": {
                    if (!ensureCapacity()) break;
                    if (cmd.length < 2) {
                        System.out.println("Usage: event <description> /from <start> /to <end>");
                        break;
                    }
                    String eFull = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)).trim();
                    int fromPos = eFull.lastIndexOf(" /from ");
                    int toPos = eFull.lastIndexOf(" /to ");
                    if (fromPos == -1 || toPos == -1 || toPos <= fromPos) {
                        System.out.println("Usage: event <description> /from <start> /to <end>");
                        break;
                    }
                    String eDesc = eFull.substring(0, fromPos).trim();
                    String eFrom = eFull.substring(fromPos + 7, toPos).trim();
                    String eTo = eFull.substring(toPos + 5).trim();
                    if (eDesc.isEmpty() || eFrom.isEmpty() || eTo.isEmpty()) {
                        System.out.println("Usage: event <description> /from <start> /to <end>");
                        break;
                    }
                    list[counter++] = new Event(eDesc, eFrom, eTo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list[counter - 1]);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    break;
                }

                default: {
                    if (!ensureCapacity()) break;
                    String desc = String.join(" ", cmd).trim();
                    if (desc.isEmpty()) {
                        System.out.println("Nothing to add.");
                        break;
                    }
                    list[counter++] = new Task(desc);
                    System.out.println("added: " + list[counter - 1]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Hmm, that command was missing arguments. Try 'list', 'todo', 'deadline', 'event', 'mark', or 'unmark'.");
        } catch (NullPointerException e) {
            System.out.println("That task number doesn't exist yet.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("____________________________________________________________");
        }
    }
}