package cat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static boolean isValidDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public static String analyse(String userInput){

        String pattern = "d/M/yyyy HHmm";

        try {
            String output = "";

            assert userInput != null : "User input cannot be null";
            Exception.checkEmptyInput(userInput);

            int last = userInput.charAt(userInput.length() - 1);
            int num = last - '0';

            //String[] parts = userInput.split("/");
            String[] parts = userInput.split("\\|");

            parts[0] = parts[0].trim().toLowerCase();


            if (parts[0].equals("list")) {
                output += Meow.printAll();
            } else if (parts[0].equals("bye")) {
                output += Ui.showBye();
            } else if(parts[0].equals("unmark")) {
                Meow.tasks.get(num - 1).setUnDone();
                output += Meow.printAll();
            }else if(parts[0].equals("mark")) {
                Meow.tasks.get(num - 1).setDone();
                output += Meow.printAll();
            }else if(parts[0].equals("delete")){
                Meow.tasks.remove(num - 1);
                output += Meow.printAll();
            }else if(parts[0].equals("t")) {
                String name = parts[1].trim();
                Todo todo = new Todo(name);
                Meow.tasks.add(todo);
                output += Meow.printAll();
            }else if(parts[0].equals("d")) {
                String name = parts[1].trim();
                String endDate = parts[2].trim();
                if (isValidDate(endDate, pattern)){
                    Deadline deadline = new Deadline(name, endDate);
                    Meow.tasks.add(deadline);
                    output += Meow.printAll();
                }else{
                    output += "Wrong date format";
                }

            } else if(parts[0].equals("e")) {
                String name = parts[1].trim();
                String startDate = parts[2].trim();
                String endDate = parts[3].trim();
                if (isValidDate(startDate, pattern) && isValidDate(endDate, pattern)){
                    Event event = new Event(name, startDate, endDate);
                    Meow.tasks.add(event);
                    output += Meow.printAll();
                }else{
                    output += "Wrong date format";
                }

            }else if(parts[0].equals("find")) {
                String keyword = parts[1].trim();
                output += Meow.find(keyword);
            } else{
                output += Ui.inValidInput();
                output += Ui.showAsk();
            }

            return output;


        } catch (Exception e) {
            //System.out.println("OOPS! " + e.getMessage());
            //String exc = "OOPS! " + e.getMessage();
            return "OOPS! " + e.getMessage();
        }

    }

}
