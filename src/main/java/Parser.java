import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Parser {

    public static LocalDate stringToLocalDate(String dateString) throws MochiException {
        LocalDate date;
        String[] dateTime = dateString.split(" ");
        String header = dateTime[0];
        try {
            if (header.contains("/")) {
                date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("[yyyy/MM/dd]"));
            } else if (header.contains("-")) {
                date = LocalDate.parse(dateTime[0]);
            } else if (header.equals("tomorrow")) {
                date = LocalDate.now().plus(1, ChronoUnit.DAYS);
            } else if (header.equals("today")) {
                date = LocalDate.now();
            } else {
                throw new MochiException("Please input a proper date in the format [yyyy/MM/dd]");
            }
        } catch (DateTimeParseException e) {
            throw new MochiException("Please input a proper date in the format [yyyy/MM/dd]");
        }

        System.out.println(date.toString());
        return date;
    }

    // Parses a given string into a LocalDateTime object. For use in Deadlines.
    public static LocalDateTime stringToLocalDateTime(String dateTimeString) throws MochiException {
        LocalDateTime dateTime;
        LocalDate date;
        String[] dateTimeParts = dateTimeString.split(" ", 2);
        String dateString = dateTimeParts[0];
        // Parse date first, according to two different formats
        try {
            if (dateString.contains("/")) {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } else if (dateString.contains("-")) {
                date = LocalDate.parse(dateString);
            } else if (dateString.equals("tomorrow")) {
                date = LocalDate.now().plus(1, ChronoUnit.DAYS);
            } else if (dateString.equals("today")) {
                date = LocalDate.now();
            } else {
                throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
            }

        } catch (DateTimeParseException e) {
            // Prompt for new input
            throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
        }
        // Parse time
        try {
            // If timeString does not exist, ArrayIndexOutOfBoundsException will be thrown.
            String timeString = dateTimeParts[1];
            if (timeString.isEmpty()) {
                dateTime = date.atStartOfDay();
            } else {
                dateTime = date.atTime(LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm")));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            dateTime = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
        }

//        System.out.println(dateTime.toString());
        return dateTime;

    }
}
