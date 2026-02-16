package shadow.parsers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


/**
 * A custom {@link TypeAdapter} implementation for serializing and deserializing
 * {@link LocalDateTime} objects using Gson.
 * <p>
 * This adapter converts {@link LocalDateTime} instances into ISO-8601 formatted
 * strings for JSON serialization and parses ISO-8601 formatted strings back into
 * {@link LocalDateTime} objects during deserialization.
 * <p>
 * It is designed to be used with {@link GsonBuilder}'s {@code registerTypeAdapter}
 * method for seamless integration into the serialization and deserialization workflow.
 */
public class DateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Serializes a {@link LocalDateTime} object to JSON.
     * <p>
     * Writes the date-time as a formatted string using the defined formatter.
     * If the value is {@code null}, writes a JSON null value.
     * </p>
     *
     * @param out the {@link JsonWriter} to write to
     * @param value the {@link LocalDateTime} value to serialize, may be {@code null}
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.format(formatter));
        }
    }

    /**
     * Deserializes a {@link LocalDateTime} object from JSON.
     * <p>
     * Reads a date-time string from JSON and parses it using the defined formatter.
     * </p>
     *
     * @param in the {@link JsonReader} to read from
     * @return the parsed {@link LocalDateTime} object
     * @throws IOException if an I/O error occurs
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String str = in.nextString();
        return LocalDateTime.parse(str, formatter);
    }
}
