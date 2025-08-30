package stella;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class TimeConverter {
    // input is either dd-mm-yyyy or dd-mm-yyyy-tttt
    public static String convertDatewithTime(String rawFormat) {
        try {
            int day = Integer.parseInt(rawFormat.substring(0, 2));
            int month = Integer.parseInt(rawFormat.substring(3, 5));
            int year = Integer.parseInt(rawFormat.substring(6, 10));
            int hour = Integer.parseInt(rawFormat.substring(11, 13));
            int min = Integer.parseInt(rawFormat.substring(13, 15));
            LocalDateTime input = LocalDateTime.of(year, month,day, hour,min);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:ss, dd MMMM yyyy");

            return input.format(formatter);
        }
        catch (NumberFormatException e) {
            System.out.println("The date you key in may be invalid.");
        }
        return "Invalid Timing";
    }
    public static String convertDate(String rawFormat) {
        try {
            int day = Integer.parseInt(rawFormat.substring(0, 2));
            int month = Integer.parseInt(rawFormat.substring(3, 5));
            int year = Integer.parseInt(rawFormat.substring(6, 10));

            LocalDate input = LocalDate.of(year, month,day);


            return input.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        }
        catch (NumberFormatException e) {
            System.out.println("The date you key in may be invalid.");
        }
        return "Invalid Timing";
    }

}
