import java.util.Scanner;
import java.util.ArrayList;
public class Stella {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        TaskList lists = new TaskList(Storage.readFile());

        System.out.println(" > Hello! I am Stella");
        System.out.println(" > What can I do for you?");

        String user_input = scan.nextLine();

        while (!user_input.equals("bye")) {
            try {
                if (user_input.equals(("list"))) {
                    lists.printList();
                } else if (user_input.contains("delete")) {
                    if (user_input.length() <= 7) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    int index = Integer.valueOf(user_input.substring(7)) - 1;
                    lists.deleteItem(index);

                }

                else if (user_input.contains("unmark")) {
                    if (user_input.length() <= 7) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    Integer index = Integer.valueOf(user_input.substring(7)) - 1;
                    lists.modifyItem(index, "unmark");

                } else if (user_input.contains("mark")) {
                    if (user_input.length() <= 5) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    Integer index = Integer.valueOf(user_input.substring(5)) - 1;
                    lists.modifyItem(index, "mark");

                } else if (user_input.contains("todo")) {
                    if (user_input.length() <= 5) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    String description = user_input.substring(5);
                    ToDo temp = new ToDo(description);
                    lists.addItem(temp);

                } else if (user_input.contains("deadline")) {
                    if (user_input.length() <= 9) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    String description = user_input.substring(9, user_input.indexOf('/'));
                    String deadline = user_input.substring(user_input.indexOf('/') + 1);
                    if (deadline.length() == 10) {
                        deadline = TimeConverter.convertDate(deadline);
                    }
                    if (deadline.length() == 15) {
                        deadline = TimeConverter.convertDatewithTime(deadline);
                    }

                    Deadline temp = new Deadline(description, deadline);
                    lists.addItem(temp);


                } else if (user_input.contains("event")) {
                    if (user_input.length() <= 6) {
                        throw new IncompleteInstructionException(user_input);
                    }
                    String description = user_input.substring(6, user_input.indexOf('/'));
                    String start = user_input.substring(user_input.indexOf('/') + 1, user_input.lastIndexOf('/'));
                    String end = user_input.substring(user_input.lastIndexOf('/') + 1);
                    if (start.length() == 10) {
                        start = TimeConverter.convertDate(start);
                    }
                    if (start.length() == 15) {
                        start = TimeConverter.convertDatewithTime(start);
                    }
                    if (end.length() == 10) {
                        end = TimeConverter.convertDate(end);
                    }
                    if (end.length() == 15) {
                        end = TimeConverter.convertDatewithTime(end);
                    }
                    Event temp = new Event(description, start, end);
                    lists.addItem(temp);

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
