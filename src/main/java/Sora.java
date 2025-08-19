import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
            else{
                System.out.println(input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
