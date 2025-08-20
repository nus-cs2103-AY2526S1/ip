import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] task = new Task[100];
        int count =0;

        String logo = "  ____                 \n"
                + " / ___|  ____ ______ __ \n"
                + " \\___ \\ / __ \\| '__/ _  \\\n"
                + "  ___\\ | |__| | | | |_| |\n"
                + " |____/ \\____/|_|  \\___/|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Sora");
        System.out.println("What can I do for you?");

        while(true){
            String input = scanner.nextLine();
            String[] part = input.split(" ");
            if(part.length==1 && part[0].equals("bye")){
                break;
            }
            else if (part.length==1 && part[0].equals("list")){
                System.out.println("Here are the task in your list:");
                for (int i=0;i<count;i++){
                    System.out.println((i+1) + ". " + task[i].toString());
                }
            }
            else if (part.length==2 && part[0].equals("mark")) {
                Integer x = Integer.parseInt(part[1]);
                task[x-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task[x-1].toString());
            }
            else if (part.length==2 && part[0].equals("unmark")) {
                Integer x = Integer.parseInt(part[1]);
                task[x-1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task[x-1].toString());
            }
            else{
                System.out.println("added: " + input);
                task[count]=new Task(input);
                count++;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
