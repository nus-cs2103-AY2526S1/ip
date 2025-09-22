package broccoli.Tasks;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

//@@author broccoli0616-reused
//Reused from https://www.baeldung.com/java-string-valid-date
// with minor modifications
public class DateValidatorUsingDateFormat implements DateValidator {
    private String dateFormat;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    public String convertDateFormat(String dateFormat){
        String[] parts = dateFormat.split("/");
        String newFormat = parts[2]+ "-" + parts[1] + "-" + parts[0];
        return newFormat;
    }

    @Override
    public boolean isValid(String dateStr) {
        String[] parts = dateStr.split("/");
        if(!(parts[2].length() == 4)){
            return false;
        }
        if(!(parts[1].length() == 2)){
            return false;
        }
        if(!(parts[0].length() == 2)){
            return false;
        }
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

//@@author:Chandra Prakash