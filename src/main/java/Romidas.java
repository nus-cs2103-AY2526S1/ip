import java.util.ArrayList;
import java.util.Scanner;

public class Romidas {
    private static final String DATA_PATH = "romidas.txt";

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        ArrayList<Task> store = storage.loadTasks(DATA_PATH);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Romidas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) break;
            try {
                System.out.println("____________________________________________________________");
                String[] words = input.trim().split("\\s+");
                String cmdWord = words[0].toUpperCase();
                Command cmd = Command.valueOf(cmdWord);
                Task task = null;

                switch (cmd) {
                case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < store.size(); i++) {
                            Task t = store.get(i);
                            System.out.println((i + 1) + "." + t.toString());
                        }
                        break;

                case MARK:
                    if (words.length != 2)
                        throw new RomidasException("Should follow the format: mark <Task Number>");
                    int indexMark = Integer.parseInt(words[1]) - 1;
                    if (indexMark < 0 || indexMark >= store.size())
                        throw new InvalidIndexException();
                    System.out.println("Nice! I've marked this task as done:");
                    Task tMark = store.get(indexMark);
                    tMark.setIsDone(true);
                    System.out.println("  " + tMark.toString());

                    break;

                case UNMARK:
                    if (words.length != 2)
                        throw new RomidasException("Should follow the format: unmark <Task Number>");
                    int indexUnmark = Integer.parseInt(words[1]) - 1;
                    if (indexUnmark < 0 || indexUnmark >= store.size())
                        throw new InvalidIndexException();
                    System.out.println("OK, I've marked this task as not done yet:");
                    Task tUnmark = store.get(indexUnmark);
                    tUnmark.setIsDone(false);
                    System.out.println("  " + tUnmark.toString());

                    break;

                case TODO:
                    if (words.length < 2) throw new RomidasException("todo requires a description");
                    if (input.length() < 6) throw new RomidasException("todo requires a description");
                    String description = input.trim().substring(5);
                    task = new TodoTask(description);
                    break;

                case DEADLINE:
                    if (words.length < 2)
                        throw new RomidasException("deadline tasks should follow the format: deadline <task> /by <date/time>");
                    if (input.length() <= 9)
                        throw new RomidasException("deadline tasks should follow the format: deadline <task> /by <date/time>");
                    String subDeadline = input.substring(9);
                    String[] partsDeadline = subDeadline.split(" /by ");
                    if (partsDeadline.length < 2 || partsDeadline[0].isBlank() || partsDeadline[1].isBlank())
                        throw new RomidasException("deadline tasks should follow the format: deadline <task> /by <date/time>");
                    task = new DeadlineTask(partsDeadline[0] + " (by: " + partsDeadline[1] + ")", partsDeadline[1]);
                    break;

                case EVENT:
                    if (words.length < 2)
                        throw new RomidasException("event tasks should follow the format: event <event name> /from <date/time> /to <date/time>");
                    if (input.length() <= 6)
                        throw new RomidasException("event tasks should follow the format: event <event name> /from <date/time> /to <date/time>");
                    String subEvent = input.substring(6);
                    String[] partsEvent = subEvent.split(" /from ");
                    if (partsEvent.length < 2 || partsEvent[0].isBlank())
                        throw new RomidasException("event tasks should follow the format: event <event name> /from <date/time> /to <date/time>");
                    String fromAndTo = partsEvent[1];
                    String[] timeParts = fromAndTo.split(" /to ");
                    if (timeParts.length < 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
                        throw new RomidasException("event tasks should follow the format: event <event name> /from <date/time> /to <date/time>");
                    }
                    task = new Event(partsEvent[0] + " (from: " + timeParts[0] + " to: " + timeParts[1] + ")", timeParts[0], timeParts[1]);
                    break;

                case DELETE:
                    if (words.length < 2) {
                        throw new RomidasException("Should follow the format: delete <Task Number>");
                    }
                    int index =  Integer.parseInt(words[1]) - 1;
                    if (index < 0 || index >= store.size()) {
                        throw new InvalidIndexException();
                    }
                    Task dele = store.get(index);
                    System.out.println("Noted. I've removed this task:");
                    store.remove(index);
                    System.out.println("  " + dele.toString());
                    System.out.println("Now you have " + store.size() + " tasks in your list.");

                }

                if (task != null) {
                    System.out.println("Got it. I've added this task:");
                    store.add(task);
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + store.size() + " tasks in your list.");

                }
                storage.saveTasks(DATA_PATH, store);

            } catch (NumberFormatException e) {
                System.out.println("Task number must be an integer.");
            } catch (IllegalArgumentException e) {
                System.out.println("I'm sorry, I don't recognise that command. Try one of list, event, todo, deadline, mark, unmark, delete");
            } catch (RomidasException e) {
                System.out.println(e.getMessage());
            }
            finally {

                System.out.println("____________________________________________________________");
            }

        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Romidas romidas = new Romidas();
        romidas.run();
    }
}