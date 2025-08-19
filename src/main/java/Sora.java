import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] task = new String[100];
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
            if(input.equals("bye")){
                break;
            }
            else if (input.equals("list")){
                for (int i=0;i<count;i++){
                    System.out.println((i+1) + ". " + task[i]);
                }
            }
            else{
                System.out.println("added: " + input);
                task[count]=input;
                count++;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
