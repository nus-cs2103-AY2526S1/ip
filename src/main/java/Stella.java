import java.util.Scanner;
import java.util.ArrayList;
public class Stella {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> lists = new ArrayList<>();


        System.out.println(" > Hello! I am Stella");
        System.out.println(" > What can I do for you?");

        String user_input = scan.nextLine();

        while (!user_input.equals("bye")) {
            try {
                if (user_input.equals(("list"))) {
                    if (lists.isEmpty()) {
                        System.out.println("Task list is empty. Add one now: ");
                    }
                    for (int i = 1; i <= lists.size(); i = i + 1) {
                        System.out.println(" > " + i + ". " + lists.get(i-1));
                    }
                } else if (user_input.contains("delete")) {
                    if (user_input.length() <= 7) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    int index = Integer.valueOf(user_input.substring(7)) - 1;
                    Task temp = lists.remove(index);
                    System.out.println("I have removed the following: ");
                    System.out.println("" + temp);


                    System.out.println(" > Now you have " + lists.size() + " task(s) in the list");

                }

                else if (user_input.contains("unmark")) {
                    if (user_input.length() <= 7) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    Integer index = Integer.valueOf(user_input.substring(7)) - 1;


                    lists.get(index).markUndone();
                    System.out.println(" > OK, I've marked this task as not done yet: ");
                    System.out.println(" > " + lists.get(index));

                } else if (user_input.contains("mark")) {
                    if (user_input.length() <= 5) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    Integer index = Integer.valueOf(user_input.substring(5)) - 1;
                    lists.get(index).markDone();
                    System.out.println(" > Nice! I've marked this task as done: ");
                    System.out.println(" > " + lists.get(index));

                } else if (user_input.contains("todo")) {
                    if (user_input.length() <= 5) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    String description = user_input.substring(5);
                    lists.add(new ToDo(description));
                    System.out.println(" > added: " + lists.get(lists.size() - 1));

                    System.out.println(" > Now you have " + lists.size() + " task(s) in the list");

                } else if (user_input.contains("deadline")) {
                    if (user_input.length() <= 9) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    String description = user_input.substring(9, user_input.indexOf('/'));
                    String deadline = user_input.substring(user_input.indexOf('/') + 1);

                    lists.add(new Deadline(description, deadline));
                    System.out.println(" > added: " + lists.get(lists.size() - 1));

                    System.out.println(" > Now you have " + lists.size() + " task(s) in the list");

                } else if (user_input.contains("event")) {
                    if (user_input.length() <= 6) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    String description = user_input.substring(6, user_input.indexOf('/'));
                    String start = user_input.substring(user_input.indexOf('/') + 1, user_input.lastIndexOf('/'));
                    String end = user_input.substring(user_input.lastIndexOf('/') + 1);

                    lists.add(new Event(description, start, end));
                    System.out.println(" > added: " + lists.get(lists.size() - 1));

                    System.out.println(" > Now you have " + lists.size() + " task(s) in the list");


                } else {
                    throw new UnknownInstructionException(user_input);
                }

                user_input = scan.nextLine();
            }
            catch (UnknownInstructionException e) {
                System.out.println(e.getMessage() + " is an invalid message. Type new message: ");
                user_input = scan.nextLine();
            }
            catch (IncompleteInstructionException e) {
                System.out.println(e.getMessage() + " is an incomplete message. Type new message: ");
                user_input = scan.nextLine();
            }
            catch (NullPointerException e) {
                System.out.println("Invalid value. Type new message: ");
                user_input = scan.nextLine();
            }
            catch (Exception e) {
                System.out.println("Did you key in the correct message? Type new message: ");
                user_input = scan.nextLine();
            }

        }
        System.out.println(" > Goodbye!");
    }





}
