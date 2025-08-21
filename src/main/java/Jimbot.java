import java.util.Scanner;

public class Jimbot {
    public static void main(String[] args) {
        String userInput;
        Response user = new Response();
        Scanner scanner = new Scanner(System.in);
        taskList userList = new taskList();

        user.hello("Jimbot");

        while (true) {
            userInput = scanner.nextLine().trim();
            int taskCount = userList.getTaskCount();

            if (userInput.toLowerCase().contains("bye")) {
                user.goodBye();
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                user.printList(userList.getTaskList());
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < taskCount) {
                    userList.getTask(index).markAsDone();
                    user.markRes(userList, index);
                } else {
                    System.out.println("Invalid task number!\n (╥﹏╥)");
                }
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                if (index < taskCount) {
                    userList.getTask(index).markAsUndone();
                    user.unmarkRes(userList, index);
                } else {
                    System.out.println("Invalid task number!\n (╥﹏╥)");
                }
            } else if (userInput.startsWith("deadline")) {

                if (!userInput.contains("/by")) {
                    System.out.println("Invalid deadline format!\n (╯°□°）╯︵ ┻━┻");
                } else {
                    String[] deadline = userInput.substring(9)
                            .trim()
                            .split("/by", 2);
                    String description =  deadline[0].trim();
                    String by = deadline[1].trim();

                    if (taskCount >= 100) {
                        System.out.println("Too many tasks!\n ＞_＜");
                    } else {
                        Deadline userDeadline = new Deadline(description, by);
                        userList.addToList(userDeadline);
                        user.addTaskRes(userDeadline, taskCount);
                    }
                }
            } else if (userInput.startsWith("event")) {

                if (!userInput.contains("/from") || !userInput.contains("/to")) {
                    System.out.println("Invalid event format...\n ಥ_ಥ");
                } else {
                    String[] event = userInput.substring(6)
                            .trim()
                            .split("/from", 2);

                    String description =  event[0].trim();
                    String[] timings = event[1]
                            .trim()
                            .split("/to", 2);

                    String from = timings[0].trim();
                    String to = timings[1].trim();

                    if (taskCount >= 100) {
                        System.out.println("Too many tasks!\n ＞_＜");
                    } else {
                        Event userEvent = new Event(description, from, to);
                        userList.addToList(userEvent);
                        user.addTaskRes(userEvent, taskCount);
                    }
                }
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5).trim();

                if (taskCount >= 100) {
                    System.out.println("Too many tasks!\n ＞_＜");
                } else {
                    ToDo userToDo = new ToDo(description);
                    userList.addToList(userToDo);
                    user.addTaskRes(userToDo, taskCount);
                }
            } else {
                user.echo(userInput);
            }
        }
        scanner.close();
    }
}
