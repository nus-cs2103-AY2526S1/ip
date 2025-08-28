import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sam {
    private static void printAdded(Task t, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + t);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void printError(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + msg);
        System.out.println("____________________________________________________________");
    }

    private static int parseIndex(String arg, int size) throws SamException {
        if (arg == null || arg.isBlank()) throw new SamException("OOPS!!! Usage: <command> <number>");
        int n = Integer.parseInt(arg);
        int idx = n - 1;
        if (idx < 0 || idx >= size) throw new SamException("OOPS!!! Invalid task number.");
        return idx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> tasks = storage.load();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Sam");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine().trim();

            // split once: parts[0] = verb, parts[1] = rest (args)
            String[] parts = input.split("\\s+", 2);
            String verb = parts[0].toLowerCase();
            String rest = (parts.length > 1) ? parts[1].trim() : "";

            try {
                switch (Command.of(verb)) {
                    case BYE:
                        System.out.println("____________________________________________________________");
                        System.out.println(" Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        sc.close();
                        return;

                    case LIST:
                        System.out.println("____________________________________________________________");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case MARK: {
                        int idx = parseIndex(rest, tasks.size());                        git tag Level-7
                        tasks.get(idx).markDone();
                        storage.save(tasks);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println(" " + tasks.get(idx));
                        System.out.println("____________________________________________________________");
                        break;
                    }

                    case UNMARK: {
                        int idx = parseIndex(rest, tasks.size());
                        tasks.get(idx).unmark();
                        storage.save(tasks);
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println(" " + tasks.get(idx));
                        System.out.println("____________________________________________________________");
                        break;
                    }

                    case DELETE: {
                        int idx = parseIndex(rest, tasks.size());
                        Task removed = tasks.remove(idx);
                        storage.save(tasks);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println(" " + removed);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;
                    }

                    case TODO: {
                        if (rest.isEmpty()) throw new EmptyDescriptionException("todo");
                        tasks.add(new Todo(rest));
                        storage.save(tasks);
                        printAdded(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    }

                    case DEADLINE: {
                        if (rest.isEmpty() || !rest.contains("/by"))
                            throw new SamException("OOPS!!! Use: deadline <description> /by <time>");
                        String[] a = rest.split("/by", 2);
                        String descr = a[0].trim(), by = a[1].trim();
                        if (descr.isEmpty() || by.isEmpty())
                            throw new SamException("OOPS!!! Use: deadline <description> /by <time>");
                        tasks.add(new Deadline(descr, by));
                        storage.save(tasks);
                        printAdded(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    }

                    case EVENT: {
                        if (rest.isEmpty() || !rest.contains("/from") || !rest.contains("/to"))
                            throw new SamException("OOPS!!! Use: event <description> /from <start> /to <end>");
                        String[] p1 = rest.split("/from", 2);
                        String[] p2 = p1[1].split("/to", 2);
                        String descr = p1[0].trim(), from = p2[0].trim(), to = p2[1].trim();
                        if (descr.isEmpty() || from.isEmpty() || to.isEmpty())
                            throw new SamException("OOPS!!! Use: event <description> /from <start> /to <end>");
                        tasks.add(new Event(descr, from, to));
                        storage.save(tasks);
                        printAdded(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    }

                    case UNKNOWN:
                        throw new UnknownCommandException();
                }

            } catch (SamException e) {
                printError(e.getMessage());
            } catch (NumberFormatException e) {
                printError("OOPS!!! Task number must be an integer.");
            }
        }
    }
}
