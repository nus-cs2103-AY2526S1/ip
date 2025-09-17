package burrito.parser;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Parser {

    public Parser() {}

    /**
     * Parses date from input string
     *
     * @param input User input in format "YYYY/MM/DD"
     * @return Reformatted date.
     */
    static public String dateParser(String input) {
        try {

            LocalDate date = LocalDate.parse(input.replace("/", "-"));

            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            System.out.println("Error! Wrong date formatting (Use YYYY-MM-DD, eg 2019-12-28).");
            return input;
        } finally { }
    }

    /**
     * Parses time from input string
     *
     * @param input User input in 24h format
     * @return Reformatted time in 12h format.
     */
    static public String timeParser(String input) {
        try {
            String _24HourTime = input.charAt(0) + input.charAt(1) + ":" + input.charAt(2) + input.charAt(3);
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            return _12HourSDF.format(_24HourDt);


        } catch (Exception e) {
            System.out.println("Error! Wrong time formatting (Use 24h format, eg: '1300' for 1pm).");
            return input;
        } finally { }
    }



}
