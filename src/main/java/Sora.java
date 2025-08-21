import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] task = new Task[100];
        int count =0;

        String logo = "  ____\n"
                + " / ___|  ____ ______ __\n"
                + " \\___ \\ / __ \\| '__/ _  \\\n"
                + "  ___\\ | |__| | | | |_| |\n"
                + " |____/ \\____/|_|  \\___/|\n";
        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("Hello! I'm Sora");
        System.out.println("What can I do for you?");

        while(true){
            try {
                String input = scanner.nextLine();
                String[] part = input.split(" ");
                if (input.equals("bye")) {
                    break;
                } else if (part.length == 1 && part[0].equals("list")) {
                    System.out.println("Here are the task in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + task[i].toString());
                    }
                } else if (part.length == 2 && part[0].equals("mark")) {
                    int x = Integer.parseInt(part[1]);
                    task[x - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task[x - 1].toString());
                } else if (part.length == 2 && part[0].equals("unmark")) {
                    int x = Integer.parseInt(part[1]);
                    task[x - 1].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task[x - 1].toString());
                } else {
                    if (part[0].equals("todo")) {
                        if (part.length == 1) {
                            throw new SoraException("Cannot todo nothing!");
                        }
                        task[count] = new Todo(input.substring(5));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task[count].toString());
                        count++;
                        System.out.println("Now you have " + count + " tasks in the list.");
                    } else if (part.length > 1 && part[0].equals("deadline")) {
                        if(!input.contains("/by")){
                            throw new SoraException("Lack of deadline time!");
                        }
                        String[] check = input.substring(9).split(" /by ");
                        task[count] = new Deadline(check[0], check[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task[count].toString());
                        count++;
                        System.out.println("Now you have " + count + " tasks in the list.");
                    } else if (part.length > 1 && part[0].equals("event")) {
                        if(!input.contains("/from")){
                            throw new SoraException("Lack of start time!");
                        }
                        String[] check = input.substring(6).split(" /from ");
                        if(!input.contains("/to")){
                            throw new SoraException("Lack of end time!");
                        }
                        String[] time = check[1].split(" /to ");
                        task[count] = new Event(check[0], time[0], time[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task[count].toString());
                        count++;
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                    else{
                        throw new SoraException("Not a valid input!");
                    }
                }
            }
            catch (SoraException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
