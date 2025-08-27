import java.util.Scanner;
import Exceptions.*;

public class EvansBot {

    private static int parseIndex(String input, int taskCount) throws InvalidTaskIndexException {
        try {
            int index = Integer.parseInt(input.trim());
            if (index <= 0 || index > taskCount) {
                throw new InvalidTaskIndexException(taskCount);
            }
            return index;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(taskCount);
        }
    }

    public static void main(String[] args) {
        Greet greeter = new Greet("EvansBot");
        Exit exiter = new Exit();
        TaskList tasks = new TaskList(100); //size 100 array
        Scanner scanner = new Scanner(System.in);
        String input;

        greeter.greet();
        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    exiter.sayBye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    tasks.listTasks();
                } else if (input.startsWith("mark")) {
                    String[] inputs = input.trim().split(" ");
                    if (inputs.length < 2) {
                        throw new InvalidTaskIndexException(tasks.getCount());
                    }
                    //if int after mark is not within the number of tasks, throws InvalidTaskIndexException
                    int index = parseIndex(inputs[1], tasks.getCount());
                    tasks.markTask(index);
                } else if (input.startsWith("unmark")) {
                    String[] inputs = input.trim().split(" ");
                    if (inputs.length < 2) {
                        throw new InvalidTaskIndexException(tasks.getCount());
                    }
                    //if int after unmark is not within the number of tasks, throws InvalidTaskIndexException
                    int index = parseIndex(inputs[1], tasks.getCount());
                    tasks.unmarkTask(index);
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5);
                    if (description.isEmpty()){
                        throw new InvalidTodoException();
                    }
                    tasks.addTask(new ToDo(description));
                } else if (input.startsWith("deadline ")) {
                    //split by /by
                    String[] information = input.substring(9).split(" /by", 2);
                    if (information.length < 2) {
                        throw new InvalidDeadlineException();
                    }
                    String description = information[0];
                    String by = information[1];
                    tasks.addTask(new Deadline(description, by));
                } else if (input.startsWith("event ")) {
                    //split by /from and /to
                    String[] information = input.substring(6).split(" /from | /to ", 3);
                    if (information.length < 3) {
                        throw new InvalidEventException();
                    }
                    String description = information[0];
                    String from = information[1];
                    String to = information[2];
                    tasks.addTask(new Event(description, from, to));
                } else if (input.startsWith("delete")) {
                    String[] inputs = input.trim().split(" ");
                    if (inputs.length < 2) {
                        throw new InvalidTaskIndexException(tasks.getCount());
                    }
                    int index = parseIndex(inputs[1], tasks.getCount());
                    tasks.deleteTask(index);
                }
                else {
                    System.out.println("Sorry! I don't know what this comment is supposed to be...");
                    System.out.println("Available commands: todo (description) , event (description) (from) (to), deadline (description) (by)");
                    System.out.println("Type 'bye' to cancel the chat!");
                }
            } catch (InvalidTaskIndexException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }

        }

        scanner.close();
    }
}
