package stella;

import java.util.Scanner;
public class Ui {
    private Parser parser;
    public Ui(Parser parser) {
        this.parser = parser;
    }


    public void callInteraction() {
        Scanner scan = new Scanner(System.in);
        System.out.println(" > Hello! I am Stella");
        System.out.println(" > What can I do for you?");
        String user_input = scan.nextLine();


        while (!user_input.equals("bye")) {
            try {
                parser.identifyCommand(user_input);
                user_input = scan.nextLine();
            } catch (UnknownInstructionException e) {
                System.out.println(e.getMessage() + " is an invalid message. Type new message: ");
                user_input = scan.nextLine();
            } catch (IncompleteInstructionException e) {
                System.out.println(e.getMessage() + " is an incomplete message. Type new message: ");
                user_input = scan.nextLine();
            } catch (NullPointerException e) {
                System.out.println("Invalid value. Type new message: ");
                user_input = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Did you key in the correct message? Type new message: ");
                user_input = scan.nextLine();
            }
        }
        System.out.println(" > Goodbye!");
    }

    }




