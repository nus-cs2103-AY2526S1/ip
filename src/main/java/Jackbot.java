import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jackbot {

    static class Task {
        private final String description;
        private boolean done;

        Task(String description) {
            this.description = description;
            this.done = false;
        }

        void mark()   { this.done = true; }
        void unmark() { this.done = false; }

        String checkbox() { return done ? "[X]" : "[ ]"; }
        boolean isDone()  { return done; }
        String getDescription() { return description; }

        @Override
        public String toString() {
            return checkbox() + " " + description;
        }
    }

    // Helper function to print framed messages
    private static void printFramed(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        List<Task> tasklist = new ArrayList<>();

        // Start session
        printFramed("Hello! I'm Jackbot\nWhat can I do for you?\n");
        
        // Event loop
        while (true) {
            String input = sc.nextLine();
            
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                StringBuilder sb = new StringBuilder("Your previous entries:");
                for (int i = 0; i < tasklist.size(); i++) {
                    sb.append("\n").append(i + 1).append(". ").append(tasklist.get(i));
                }
                printFramed(sb.toString());
            } else if (input.toLowerCase().startsWith("mark ")) {
                Integer idx = Integer.parseInt(input.substring(5).trim());
                tasklist.get(idx - 1).mark();
            } else if (input.toLowerCase().startsWith("unmark ")) {
                Integer idx = Integer.parseInt(input.substring(7).trim());
                tasklist.get(idx - 1).unmark();
            } else { 
                // Else, store task in tasklist
                tasklist.add(new Task(input));
                printFramed("added: " + input + "\n");
            }
        }

        // End session
        printFramed(" Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
